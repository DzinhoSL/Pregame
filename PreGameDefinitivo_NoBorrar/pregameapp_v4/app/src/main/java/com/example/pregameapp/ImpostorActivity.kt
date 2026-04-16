package com.example.pregameapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pregameapp.ui.theme.*

class ImpostorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PreGameTheme {
                ImpostorScreen(onBack = { finish() })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImpostorScreen(onBack: () -> Unit) {

    val temas = listOf(
        Pair("Playa",         listOf("Arena", "Olas", "Sombrilla", "Bikini", "Gaviotas", "Helado", "Surf", "Bronceador", "Chiringuito", "Chanclas")),
        Pair("Montaña",       listOf("Senderismo", "Cima", "Nieve", "Refugio", "Mochila", "Botas", "Cascada", "Niebla", "Pinos", "Barranquismo")),
        Pair("Discoteca",     listOf("DJ", "Luces", "Barra", "Baile", "Música", "Entrada", "Chupito", "Pista", "Cola", "Seguridad")),
        Pair("Aeropuerto",    listOf("Maleta", "Vuelo", "Control", "Terminal", "Pasaporte", "Embarque", "Aduana", "Escala", "Retraso", "Facturar")),
        Pair("Colegio",       listOf("Pizarra", "Recreo", "Mochila", "Deberes", "Profesor", "Tiza", "Comedor", "Examen", "Tutor", "Fotocopiadora")),
        Pair("Hospital",      listOf("Jeringa", "Bata", "Camilla", "Pastillas", "Rayos X", "Urgencias", "Bisturí", "Análisis", "Ecografía", "Quirófano")),
        Pair("Supermercado",  listOf("Carrito", "Caja", "Oferta", "Frutas", "Cola", "Ticket", "Congelados", "Sección", "Báscula", "Bolsa")),
        Pair("Circo",         listOf("Trapecio", "Payaso", "Domador", "Carpa", "Acróbata", "Pista", "Malabar", "Tigre", "Equilibrista", "Enano")),
        Pair("Campo de fútbol", listOf("Portería", "Árbitro", "Penalti", "Hinchada", "Córner", "Banquillo", "Tarjeta", "Fuera de juego", "Portero", "Gol")),
        Pair("Biblioteca",    listOf("Estantería", "Carné", "Silencio", "Ficha", "Préstamo", "Mesa", "Folio", "Catálogo", "Hemeroteca", "Marcapáginas")),
        Pair("Cocina",        listOf("Sartén", "Horno", "Cuchillo", "Sal", "Aceite", "Receta", "Espátula", "Batidora", "Colador", "Microondas")),
        Pair("Gym / Gimnasio",listOf("Mancuernas", "Cinta", "Espejo", "Proteína", "Vestuario", "Sudor", "Rutina", "Máquina", "Entrenador", "Taquilla")),
        Pair("Parque de atracciones", listOf("Montaña rusa", "Algodón de azúcar", "Cola", "Atracción", "Carrusel", "Tiro al blanco", "Palomitas", "Pulsera", "Tobogán", "Espera")),
        Pair("Camping",       listOf("Tienda", "Hoguera", "Saco de dormir", "Mosquitos", "Linterna", "Cantimplora", "Senderismo", "Mapa", "Leña", "Marshmallow")),
        Pair("Oficina",       listOf("Ordenador", "Reunión", "Café", "Jefe", "Excel", "Impresora", "Deadline", "Nómina", "Correo", "Presentación")),
        Pair("Concierto",     listOf("Escenario", "Micrófono", "Público", "Backstage", "Seguridad", "Canción", "Luces", "Entrada", "Bises", "Merchandising")),
        Pair("Tren / Metro",  listOf("Vagón", "Andén", "Billete", "Revisor", "Retraso", "Parada", "Asiento", "Altavoz", "Equipaje", "Conexión")),
        Pair("Zoo",           listOf("Jaula", "Guarda", "Pienso", "Flamenco", "Elefante", "Recinto", "Visita", "Plano", "Hábitat", "Cocodrilo")),
        Pair("Centro comercial", listOf("Escalera mecánica", "Probador", "Oferta", "Parking", "Food court", "Tienda", "Escaparate", "Saldo", "Bolsa", "Cine")),
        Pair("Barco / Crucero", listOf("Cubierta", "Camarote", "Capitán", "Ancla", "Timón", "Chaleco", "Buffet", "Puerto", "Ola", "Gaviota")),
        Pair("Boda",          listOf("Novia", "Alianza", "Vals", "Ramo", "Testigo", "Banquete", "Discurso", "Tarta", "Confeti", "Padrino")),
        Pair("Cumpleaños",    listOf("Tarta", "Vela", "Globo", "Sorpresa", "Regalo", "Matar", "Canción", "Fotos", "Fiesta", "Invitados")),
        Pair("Halloween",     listOf("Disfraz", "Calabaza", "Truco o trato", "Cementerio", "Bruja", "Fantasma", "Caramelos", "Miedo", "Murciélago", "Calavera")),
        Pair("Navidad",       listOf("Árbol", "Papá Noel", "Regalo", "Turrón", "Belén", "Villancico", "Reyes", "Cena", "Nieve", "Adorno")),
        Pair("Examen final",  listOf("Nervios", "Trampa", "Tiempo", "Folio", "Bolígrafo", "Chuleta", "Suspenso", "Nota", "Aprobado", "Silencio")),
        Pair("Primera cita",  listOf("Nervios", "Restaurante", "Flor", "Conversación", "Beso", "Cuenta", "Mirada", "Perfume", "WhatsApp", "Mentira")),
        Pair("Juicio",        listOf("Juez", "Abogado", "Testigo", "Fiscal", "Veredicto", "Celda", "Prueba", "Jurado", "Acusado", "Mazo")),
        Pair("Entrevista de trabajo", listOf("Currículum", "Traje", "Pregunta", "Nervios", "Competencia", "Sueldo", "HR", "Experiencia", "Mentira", "Empresa")),
        Pair("Festival de música", listOf("Cartel", "Barro", "Tienda", "Pulsera", "Escenario", "Cerveza", "Cola", "Cacheo", "Artista", "Acampar")),
        Pair("Spa / Relax",   listOf("Masaje", "Bañera", "Sauna", "Bata", "Aceite", "Silencio", "Vela", "Música relajante", "Pepino", "Aromaterapia")),
        Pair("Superhéroes",   listOf("Capa", "Poderes", "Villano", "Máscara", "Guarida", "Kryptonita", "Traje", "Ciudad", "Rescate", "Identidad secreta")),
        Pair("Videojuegos",   listOf("Mando", "Pantalla", "Nivel", "Vida extra", "Jefe final", "Multijugador", "DLC", "Ranking", "Combo", "Easter egg")),
        Pair("Redes sociales", listOf("Like", "Story", "Seguidor", "Filtro", "Trending", "Meme", "Influencer", "Algoritmo", "Troll", "Viral")),
        Pair("Película de terror", listOf("Susto", "Sangre", "Asesino", "Oscuridad", "Grito", "Trampa", "Huir", "Víctima", "Jumpscare", "Final")),
        Pair("Harry Potter",  listOf("Varita", "Escoba", "Mago", "Hechizo", "Castillo", "Sombrero", "Poción", "Fantasma", "Grifo", "Profesor")),
        Pair("Star Wars",     listOf("Sable de luz", "Fuerza", "Droide", "Nave", "Planeta", "Jedi", "Sith", "Casco", "Galaxia", "Imperio")),
        Pair("Espacio exterior", listOf("Astronauta", "Gravedad", "Satélite", "Nebulosa", "Agujero negro", "Cohete", "Traje espacial", "Órbita", "Telescopio", "Estación")),
        Pair("Fondo del mar",  listOf("Tiburón", "Coral", "Pulpo", "Submarino", "Presión", "Algas", "Pez globo", "Bioluminiscencia", "Ballena", "Buceo")),
        Pair("Dinosaurios",   listOf("T-Rex", "Fósil", "Meteoro", "Excavación", "Velocirraptor", "Triásico", "Huevo", "Colmillo", "Museo", "Pterodáctilo")),
        Pair("Laboratorio",   listOf("Probeta", "Microscopio", "Experimento", "Reacción", "Bata", "Explosión", "Fórmula", "Resultado", "Tesis", "Mezcla")),
        Pair("Barbacoa",      listOf("Parrilla", "Carbón", "Pincho", "Salsa", "Humo", "Cerveza", "Pinzas", "Alitas", "Amigos", "Madrugada")),
        Pair("Sushi",         listOf("Arroz", "Alga", "Salmón", "Palillos", "Wasabi", "Soja", "Tempura", "Sake", "Conveyor", "Nigiri")),
        Pair("Fast food",     listOf("Hamburguesa", "Patatas fritas", "Refresco", "Menú", "Ketchup", "Cajita", "Drive-through", "Nuggets", "Mostaza", "Caloría")),
        Pair("Antiguo Egipto",listOf("Pirámide", "Faraón", "Momia", "Jeroglífico", "Esfinge", "Nilo", "Tumba", "Cartucho", "Papiro", "Sarcófago")),
        Pair("Piratería",     listOf("Barco", "Mapa del tesoro", "Loro", "Parche", "Sable", "Abordaje", "Isla", "Calavera", "Ron", "Cañón")),
        Pair("Olimpiadas",    listOf("Antorcha", "Medalla", "Podio", "Atleta", "Récord", "Estadio", "Ceremonia", "Jurado", "Bandera", "Sprint")),
        Pair("Mafia / Crimen",listOf("Padrino", "Traición", "Pistola", "Guardia", "Código", "Reunión", "Chivato", "Fuga", "Soborno", "Alias")),
        Pair("Revolución francesa", listOf("Guillotina", "Rey", "Pueblo", "Versalles", "Barricada", "Traición", "Corona", "Cárcel", "Bandera", "Asamblea")),
        Pair("Feria de pueblo", listOf("Atracción", "Churros", "Verbena", "Caseta", "Pólvora", "Farolillos", "Peluche", "Caballito", "Orquesta", "Culebrón")),
        Pair("Mercadillo",    listOf("Regateo", "Antigüedad", "Puesto", "Gangas", "Cachivache", "Moneda", "Ropa", "Despertador", "Coleccionista", "Cartón")),
        Pair("Submarino / Inmersión", listOf("Tanque", "Presión", "Tiburón", "Arrecife", "Manguera", "Bucear", "Aleta", "Luz", "Corriente", "Naufragio")),
    )

    var temasUsados by remember { mutableStateOf(setOf<String>()) }
    var numJugadores  by remember { mutableStateOf(4) }
    var fase          by remember { mutableStateOf("config") }
    var jugadorActual by remember { mutableStateOf(0) }
    var roles         by remember { mutableStateOf(listOf<String>()) }
    var temaActual    by remember { mutableStateOf(Pair("", listOf<String>())) }
    var mostrarRol    by remember { mutableStateOf(false) }
    var impostorIdx   by remember { mutableStateOf(0) }
    var palabraRonda  by remember { mutableStateOf("") }

    val context = androidx.compose.ui.platform.LocalContext.current

    fun iniciarJuego() {
        val disponibles = temas.filter { it.first !in temasUsados }
        val pool = if (disponibles.isNotEmpty()) disponibles else {
            temasUsados = setOf()
            temas
        }
        val tema = pool.random()
        temasUsados = temasUsados + tema.first
        temaActual  = tema
        impostorIdx = (0 until numJugadores).random()
        val palabra = tema.second.random()
        palabraRonda = palabra
        roles = (0 until numJugadores).map { i ->
            if (i == impostorIdx)
                "🕵️ IMPOSTOR\n\nEl tema es: \"${tema.first}\"\n\nNo conoces la palabra exacta.\n¡Intenta no delatarte!"
            else
                "✅ CIUDADANO\n\nPalabra secreta:\n\"$palabra\""
        }
        jugadorActual = 0
        mostrarRol    = false
        fase          = "turno"
        com.example.pregameapp.ui.SensoryFeedback.vibrateSuccess(context)
    }

    Box(modifier = Modifier.fillMaxSize().background(DeepBlack)) {
        Column(modifier = Modifier.fillMaxSize().statusBarsPadding()) {

            Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, "Volver", tint = TextPrimary)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text("🕵️ Impostor", color = TextPrimary, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            }

            when (fase) {
                "config" -> {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("Configura la partida", color = TextMuted, fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(32.dp))

                        Surface(shape = RoundedCornerShape(20.dp), color = SurfaceDark, modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("Número de jugadores", color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                Spacer(modifier = Modifier.height(16.dp))
                                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                                    FilledIconButton(
                                        onClick = { if (numJugadores > 3) numJugadores-- },
                                        colors = IconButtonDefaults.filledIconButtonColors(containerColor = CardDark)
                                    ) {
                                        Text("−", color = TextPrimary, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                                    }
                                    Text("$numJugadores", color = ImpostorRed, fontSize = 48.sp, fontWeight = FontWeight.ExtraBold)
                                    FilledIconButton(
                                        onClick = { if (numJugadores < 10) numJugadores++ },
                                        colors = IconButtonDefaults.filledIconButtonColors(containerColor = CardDark)
                                    ) {
                                        Text("+", color = TextPrimary, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                                    }
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                Text("Mínimo 3 · Máximo 10", color = TextMuted, fontSize = 12.sp)
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))
                        Surface(shape = RoundedCornerShape(16.dp), color = CardDark, modifier = Modifier.fillMaxWidth()) {
                            Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                                Text("🎲", fontSize = 20.sp)
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    "${temas.size} temas · Sin repetición · Pista solo para el impostor",
                                    color = TextMuted,
                                    fontSize = 12.sp
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(32.dp))

                        Button(
                            onClick = { iniciarJuego() },
                            modifier = Modifier.fillMaxWidth().height(56.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = ImpostorRed, contentColor = Color.White)
                        ) {
                            Text("¡Empezar partida!", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        }
                    }
                }

                "turno" -> {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            "Jugador ${jugadorActual + 1}",
                            color = TextPrimary,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Text(
                            "Pasa el teléfono en privado",
                            color = TextMuted,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(32.dp))

                        AnimatedContent(
                            targetState = mostrarRol,
                            transitionSpec = {
                                (slideInVertically { it } + fadeIn()) togetherWith (slideOutVertically { -it } + fadeOut())
                            }
                        ) { show ->
                            if (!show) {
                                var swipeOffset by remember { mutableStateOf(0f) }
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(260.dp)
                                        .pointerInput(Unit) {
                                            detectVerticalDragGestures(
                                                onVerticalDrag = { _, dragAmount ->
                                                    swipeOffset += dragAmount
                                                },
                                                onDragEnd = {
                                                    if (swipeOffset < -80f) {
                                                        mostrarRol = true
                                                        if (jugadorActual == impostorIdx) {
                                                            com.example.pregameapp.ui.SensoryFeedback.vibrateError(context)
                                                        } else {
                                                            com.example.pregameapp.ui.SensoryFeedback.vibrateSelection(context)
                                                        }
                                                    }
                                                    swipeOffset = 0f
                                                }
                                            )
                                        },
                                    shape = RoundedCornerShape(24.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color.Transparent)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(
                                                Brush.verticalGradient(listOf(ImpostorRed, Color(0xFF7F1D1D))),
                                                shape = RoundedCornerShape(24.dp)
                                            ),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                            Text("☝️", fontSize = 48.sp)
                                            Spacer(modifier = Modifier.height(12.dp))
                                            Text(
                                                "Desliza hacia arriba\npara ver tu rol",
                                                color = Color.White,
                                                fontSize = 18.sp,
                                                fontWeight = FontWeight.Bold,
                                                textAlign = TextAlign.Center
                                            )
                                            Text("Solo míralo tú", color = Color.White.copy(alpha = 0.7f), fontSize = 13.sp)
                                        }
                                    }
                                }
                            } else {
                                val esImpostor = jugadorActual == impostorIdx
                                Card(
                                    modifier = Modifier.fillMaxWidth().height(260.dp),
                                    shape = RoundedCornerShape(24.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color.Transparent)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(
                                                Brush.verticalGradient(
                                                    if (esImpostor) listOf(Color(0xFF7F1D1D), Color(0xFF450A0A))
                                                    else listOf(Color(0xFF14532D), Color(0xFF052E16))
                                                ),
                                                shape = RoundedCornerShape(24.dp)
                                            )
                                            .padding(24.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            roles[jugadorActual],
                                            color = Color.White,
                                            fontSize = 17.sp,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center,
                                            lineHeight = 25.sp
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        if (mostrarRol) {
                            Button(
                                onClick = {
                                    if (jugadorActual < numJugadores - 1) {
                                        jugadorActual++
                                        mostrarRol = false
                                    } else {
                                        fase = "discusion"
                                    }
                                },
                                modifier = Modifier.fillMaxWidth().height(52.dp),
                                shape = RoundedCornerShape(14.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = ImpostorRed, contentColor = Color.White)
                            ) {
                                Text(
                                    if (jugadorActual < numJugadores - 1) "Siguiente jugador →" else "¡Empezar discusión!",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp
                                )
                            }
                        }
                    }
                }

                "discusion" -> {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("🗣️", fontSize = 64.sp, textAlign = TextAlign.Center)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("¡A debatir!", color = TextPrimary, fontSize = 28.sp, fontWeight = FontWeight.ExtraBold, textAlign = TextAlign.Center)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "Cada jugador da una pista sobre la palabra.\nEl impostor solo sabe el tema, ¡no la palabra!\nVotad quién creéis que es el impostor.",
                            color = TextMuted,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            lineHeight = 22.sp
                        )
                        Spacer(modifier = Modifier.height(40.dp))

                        Button(
                            onClick = { fase = "reveal" },
                            modifier = Modifier.fillMaxWidth().height(52.dp),
                            shape = RoundedCornerShape(14.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = ImpostorRed, contentColor = Color.White)
                        ) {
                            Text("Revelar impostor", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                        }
                    }
                }

                "reveal" -> {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("🎭", fontSize = 72.sp, textAlign = TextAlign.Center)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("El impostor era el", color = TextMuted, fontSize = 16.sp, textAlign = TextAlign.Center)
                        Text(
                            "Jugador ${impostorIdx + 1}",
                            color = ImpostorRed,
                            fontSize = 40.sp,
                            fontWeight = FontWeight.ExtraBold,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("La palabra era: \"$palabraRonda\"", color = TextMuted, fontSize = 16.sp, textAlign = TextAlign.Center)
                        Spacer(modifier = Modifier.height(40.dp))

                        Button(
                            onClick = { fase = "config" },
                            modifier = Modifier.fillMaxWidth().height(52.dp),
                            shape = RoundedCornerShape(14.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = ImpostorRed, contentColor = Color.White)
                        ) {
                            Text("🔄 Nueva partida", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                        }
                    }
                }
            }
        }
    }
}
