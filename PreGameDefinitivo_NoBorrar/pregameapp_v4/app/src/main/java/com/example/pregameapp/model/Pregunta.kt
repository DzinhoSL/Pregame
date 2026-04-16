package com.example.pregameapp.model

data class Pregunta(
    val enunciado: String,
    val opciones: List<String>,
    val correcta: Int
)
