package com.example.pregameapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pregameapp.ui.theme.*
import kotlinx.coroutines.delay

class MimicaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PreGameTheme {
                MimicaScreen(onBack = { finish() })
            }
        }
    }
}

@Composable
fun MimicaScreen(onBack: () -> Unit) {
    val context = LocalContext.current

    val categorias = mapOf(
        "🎬 Películas" to listOf(
            "El Rey León", "Titanic", "Harry Potter", "Jurassic Park", "Matrix",
            "Frozen", "Avatar", "Los Vengadores", "Toy Story", "Shrek",
            "Interstellar", "El Señor de los Anillos", "Spider-Man", "Coco", "Up",
            "Piratas del Caribe", "Inception", "El Padrino", "Gladiator", "Forrest Gump",
            "El club de la lucha", "Pulp Fiction", "El exorcista", "Terminator", "Braveheart"
        ),
        "🐾 Animales" to listOf(
            "Elefante", "Pingüino", "Canguro", "Serpiente", "Gorila",
            "Flamenco", "Pulpo", "Medusa", "Cocodrilo", "Avestruz",
            "Camello", "Koala", "Caballo", "Delfín", "Murciélago",
            "Panda gigante", "Cebra", "Jirafa", "Rinoceronte", "Castor",
            "Mapache", "Foca", "Cuervo", "Cangrejo", "Toro"
        ),
        "⚽ Deportes" to listOf(
            "Tenis", "Golf", "Boxeo", "Natación", "Esquí",
            "Karate", "Ciclismo", "Esgrima", "Patinaje", "Lanzamiento de peso",
            "Surf", "Escalada", "Tiro con arco", "Remo", "Salto de altura",
            "Lucha libre", "Salto de trampolín", "Lanzamiento de jabalina", "Esquí acuático", "Polo"
        ),
        "💼 Profesiones" to listOf(
            "Cirujano", "Músico de orquesta", "Bombero", "Malabarista", "Escultor",
            "Panadero", "Árbitro de fútbol", "Astronauta", "Payaso", "Dentista",
            "Marinero", "Piloto de avión", "Director de cine", "Domador de leones", "Pescador",
            "Peluquero", "Mago de escenario", "Minero", "Trapecista", "Boxeador"
        ),
        "🎭 Situaciones" to listOf(
            "Bostezar sin parar", "Cocinar en una tormenta", "Bailar salsa",
            "Nadar en un río", "Escalar una montaña", "Planchar la ropa",
            "Pescar sin éxito", "Montar a caballo desbocado", "Dormir en el metro",
            "Pintar una pared muy alta", "Abrir un regalo enorme", "Luchar contra el viento",
            "Comer espaguetis sin tenedor", "Fotografiar un pájaro en vuelo",
            "Subir unas escaleras larguísimas", "Perseguir un autobús y perderlo",
            "Intentar abrir un frasco de conservas imposible", "Hablar por teléfono con mala cobertura"
        ),
        "🥘 Comida y cocina" to listOf(
            "Espaguetis a la boloñesa", "Sushi", "Paella", "Hamburguesa gigante", "Pizza margarita",
            "Taco mexicano", "Croissant", "Ramen japonés", "Ceviche", "Fondue de queso",
            "Langosta al vapor", "Crepe con Nutella", "Macarons de París", "Chuletón a la brasa", "Gazpacho"
        ),
        "🎵 Música y artistas" to listOf(
            "Tocar la guitarra eléctrica", "Ser DJ en una discoteca", "Dirigir una orquesta",
            "Tocar la batería", "Cantar ópera", "Rapear en un estadio",
            "Tocar el violín", "Bailar flamenco", "Hacer beatboxing",
            "Afinar una guitarra de oído", "Tocar el saxofón en la calle",
            "Grabar un videoclip", "Hacer un solo de guitarra", "Cantar en un karaoke horrible"
        ),
        "🏖️ Vacaciones y viajes" to listOf(
            "Facturar una maleta enorme en el aeropuerto", "Perderse en un país sin hablar el idioma",
            "Quemarse en la playa sin querer", "Montar en burro por la montaña",
            "Hacer snorkel y ver un tiburón", "Perderse en la selva amazónica",
            "Subir a la cima del Everest", "Cruzar el Sahara en camello",
            "Navegar en kayak por rápidos", "Hacer bungee jumping en Nueva Zelanda",
            "Viajar en tren por el Transiberiano", "Perderse en el metro de Tokio"
        ),
        "👻 Terror y fantasía" to listOf(
            "Fantasma atravesando paredes", "Zombi hambriento", "Vampiro sediento",
            "Bruja haciendo pociones", "Hombre lobo transformándose", "Momia desenfadajando",
            "Dragón escupiendo fuego", "Mago lanzando hechizos", "Robot malfuncionando",
            "Alien aterrizando en la Tierra", "Esqueleto bailando", "Gólem de piedra despertando"
        ),
        "📱 Tecnología y redes" to listOf(
            "Hacer un selfie ridículo", "Grabar una story de Instagram", "Quedarse sin batería",
            "Hacer una videollamada con lag", "Intentar conectarse a un WiFi malo",
            "Subir una foto a Instagram y esperar likes", "Enviar un audio de WhatsApp largo",
            "Scrollear TikTok tres horas seguidas", "Pagar con el móvil cuando no funciona",
            "Resetear un ordenador que no arranca"
        ),
        "🦸 Superhéroes y villanos" to listOf(
            "Superman volando", "Spider-Man lanzando telarañas", "Batman en la Batmobile",
            "Wonder Woman con el lazo", "Iron Man con la armadura", "Hulk destruyendo todo",
            "Thanos con el guantelete", "El Joker riendo", "Darth Vader respirando",
            "Gandalf con su bastón"
        ),
        "🌍 Países y culturas" to listOf(
            "Torero en la plaza de toros", "Samurái en Japón", "Gaucho argentino",
            "Oficial de la Guardia Suiza", "Músico mariachi mexicano", "Monje shaolín",
            "Sumo japonés", "Matador de flamenco", "Gladiador romano",
            "Viking navegando", "Faraón egipcio", "Pirata caribeño"
        ),
    )

    val categoriasList = categorias.keys.toList()

    var fase          by remember { mutableStateOf("categoria") }
    var catActual     by remember { mutableStateOf("") }
    var palabraActual by remember { mutableStateOf("") }
    var segundos      by remember { mutableStateOf(60) }
    var correcto      by remember { mutableStateOf(0) }
    var fallado       by remember { mutableStateOf(0) }
    var cronActivo    by remember { mutableStateOf(false) }
    var palabrasUsadas by remember { mutableStateOf(setOf<String>()) }

    LaunchedEffect(cronActivo) {
        if (cronActivo) {
            while (segundos > 0 && cronActivo) {
                delay(1000)
                segundos--
            }
            if (segundos == 0) {
                cronActivo = false
                fase = "resultado"
                com.example.pregameapp.ui.SensoryFeedback.vibrateError(context)
            }
        }
    }

    fun nuevaPalabra() {
        val palabras = categorias[catActual] ?: return
        val disponibles = palabras.filter { it !in palabrasUsadas }
        val siguiente = if (disponibles.isNotEmpty()) disponibles.random() else {
            palabrasUsadas = setOf()
            palabras.random()
        }
        palabrasUsadas = palabrasUsadas + siguiente
        palabraActual = siguiente
    }

    Box(modifier = Modifier.fillMaxSize().background(DeepBlack)) {
        Column(modifier = Modifier.fillMaxSize().statusBarsPadding()) {

            Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = {
                    cronActivo = false
                    onBack()
                }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, "Volver", tint = TextPrimary)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text("🎬 Mímica", color = TextPrimary, fontSize = 22.sp, fontWeight = FontWeight.Bold)

                if (fase == "jugando") {
                    Spacer(modifier = Modifier.weight(1f))
                    val color = if (segundos <= 10) ImpostorRed else MimicaOrange
                    Surface(shape = RoundedCornerShape(12.dp), color = CardDark) {
                        Text(
                            " ⏱ ${segundos}s ",
                            color = color,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }
                }
            }

            when (fase) {
                "categoria" -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("¿Qué categoría?", color = TextPrimary, fontSize = 22.sp, fontWeight = FontWeight.ExtraBold)
                        Text("Elige la temática de las palabras", color = TextMuted, fontSize = 14.sp)
                        Spacer(modifier = Modifier.height(24.dp))

                        categoriasList.forEach { cat ->
                            Button(
                                onClick = {
                                    catActual     = cat
                                    palabrasUsadas = setOf()
                                    correcto      = 0
                                    fallado       = 0
                                    segundos      = 60
                                    cronActivo    = false
                                    nuevaPalabra()
                                    fase = "preparar"
                                },
                                modifier = Modifier.fillMaxWidth().height(56.dp).padding(vertical = 4.dp),
                                shape = RoundedCornerShape(14.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = CardDark, contentColor = TextPrimary)
                            ) {
                                Text(cat, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }

                "preparar" -> {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("🎬", fontSize = 72.sp, textAlign = TextAlign.Center)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("¡Reglas del juego!", color = TextPrimary, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold, textAlign = TextAlign.Center)
                        Spacer(modifier = Modifier.height(16.dp))

                        Surface(shape = RoundedCornerShape(20.dp), color = SurfaceDark, modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.padding(20.dp)) {
                                ReglaMimica("🙊", "Sin hablar, sin sonidos")
                                ReglaMimica("👁️", "Solo gestos y expresiones")
                                ReglaMimica("⏱️", "60 segundos por ronda")
                                ReglaMimica("✅", "Acierto: +1 punto y nueva palabra")
                                ReglaMimica("❌", "Fallo: -1 punto y nueva palabra")
                                ReglaMimica("🔄", "Sin palabras repetidas")
                            }
                        }

                        Spacer(modifier = Modifier.height(32.dp))

                        Button(
                            onClick = { cronActivo = true; fase = "jugando" },
                            modifier = Modifier.fillMaxWidth().height(56.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MimicaOrange, contentColor = Color.White)
                        ) {
                            Text("¡Empezar!", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        }
                    }
                }

                "jugando" -> {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            Surface(modifier = Modifier.weight(1f), shape = RoundedCornerShape(14.dp), color = Color(0xFF14532D)) {
                                Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text("✅ Aciertos", color = TruthGreen, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                    Text("$correcto", color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.ExtraBold)
                                }
                            }
                            Surface(modifier = Modifier.weight(1f), shape = RoundedCornerShape(14.dp), color = Color(0xFF7F1D1D)) {
                                Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text("❌ Fallos", color = ImpostorRed, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                    Text("$fallado", color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.ExtraBold)
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(28.dp))
                        Text(catActual, color = MimicaOrange, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    Brush.verticalGradient(listOf(MimicaOrange, Color(0xFF9A3412))),
                                    shape = RoundedCornerShape(24.dp)
                                )
                                .padding(vertical = 40.dp, horizontal = 24.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                palabraActual,
                                color = Color.White,
                                fontSize = 32.sp,
                                fontWeight = FontWeight.ExtraBold,
                                textAlign = TextAlign.Center
                            )
                        }

                        Spacer(modifier = Modifier.height(28.dp))

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            Button(
                                onClick = {
                                    fallado++
                                    nuevaPalabra()
                                    com.example.pregameapp.ui.SensoryFeedback.vibrateError(context)
                                },
                                modifier = Modifier.weight(1f).height(64.dp),
                                shape = RoundedCornerShape(16.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = ImpostorRed, contentColor = Color.White)
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text("❌", fontSize = 24.sp)
                                    Text("No la saben", fontWeight = FontWeight.Bold, fontSize = 11.sp)
                                }
                            }

                            Button(
                                onClick = {
                                    correcto++
                                    nuevaPalabra()
                                    com.example.pregameapp.ui.SensoryFeedback.vibrateSuccess(context)
                                },
                                modifier = Modifier.weight(1f).height(64.dp),
                                shape = RoundedCornerShape(16.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = TruthGreen, contentColor = Color.White)
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text("✅", fontSize = 24.sp)
                                    Text("¡La adivinan!", fontWeight = FontWeight.Bold, fontSize = 11.sp)
                                }
                            }
                        }
                    }
                }

                "resultado" -> {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("⏰", fontSize = 64.sp, textAlign = TextAlign.Center)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("¡Tiempo!", color = TextPrimary, fontSize = 32.sp, fontWeight = FontWeight.ExtraBold)
                        Spacer(modifier = Modifier.height(24.dp))

                        Surface(shape = RoundedCornerShape(20.dp), color = SurfaceDark, modifier = Modifier.fillMaxWidth()) {
                            Row(modifier = Modifier.padding(24.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text("✅", fontSize = 32.sp)
                                    Text("$correcto", color = TruthGreen, fontSize = 40.sp, fontWeight = FontWeight.ExtraBold)
                                    Text("Aciertos", color = TextMuted, fontSize = 13.sp)
                                }
                                Divider(modifier = Modifier.height(80.dp).width(1.dp), color = BorderDark)
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text("❌", fontSize = 32.sp)
                                    Text("$fallado", color = ImpostorRed, fontSize = 40.sp, fontWeight = FontWeight.ExtraBold)
                                    Text("Fallos", color = TextMuted, fontSize = 13.sp)
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        val total = correcto + fallado
                        val msg = when {
                            total == 0          -> "¡Vaya, sin palabras! 😶"
                            correcto > fallado * 2 -> "¡Eres un actor/actriz increíble! 🌟"
                            correcto > fallado  -> "¡Buen trabajo! ¡A seguir practicando!"
                            correcto == fallado -> "¡Empate! ¡Revancha!"
                            else                -> "¡Qué difícil! ¡La próxima será mejor!"
                        }
                        Text(msg, color = TextMuted, fontSize = 14.sp, textAlign = TextAlign.Center)
                        Spacer(modifier = Modifier.height(32.dp))

                        Button(
                            onClick = { fase = "categoria" },
                            modifier = Modifier.fillMaxWidth().height(52.dp),
                            shape = RoundedCornerShape(14.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MimicaOrange, contentColor = Color.White)
                        ) {
                            Text("🔄 Nueva partida", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ReglaMimica(emoji: String, texto: String) {
    Row(modifier = Modifier.padding(vertical = 6.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(emoji, fontSize = 20.sp)
        Spacer(modifier = Modifier.width(12.dp))
        Text(texto, color = TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.Medium)
    }
}
