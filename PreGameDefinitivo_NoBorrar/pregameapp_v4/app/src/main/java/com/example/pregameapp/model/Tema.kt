package com.example.pregameapp.model

data class Tema(
    val name: String,
    val words: List<String>
) {
    fun getRandom(): String = words.random()
}
