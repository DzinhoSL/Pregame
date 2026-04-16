package com.example.pregameapp.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.pregameapp.model.User

class UserDbHelper(context: Context) : SQLiteOpenHelper(context, "pregame.db", null, 2) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE usuarios (
                id       INTEGER PRIMARY KEY AUTOINCREMENT,
                username TEXT    NOT NULL UNIQUE,
                email    TEXT    NOT NULL UNIQUE,
                password TEXT    NOT NULL
            )
        """.trimIndent())

        db.execSQL("""
            CREATE TABLE preguntas_personalizadas (
                id      INTEGER PRIMARY KEY AUTOINCREMENT,
                tipo    TEXT    NOT NULL,
                texto   TEXT    NOT NULL
            )
        """.trimIndent())
    }

    // BUG FIX: onUpgrade ahora borra AMBAS tablas, no solo usuarios
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS preguntas_personalizadas")
        db.execSQL("DROP TABLE IF EXISTS usuarios")
        onCreate(db)
    }

    private fun hashPassword(password: String): String {
        val bytes = password.toByteArray()
        val md = java.security.MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }

    fun register(username: String, email: String, password: String): Boolean {
        return try {
            val cv = ContentValues().apply {
                put("username", username)
                put("email", email)
                put("password", hashPassword(password))
            }
            writableDatabase.insertOrThrow("usuarios", null, cv)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun login(email: String, password: String): User? {
        val cursor = readableDatabase.rawQuery(
            "SELECT * FROM usuarios WHERE email=? AND password=?",
            arrayOf(email, hashPassword(password))
        )
        return if (cursor.moveToFirst()) {
            val user = User(
                id       = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                username = cursor.getString(cursor.getColumnIndexOrThrow("username")),
                email    = cursor.getString(cursor.getColumnIndexOrThrow("email")),
                password = cursor.getString(cursor.getColumnIndexOrThrow("password"))
            )
            cursor.close()
            user
        } else {
            cursor.close()
            null
        }
    }

    fun userExists(email: String): Boolean {
        val cursor = readableDatabase.rawQuery(
            "SELECT id FROM usuarios WHERE email=?", arrayOf(email)
        )
        val exists = cursor.moveToFirst()
        cursor.close()
        return exists
    }

    fun addCustomQuestion(tipo: String, texto: String): Boolean {
        return try {
            val cv = ContentValues().apply {
                put("tipo", tipo)
                put("texto", texto)
            }
            writableDatabase.insert("preguntas_personalizadas", null, cv)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getCustomQuestions(tipo: String): List<String> {
        val list = mutableListOf<String>()
        val cursor = readableDatabase.rawQuery(
            "SELECT texto FROM preguntas_personalizadas WHERE tipo=?",
            arrayOf(tipo)
        )
        if (cursor.moveToFirst()) {
            do { list.add(cursor.getString(0)) } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }

    fun deleteCustomQuestion(texto: String) {
        writableDatabase.delete("preguntas_personalizadas", "texto=?", arrayOf(texto))
    }
}
