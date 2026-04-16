package com.example.pregameapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pregameapp.data.UserDbHelper
import com.example.pregameapp.ui.theme.*

class CustomQuestionsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PreGameTheme {
                CustomQuestionsScreen(onBack = { finish() })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomQuestionsScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val db = remember { UserDbHelper(context) }
    
    var tipoSeleccionado by remember { mutableStateOf("verdad") }
    var nuevoTexto by remember { mutableStateOf("") }
    var preguntas by remember { mutableStateOf(listOf<String>()) }
    
    // Cargar preguntas al cambiar el tipo o al inicio
    LaunchedEffect(tipoSeleccionado) {
        preguntas = db.getCustomQuestions(tipoSeleccionado)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlack)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            // Cabecera
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, "Volver", tint = TextPrimary)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text("Personalizar Juego", color = TextPrimary, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            }

            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                // Selector de tipo
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterChip(
                        selected = tipoSeleccionado == "verdad",
                        onClick = { tipoSeleccionado = "verdad" },
                        label = { Text("Verdades") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = TruthGreen,
                            selectedLabelColor = Color.White,
                            containerColor = CardDark,
                            labelColor = TextMuted
                        )
                    )
                    FilterChip(
                        selected = tipoSeleccionado == "reto",
                        onClick = { tipoSeleccionado = "reto" },
                        label = { Text("Retos") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = NeonPurple,
                            selectedLabelColor = Color.White,
                            containerColor = CardDark,
                            labelColor = TextMuted
                        )
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Input para nueva pregunta
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = nuevoTexto,
                        onValueChange = { nuevoTexto = it },
                        placeholder = { Text("Añade tu propia $tipoSeleccionado...", color = TextMuted) },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary,
                            cursorColor = NeonPurple,
                            focusedBorderColor = NeonPurple,
                            unfocusedBorderColor = BorderDark
                        )
                    )
                    IconButton(
                        onClick = {
                            if (nuevoTexto.isNotBlank()) {
                                db.addCustomQuestion(tipoSeleccionado, nuevoTexto.trim())
                                preguntas = db.getCustomQuestions(tipoSeleccionado)
                                nuevoTexto = ""
                            }
                        },
                        modifier = Modifier
                            .size(56.dp)
                            .background(if (tipoSeleccionado == "verdad") TruthGreen else NeonPurple, RoundedCornerShape(12.dp))
                    ) {
                        Icon(Icons.Default.Add, "Añadir", tint = Color.White)
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    "TUS PREGUNTAS (${preguntas.size})",
                    color = TextMuted,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Lista de preguntas
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(bottom = 20.dp)
                ) {
                    if (preguntas.isEmpty()) {
                        item {
                            Box(
                                modifier = Modifier.fillMaxWidth().padding(40.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("No hay preguntas personalizadas aún", color = TextMuted, fontSize = 14.sp)
                            }
                        }
                    }
                    items(preguntas) { texto ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = SurfaceDark)
                        ) {
                            Row(
                                modifier = Modifier.padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    texto,
                                    color = TextPrimary,
                                    modifier = Modifier.weight(1f),
                                    fontSize = 14.sp
                                )
                                IconButton(onClick = {
                                    db.deleteCustomQuestion(texto)
                                    preguntas = db.getCustomQuestions(tipoSeleccionado)
                                }) {
                                    Icon(Icons.Default.Delete, "Eliminar", tint = Color.Red.copy(alpha = 0.7f))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
