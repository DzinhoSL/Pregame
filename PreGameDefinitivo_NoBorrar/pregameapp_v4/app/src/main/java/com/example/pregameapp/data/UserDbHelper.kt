package com.example.pregameapp.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.database.sqlite.SQLiteOpenHelper
import com.example.pregameapp.model.User
import com.example.pregameapp.model.Partida
import com.example.pregameapp.model.Jugador

class UserDbHelper(context: Context) : SQLiteOpenHelper(context, "pregame.db", null, 3) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE usuarios (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                username TEXT NOT NULL UNIQUE,
                email TEXT NOT NULL UNIQUE,
                password TEXT NOT NULL
            )
        """.trimIndent())

        db.execSQL("""
            CREATE TABLE preguntas_personalizadas (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                tipo TEXT NOT NULL,
                texto TEXT NOT NULL
            )
        """.trimIndent())

        db.execSQL("""
            CREATE TABLE partidas (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                fecha DATETIME DEFAULT CURRENT_TIMESTAMP
            )
        """.trimIndent())

        db.execSQL("""
            CREATE TABLE jugadores (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                partida_id INTEGER NOT NULL,
                nombre TEXT NOT NULL,
                puntuacion INTEGER DEFAULT 0,
                FOREIGN KEY(partida_id) REFERENCES partidas(id) ON DELETE CASCADE
            )
        """.trimIndent())
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS jugadores")
        db.execSQL("DROP TABLE IF EXISTS partidas")
        db.execSQL("DROP TABLE IF EXISTS preguntas_personalizadas")
        db.execSQL("DROP TABLE IF EXISTS usuarios")
        onCreate(db)
    }

    override fun onOpen(db: SQLiteDatabase) {
        super.onOpen(db)
        if (!db.isReadOnly) {
            db.execSQL("PRAGMA foreign_keys=ON;")
        }
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
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                username = cursor.getString(cursor.getColumnIndexOrThrow("username")),
                email = cursor.getString(cursor.getColumnIndexOrThrow("email")),
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

    fun crearPartida(nombre: String): Long {
        val cv = ContentValues().apply {
            put("nombre", nombre)
        }
        return writableDatabase.insert("partidas", null, cv)
    }

    fun agregarJugador(partidaId: Long, nombre: String): Long {
        val cv = ContentValues().apply {
            put("partida_id", partidaId)
            put("nombre", nombre)
            put("puntuacion", 0)
        }
        return writableDatabase.insert("jugadores", null, cv)
    }

    fun actualizarPuntuacionJugador(jugadorId: Long, puntosSumar: Int) {
        writableDatabase.execSQL("UPDATE jugadores SET puntuacion = puntuacion + ? WHERE id = ?", arrayOf(puntosSumar, jugadorId))
    }

    fun obtenerRankingPartida(partidaId: Long): List<Jugador> {
        val lista = mutableListOf<Jugador>()
        val cursor = readableDatabase.rawQuery(
            "SELECT * FROM jugadores WHERE partida_id=? ORDER BY puntuacion DESC",
            arrayOf(partidaId.toString())
        )
        if (cursor.moveToFirst()) {
            do {
                lista.add(Jugador(
                    id = cursor.getLong(cursor.getColumnIndexOrThrow("id")),
                    partidaId = cursor.getLong(cursor.getColumnIndexOrThrow("partida_id")),
                    nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                    puntuacion = cursor.getInt(cursor.getColumnIndexOrThrow("puntuacion"))
                ))
            } while (cursor.moveNext())
        }
        cursor.close()
        return lista
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
