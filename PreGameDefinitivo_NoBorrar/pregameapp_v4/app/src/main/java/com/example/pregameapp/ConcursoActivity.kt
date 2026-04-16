package com.example.pregameapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pregameapp.model.Pregunta
import com.example.pregameapp.ui.theme.*
import kotlinx.coroutines.delay

class ConcursoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PreGameTheme {
                ConcursoScreen(onBack = { finish() })
            }
        }
    }
}

fun Modifier.shakeFeedback(trigger: Boolean): Modifier = composed {
    val animatable = remember { Animatable(0f) }
    LaunchedEffect(trigger) {
        if (trigger) {
            animatable.animateTo(
                targetValue = 0f,
                animationSpec = keyframes {
                    durationMillis = 300
                    0f   at 0
                    -14f at 40
                    14f  at 80
                    -10f at 120
                    10f  at 160
                    -6f  at 200
                    6f   at 240
                    0f   at 300
                }
            )
        }
    }
    this.graphicsLayer { translationX = animatable.value }
}

private val GoldAccent    = Color(0xFFFBBF24)
private val BlueVibrant   = Color(0xFF3B82F6)
private val BlueDeep      = Color(0xFF1D4ED8)
private val GreenRight    = Color(0xFF10B981)
private val RedWrong      = Color(0xFFEF4444)
private val SurfaceCard   = Color(0xFF1E1E2E)
private val SurfaceOption = Color(0xFF2A2A3E)
private val TimerOrange   = Color(0xFFF97316)

@Composable
fun ConcursoScreen(onBack: () -> Unit) {
    val context = LocalContext.current

    val poolPreguntas = listOf(
        Pregunta("¿Cuál es el planeta más grande del sistema solar?", listOf("Saturno","Júpiter","Neptuno","Urano"), 1),
        Pregunta("¿En qué año llegó el hombre a la Luna?", listOf("1965","1967","1969","1972"), 2),
        Pregunta("¿Cuántos huesos tiene el cuerpo humano adulto?", listOf("196","206","216","226"), 1),
        Pregunta("¿Cuál es el océano más grande del mundo?", listOf("Atlántico","Índico","Ártico","Pacífico"), 3),
        Pregunta("¿Cuál es el elemento químico más abundante en el universo?", listOf("Oxígeno","Helio","Hidrógeno","Carbono"), 2),
        Pregunta("¿Qué gas hace que los refrescos tengan burbujas?", listOf("Oxígeno","Nitrógeno","Dióxido de carbono","Argón"), 2),
        Pregunta("¿Cuántos cromosomas tiene una célula humana?", listOf("23","44","46","48"), 2),
        Pregunta("¿A qué velocidad viaja la luz aproximadamente?", listOf("200.000 km/s","300.000 km/s","400.000 km/s","150.000 km/s"), 1),
        Pregunta("¿Cuál es el animal terrestre más rápido?", listOf("León","Guepardo","Visón","Antílope"), 1),
        Pregunta("¿Cuántos lados tiene un hexágono?", listOf("5","6","7","8"), 1),
        Pregunta("¿Cuántos colores tiene el arcoíris?", listOf("5","6","7","8"), 2),
        Pregunta("¿Qué planeta se conoce como el planeta rojo?", listOf("Júpiter","Venus","Marte","Saturno"), 2),
        Pregunta("¿Cuántos litros de sangre tiene un adulto de media?", listOf("3-4 L","5-6 L","7-8 L","9-10 L"), 1),
        Pregunta("¿Cuál es el hueso más largo del cuerpo humano?", listOf("Tibia","Húmero","Fémur","Peroné"), 2),
        Pregunta("¿Cuál es el animal más grande del mundo?", listOf("Elefante africano","Ballena azul","Tiburón ballena","Jirafa"), 1),
        Pregunta("¿Qué porcentaje del cuerpo humano es agua aproximadamente?", listOf("50%","60%","70%","80%"), 2),
        Pregunta("¿En qué órgano se produce la bilis?", listOf("Páncreas","Riñón","Hígado","Estómago"), 2),
        Pregunta("¿Cuál es el metal más abundante en la corteza terrestre?", listOf("Hierro","Aluminio","Cobre","Silicio"), 1),
        Pregunta("¿Quién pintó la Mona Lisa?", listOf("Miguel Ángel","Rafael","Leonardo da Vinci","Botticelli"), 2),
        Pregunta("¿De qué material está hecha la Torre Eiffel?", listOf("Acero inoxidable","Hierro forjado","Aluminio","Hormigón"), 1),
        Pregunta("¿Quién escribió Don Quijote de la Mancha?", listOf("Lope de Vega","Quevedo","Cervantes","Góngora"), 2),
        Pregunta("¿En qué ciudad está el Museo del Prado?", listOf("Barcelona","Sevilla","Madrid","Valencia"), 2),
        Pregunta("¿Quién pintó La noche estrellada?", listOf("Monet","Picasso","Van Gogh","Dalí"), 2),
        Pregunta("¿Cuántas sinfonías escribió Beethoven?", listOf("7","8","9","10"), 2),
        Pregunta("¿Quién esculpió el David?", listOf("Donatello","Rafael","Leonardo","Miguel Ángel"), 3),
        Pregunta("¿En qué ciudad está la Sagrada Familia?", listOf("Madrid","Valencia","Bilbao","Barcelona"), 3),
        Pregunta("¿Quién escribió Cien años de soledad?", listOf("Vargas Llosa","Borges","García Márquez","Cortázar"), 2),
        Pregunta("¿Qué pintor español es conocido por el cubismo?", listOf("Dalí","Miró","Picasso","Goya"), 2),
        Pregunta("¿Cuál es la capital de Australia?", listOf("Sydney","Melbourne","Canberra","Perth"), 2),
        Pregunta("¿Cuál es el río más largo del mundo?", listOf("Amazonas","Nilo","Yangtsé","Misisipi"), 1),
        Pregunta("¿Cuál es la montaña más alta del mundo?", listOf("K2","Mont Blanc","Everest","Kilimanjaro"), 2),
        Pregunta("¿Cuál es el país más pequeño del mundo?", listOf("Mónaco","San Marino","Ciudad del Vaticano","Liechtenstein"), 2),
        Pregunta("¿Cuál es el país más grande del mundo por superficie?", listOf("China","Canadá","EEUU","Rusia"), 3),
        Pregunta("¿En qué continente está Egipto?", listOf("Asia","Europa","América","África"), 3),
        Pregunta("¿Cuál es el desierto más grande del mundo?", listOf("Sahara","Gobi","Ártico","Kalahari"), 0),
        Pregunta("¿En qué país está el río Amazonas?", listOf("Bolivia","Perú","Colombia","Brasil"), 3),
        Pregunta("¿Cuál es la capital de Japón?", listOf("Osaka","Tokio","Kioto","Hiroshima"), 1),
        Pregunta("¿Cuál es la capital de Brasil?", listOf("Río de Janeiro","São Paulo","Salvador","Brasilia"), 3),
        Pregunta("¿En qué año comenzó la Primera Guerra Mundial?", listOf("1912","1914","1916","1918"), 1),
        Pregunta("¿Quién fue el primer presidente de los Estados Unidos?", listOf("Thomas Jefferson","Abraham Lincoln","George Washington","Benjamin Franklin"), 2),
        Pregunta("¿En qué año cayó el Muro de Berlín?", listOf("1987","1988","1989","1991"), 2),
        Pregunta("¿En qué año se descubrió América?", listOf("1488","1490","1492","1498"), 2),
        Pregunta("¿Cuántos años duró la Guerra de los Cien Años?", listOf("100","116","80","150"), 1),
        Pregunta("¿En qué año terminó la Segunda Guerra Mundial?", listOf("1943","1944","1945","1946"), 2),
        Pregunta("¿En qué año fue la Revolución francesa?", listOf("1776","1789","1800","1815"), 1),
        Pregunta("¿Cuántos jugadores tiene un equipo de baloncesto en cancha?", listOf("4","5","6","7"), 1),
        Pregunta("¿Qué país ha ganado más Copas del Mundo de fútbol?", listOf("Alemania","Argentina","Brasil","Italia"), 2),
        Pregunta("¿Cuántos metros mide la distancia de un maratón?", listOf("40 km","42,195 km","43 km","45 km"), 1),
        Pregunta("¿En qué año se fundó el Real Madrid?", listOf("1898","1900","1902","1905"), 2),
        Pregunta("¿Cuántos puntos vale un try en rugby?", listOf("3","4","5","6"), 2),
        Pregunta("¿Cuántos libros tiene la saga Harry Potter?", listOf("5","6","7","8"), 2),
        Pregunta("¿Cuántas temporadas tiene Breaking Bad?", listOf("4","5","6","7"), 1),
        Pregunta("¿En qué año se lanzó el primer iPhone?", listOf("2005","2006","2007","2008"), 2),
        Pregunta("¿Cómo se llama la nave de Han Solo en Star Wars?", listOf("X-Wing","Slave I","Halcón Milenario","Estrella de la Muerte"), 2),
        Pregunta("¿Qué banda es conocida como 'Los Fab Four'?", listOf("Rolling Stones","The Who","The Beatles","Led Zeppelin"), 2),
        Pregunta("¿En qué año salió el videojuego Minecraft?", listOf("2009","2010","2011","2012"), 1),
        Pregunta("¿De qué país es originario el sushi?", listOf("China","Corea","Japón","Tailandia"), 2),
        Pregunta("¿De qué fruta se hace el guacamole?", listOf("Mango","Kiwi","Aguacate","Plátano"), 2),
        Pregunta("¿Qué especia da color amarillo a la paella valenciana?", listOf("Cúrcuma","Pimentón","Azafrán","Curry"), 2),
        Pregunta("¿De qué país es el croissant?", listOf("Francia","Austria","Bélgica","Suiza"), 1),
        Pregunta("¿En qué país se originó el chocolate?", listOf("España","Bélgica","México","Suiza"), 2),
        Pregunta("¿Qué significa 'Wi-Fi'?", listOf("Wireless Fidelity","Wire First","Wide Finder","Wireless Field"), 0),
        Pregunta("¿En qué año se creó Internet (ARPANET)?", listOf("1959","1969","1979","1989"), 1),
        Pregunta("¿Quién fundó Apple junto a Steve Jobs?", listOf("Bill Gates","Steve Wozniak","Larry Page","Elon Musk"), 1),
        Pregunta("¿Qué significa 'CPU'?", listOf("Central Power Unit","Computer Processing Unit","Central Processing Unit","Computer Power Unit"), 2),
        Pregunta("¿Cuántos bits tiene un byte?", listOf("4","8","16","32"), 1),
        Pregunta("¿En qué año se fundó Google?", listOf("1996","1998","2000","2001"), 1),
        Pregunta("¿Qué empresa creó el sistema Android?", listOf("Apple","Samsung","Google","Microsoft"), 2),
        Pregunta("¿Qué significa HTML?", listOf("Hyper Text Markup Language","High Text Markup Language","Hyper Tool Markup Language","Hyper Text Model Language"), 0),
        Pregunta("¿Cuál es la comunidad autónoma más grande de España?", listOf("Castilla y León","Andalucía","Castilla-La Mancha","Aragón"), 0),
        Pregunta("¿Cuántas comunidades autónomas tiene España?", listOf("15","17","19","21"), 1),
        Pregunta("¿Cuál es el río más largo de España?", listOf("Ebro","Tajo","Guadalquivir","Duero"), 1),
        Pregunta("¿En qué año ganó España su primer Mundial de fútbol?", listOf("2006","2008","2010","2012"), 2),
        Pregunta("¿Quién pintó Las Meninas?", listOf("Goya","El Greco","Murillo","Velázquez"), 3),
        Pregunta("¿Cuál es la ciudad más poblada de España?", listOf("Barcelona","Valencia","Sevilla","Madrid"), 3),
        Pregunta("¿En qué provincia española está la Alhambra?", listOf("Sevilla","Córdoba","Granada","Málaga"), 2),
        Pregunta("¿Cuántas islas tiene el archipiélago canario?", listOf("6","7","8","9"), 1),
        Pregunta("¿En qué ciudad española está la Torre del Oro?", listOf("Córdoba","Málaga","Sevilla","Cádiz"), 2),
        Pregunta("¿Cuántos continentes hay en la Tierra?", listOf("5","6","7","8"), 2),
        Pregunta("¿Qué país tiene más habitantes en el mundo actualmente?", listOf("India","China","EEUU","Indonesia"), 0),
        Pregunta("¿Cuántas cuerdas tiene una guitarra estándar?", listOf("4","5","6","7"), 2),
        Pregunta("¿Cuántos segundos tiene una hora?", listOf("3.000","3.600","4.200","3.200"), 1),
        Pregunta("¿A qué temperatura hierve el agua al nivel del mar?", listOf("90°C","95°C","100°C","105°C"), 2),
        Pregunta("¿Cuántos planetas tiene el sistema solar?", listOf("7","8","9","10"), 1),
        Pregunta("¿Cuánto tarda la luz del sol en llegar a la Tierra?", listOf("3 minutos","5 minutos","8 minutos","12 minutos"), 2),
        Pregunta("¿Cuántas notas musicales hay en el sistema occidental?", listOf("5","7","10","12"), 1),
        Pregunta("¿Cuántos días tiene un año bisiesto?", listOf("364","365","366","367"), 2),
        Pregunta("¿Cuál es el idioma más hablado del mundo?", listOf("Inglés","Español","Mandarín","Hindi"), 2),
        Pregunta("¿Cuántos estados tiene EEUU?", listOf("48","49","50","52"), 2),
        Pregunta("¿Cuántas estrellas tiene la bandera de la UE?", listOf("10","12","15","28"), 1),
        Pregunta("¿Cuál es el animal más venenoso del mundo?", listOf("Cobra real","Medusa caja","Taipán","Araña viuda negra"), 1),
        Pregunta("¿Cuántos tentáculos tiene un pulpo?", listOf("6","7","8","10"), 2),
        Pregunta("¿Cuánto dura el embarazo de un elefante?", listOf("12 meses","16 meses","22 meses","24 meses"), 2),
    )

    var preguntas    by remember { mutableStateOf(poolPreguntas.shuffled().take(15)) }
    var indice       by remember { mutableStateOf(0) }
    var puntuacion   by remember { mutableStateOf(0) }
    var seleccionada by remember { mutableStateOf(-1) }
    var fase         by remember { mutableStateOf("jugando") }
    var shakeTrigger by remember { mutableStateOf(false) }
    var tiempoRestante by remember { mutableStateOf(7) }

    val pregunta = if (indice < preguntas.size) preguntas[indice] else null

    // ─── FIX TIMER: key solo = indice, sin seleccionada ──────────────────────
    // Al cambiar indice el efecto se reinicia. Dentro del loop leemos
    // seleccionada como state sin que sea key → no se relanza al responder.
    LaunchedEffect(indice) {
        if (fase != "jugando") return@LaunchedEffect
        tiempoRestante = 7
        while (tiempoRestante > 0) {
            delay(1000L)
            if (seleccionada != -1) return@LaunchedEffect  // respondió manualmente → parar
            tiempoRestante--
        }
        // Tiempo agotado sin respuesta
        if (seleccionada == -1 && fase == "jugando") {
            seleccionada = -2
            com.example.pregameapp.ui.SensoryFeedback.vibrateError(context)
            delay(1400L)
            seleccionada = -1
            shakeTrigger = false
            if (indice < preguntas.size - 1) indice++ else fase = "resultado"
        }
    }

    fun siguientePregunta() {
        if (seleccionada == pregunta?.correcta) puntuacion++
        seleccionada = -1
        shakeTrigger = false
        tiempoRestante = 7
        if (indice < preguntas.size - 1) indice++ else fase = "resultado"
    }

    fun reiniciar() {
        preguntas      = poolPreguntas.shuffled().take(15)
        indice         = 0
        puntuacion     = 0
        seleccionada   = -1
        shakeTrigger   = false
        tiempoRestante = 7
        fase           = "jugando"
    }

    val infiniteTransition = rememberInfiniteTransition(label = "bg")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF0A0A1A), Color(0xFF0D1B2A), Color(0xFF0A0A1A))
                )
            )
    ) {
        // Luces de fondo decorativas
        Box(
            modifier = Modifier
                .size(360.dp)
                .offset(x = (-100).dp, y = (-80).dp)
                .background(
                    Brush.radialGradient(listOf(BlueVibrant.copy(alpha = 0.10f), Color.Transparent)),
                    CircleShape
                )
        )
        Box(
            modifier = Modifier
                .size(300.dp)
                .align(Alignment.BottomEnd)
                .offset(x = 80.dp, y = 80.dp)
                .background(
                    Brush.radialGradient(listOf(GoldAccent.copy(alpha = 0.07f), Color.Transparent)),
                    CircleShape
                )
        )

        Column(modifier = Modifier.fillMaxSize().statusBarsPadding()) {

            // HEADER
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBack,
                    modifier = Modifier.size(40.dp).clip(CircleShape).background(SurfaceCard)
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, "Volver", tint = Color.White)
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text("CONCURSO", color = GoldAccent, fontSize = 10.sp, fontWeight = FontWeight.ExtraBold, letterSpacing = 3.sp)
                    Text("Quiz Time", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.ExtraBold)
                }
                Spacer(modifier = Modifier.weight(1f))
                AnimatedVisibility(visible = fase == "jugando") {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(Brush.horizontalGradient(listOf(BlueDeep, BlueVibrant)))
                            .padding(horizontal = 14.dp, vertical = 8.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("★", color = GoldAccent, fontSize = 13.sp)
                            Spacer(Modifier.width(4.dp))
                            Text("$puntuacion pts", color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 15.sp)
                        }
                    }
                }
            }

            when (fase) {
                "jugando" -> {
                    pregunta?.let { p ->
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState())
                                .padding(horizontal = 20.dp)
                        ) {
                            // Barra de progreso global
                            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                                Text("${indice + 1} / ${preguntas.size}", color = Color.White.copy(alpha = 0.45f), fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                                Spacer(Modifier.width(8.dp))
                                Box(
                                    modifier = Modifier.weight(1f).height(5.dp).clip(RoundedCornerShape(3.dp)).background(Color.White.copy(alpha = 0.08f))
                                ) {
                                    Box(
                                        modifier = Modifier.fillMaxHeight()
                                            .fillMaxWidth((indice + 1).toFloat() / preguntas.size)
                                            .clip(RoundedCornerShape(3.dp))
                                            .background(Brush.horizontalGradient(listOf(BlueVibrant, GoldAccent)))
                                    )
                                }
                            }

                            Spacer(Modifier.height(16.dp))

                            // Timer
                            val timerFraction = tiempoRestante / 7f
                            val timerColor = when {
                                tiempoRestante <= 2 -> RedWrong
                                tiempoRestante <= 4 -> TimerOrange
                                else                -> GreenRight
                            }
                            val timerPulse by infiniteTransition.animateFloat(
                                initialValue = 1f,
                                targetValue = if (tiempoRestante <= 3) 1.10f else 1f,
                                animationSpec = infiniteRepeatable(tween(350), RepeatMode.Reverse),
                                label = "pulse"
                            )

                            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier.weight(1f).height(8.dp).clip(RoundedCornerShape(4.dp)).background(Color.White.copy(alpha = 0.07f))
                                ) {
                                    Box(
                                        modifier = Modifier.fillMaxHeight()
                                            .fillMaxWidth(timerFraction)
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(timerColor)
                                    )
                                }
                                Spacer(Modifier.width(12.dp))
                                Box(
                                    modifier = Modifier
                                        .graphicsLayer { scaleX = timerPulse; scaleY = timerPulse }
                                        .size(46.dp)
                                        .clip(CircleShape)
                                        .background(timerColor.copy(alpha = 0.15f))
                                        .border(2.dp, timerColor.copy(alpha = 0.6f), CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("$tiempoRestante", color = timerColor, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp)
                                }
                            }

                            Spacer(Modifier.height(20.dp))

                            // Tarjeta pregunta
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .shadow(16.dp, RoundedCornerShape(24.dp), ambientColor = BlueVibrant.copy(alpha = 0.25f), spotColor = BlueVibrant.copy(alpha = 0.25f))
                                    .clip(RoundedCornerShape(24.dp))
                                    .background(Brush.linearGradient(listOf(Color(0xFF1A3358), Color(0xFF112040))))
                                    .border(1.dp, Brush.linearGradient(listOf(BlueVibrant.copy(alpha = 0.5f), Color.Transparent)), RoundedCornerShape(24.dp))
                                    .padding(24.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text("PREGUNTA ${indice + 1}", color = BlueVibrant.copy(alpha = 0.75f), fontSize = 10.sp, fontWeight = FontWeight.ExtraBold, letterSpacing = 2.sp)
                                    Spacer(Modifier.height(10.dp))
                                    Text(
                                        p.enunciado,
                                        color = Color.White,
                                        fontSize = 19.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center,
                                        lineHeight = 28.sp,
                                        style = TextStyle(shadow = Shadow(BlueVibrant.copy(alpha = 0.35f), Offset(0f,2f), 8f))
                                    )
                                }
                            }

                            Spacer(Modifier.height(22.dp))

                            // Opciones
                            val letras = listOf("A","B","C","D")
                            val letraColors = listOf(Color(0xFF6366F1), Color(0xFFF59E0B), Color(0xFF10B981), Color(0xFFEC4899))

                            p.opciones.forEachIndexed { i, opcion ->
                                val esCorrecto  = i == p.correcta
                                val esSeleccion = i == seleccionada
                                val mostrar     = seleccionada != -1 && seleccionada != -2

                                val bgColor = when {
                                    seleccionada == -2 && esCorrecto -> GreenRight.copy(alpha = 0.22f)
                                    seleccionada == -2               -> SurfaceOption
                                    mostrar && esCorrecto            -> GreenRight.copy(alpha = 0.22f)
                                    mostrar && esSeleccion           -> RedWrong.copy(alpha = 0.22f)
                                    esSeleccion                      -> BlueVibrant.copy(alpha = 0.18f)
                                    else                             -> SurfaceOption
                                }
                                val borderColor = when {
                                    seleccionada == -2 && esCorrecto -> GreenRight
                                    mostrar && esCorrecto            -> GreenRight
                                    mostrar && esSeleccion           -> RedWrong
                                    esSeleccion                      -> BlueVibrant
                                    else                             -> Color.Transparent
                                }
                                val indicatorColor = when {
                                    (seleccionada == -2 && esCorrecto) || (mostrar && esCorrecto) -> GreenRight
                                    mostrar && esSeleccion -> RedWrong
                                    else -> letraColors[i]
                                }

                                Spacer(Modifier.height(8.dp))

                                Button(
                                    onClick = {
                                        if (seleccionada == -1) {
                                            seleccionada = i
                                            if (i == p.correcta) {
                                                com.example.pregameapp.ui.SensoryFeedback.vibrateSuccess(context)
                                            } else {
                                                shakeTrigger = !shakeTrigger
                                                com.example.pregameapp.ui.SensoryFeedback.vibrateError(context)
                                            }
                                        }
                                    },
                                    modifier = Modifier.fillMaxWidth().height(62.dp)
                                        .then(if (mostrar && esSeleccion && !esCorrecto) Modifier.shakeFeedback(shakeTrigger) else Modifier),
                                    shape = RoundedCornerShape(16.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = bgColor, disabledContainerColor = bgColor),
                                    border = androidx.compose.foundation.BorderStroke(
                                        if (borderColor == Color.Transparent) 1.dp else 2.dp,
                                        if (borderColor == Color.Transparent) Color.White.copy(alpha = 0.07f) else borderColor
                                    ),
                                    elevation = ButtonDefaults.buttonElevation(0.dp)
                                ) {
                                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                                        Box(
                                            modifier = Modifier.size(32.dp).clip(RoundedCornerShape(8.dp)).background(indicatorColor.copy(alpha = 0.2f)),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(letras[i], color = indicatorColor, fontWeight = FontWeight.ExtraBold, fontSize = 14.sp)
                                        }
                                        Spacer(Modifier.width(14.dp))
                                        Text(opcion, color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 15.sp, modifier = Modifier.weight(1f))
                                        if ((mostrar || seleccionada == -2) && esCorrecto) {
                                            Box(
                                                modifier = Modifier.size(28.dp).clip(CircleShape).background(GreenRight),
                                                contentAlignment = Alignment.Center
                                            ) { Text("✓", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.ExtraBold) }
                                        } else if (mostrar && esSeleccion && !esCorrecto) {
                                            Box(
                                                modifier = Modifier.size(28.dp).clip(CircleShape).background(RedWrong),
                                                contentAlignment = Alignment.Center
                                            ) { Text("✗", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.ExtraBold) }
                                        }
                                    }
                                }
                            }

                            Spacer(Modifier.height(18.dp))

                            // Feedback + botón siguiente
                            AnimatedVisibility(
                                visible = seleccionada != -1 && seleccionada != -2,
                                enter = slideInVertically { it / 2 } + fadeIn(),
                                exit  = slideOutVertically { it / 2 } + fadeOut()
                            ) {
                                Column {
                                    val esCorrecto = seleccionada == p.correcta
                                    Row(
                                        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp))
                                            .background(if (esCorrecto) GreenRight.copy(alpha = 0.10f) else RedWrong.copy(alpha = 0.10f))
                                            .padding(12.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Box(
                                            modifier = Modifier.size(36.dp).clip(CircleShape).background(if (esCorrecto) GreenRight else RedWrong),
                                            contentAlignment = Alignment.Center
                                        ) { Text(if (esCorrecto) "✓" else "✗", color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp) }
                                        Spacer(Modifier.width(12.dp))
                                        Text(
                                            if (esCorrecto) "¡Correcto! +1 punto" else "Incorrecto",
                                            color = if (esCorrecto) GreenRight else RedWrong,
                                            fontWeight = FontWeight.Bold, fontSize = 15.sp
                                        )
                                    }
                                    Spacer(Modifier.height(12.dp))
                                    Button(
                                        onClick = { siguientePregunta() },
                                        modifier = Modifier.fillMaxWidth().height(52.dp),
                                        shape = RoundedCornerShape(16.dp),
                                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                                        border = androidx.compose.foundation.BorderStroke(0.dp, Color.Transparent),
                                        elevation = ButtonDefaults.buttonElevation(0.dp),
                                        contentPadding = PaddingValues(0.dp)
                                    ) {
                                        Box(
                                            modifier = Modifier.fillMaxSize().background(Brush.horizontalGradient(listOf(BlueDeep, BlueVibrant)), RoundedCornerShape(16.dp)),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Text(if (indice < preguntas.size - 1) "Siguiente pregunta" else "Ver resultado", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                                                Spacer(Modifier.width(8.dp))
                                                Text("→", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
                                            }
                                        }
                                    }
                                }
                            }

                            // Tiempo agotado
                            AnimatedVisibility(visible = seleccionada == -2, enter = fadeIn(), exit = fadeOut()) {
                                Row(
                                    modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp))
                                        .background(RedWrong.copy(alpha = 0.10f)).padding(14.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier.size(36.dp).clip(CircleShape).background(RedWrong),
                                        contentAlignment = Alignment.Center
                                    ) { Text("!", color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 18.sp) }
                                    Spacer(Modifier.width(12.dp))
                                    Column {
                                        Text("Tiempo agotado", color = RedWrong, fontWeight = FontWeight.ExtraBold, fontSize = 14.sp)
                                        Text("Pasando a la siguiente...", color = Color.White.copy(alpha = 0.45f), fontSize = 12.sp)
                                    }
                                }
                            }

                            Spacer(Modifier.height(32.dp))
                        }
                    }
                }

                "resultado" -> {
                    val pct = puntuacion.toFloat() / preguntas.size
                    val (rankLabel, rankColor, rankDesc) = when {
                        pct >= 0.9f -> Triple("GENIO", GoldAccent, "Resultado excepcional")
                        pct >= 0.7f -> Triple("EXPERTO", BlueVibrant, "Muy por encima de la media")
                        pct >= 0.5f -> Triple("NOTABLE", GreenRight, "Buen nivel de conocimiento")
                        pct >= 0.3f -> Triple("APRENDIZ", TimerOrange, "Sigue practicando")
                        else        -> Triple("NOVATO", RedWrong, "Hay margen de mejora")
                    }

                    Column(
                        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(Modifier.height(20.dp))

                        // Badge de rango
                        Box(
                            modifier = Modifier.clip(RoundedCornerShape(16.dp))
                                .background(rankColor.copy(alpha = 0.13f))
                                .border(2.dp, rankColor.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
                                .padding(horizontal = 24.dp, vertical = 10.dp)
                        ) {
                            Text(rankLabel, color = rankColor, fontSize = 13.sp, fontWeight = FontWeight.ExtraBold, letterSpacing = 3.sp)
                        }

                        Spacer(Modifier.height(28.dp))

                        Text(
                            "$puntuacion",
                            color = Color.White,
                            fontSize = 88.sp,
                            fontWeight = FontWeight.ExtraBold,
                            style = TextStyle(shadow = Shadow(rankColor.copy(alpha = 0.55f), Offset(0f,4f), 20f))
                        )
                        Text("de ${preguntas.size} correctas", color = Color.White.copy(alpha = 0.45f), fontSize = 16.sp)

                        Spacer(Modifier.height(10.dp))
                        Text(rankDesc, color = Color.White.copy(alpha = 0.65f), fontSize = 15.sp, textAlign = TextAlign.Center)

                        Spacer(Modifier.height(28.dp))

                        Box(
                            modifier = Modifier.fillMaxWidth().height(12.dp).clip(RoundedCornerShape(6.dp)).background(Color.White.copy(alpha = 0.07f))
                        ) {
                            Box(
                                modifier = Modifier.fillMaxHeight().fillMaxWidth(pct).clip(RoundedCornerShape(6.dp))
                                    .background(Brush.horizontalGradient(listOf(BlueVibrant, rankColor)))
                            )
                        }
                        Spacer(Modifier.height(6.dp))
                        Text("${(pct * 100).toInt()}% de acierto", color = rankColor, fontSize = 13.sp, fontWeight = FontWeight.Bold)

                        Spacer(Modifier.height(44.dp))

                        Button(
                            onClick = { reiniciar() },
                            modifier = Modifier.fillMaxWidth().height(56.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                            elevation = ButtonDefaults.buttonElevation(0.dp),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize().background(Brush.horizontalGradient(listOf(BlueDeep, BlueVibrant)), RoundedCornerShape(16.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text("↺", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.ExtraBold)
                                    Spacer(Modifier.width(8.dp))
                                    Text("Jugar de nuevo", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                }
                            }
                        }

                        Spacer(Modifier.height(12.dp))

                        Button(
                            onClick = onBack,
                            modifier = Modifier.fillMaxWidth().height(52.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = SurfaceCard, contentColor = Color.White.copy(alpha = 0.55f)),
                            elevation = ButtonDefaults.buttonElevation(0.dp)
                        ) {
                            Text("Volver al menú", fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
                        }

                        Spacer(Modifier.height(24.dp))
                    }
                }
            }
        }
    }
}
