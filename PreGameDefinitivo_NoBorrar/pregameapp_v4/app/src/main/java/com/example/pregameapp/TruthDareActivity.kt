package com.example.pregameapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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

class TruthDareActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PreGameTheme {
                TruthDareScreen(onBack = { finish() })
            }
        }
    }
}

@Composable
fun TruthDareScreen(onBack: () -> Unit) {

    val verdades = listOf(
        "¿Cuál es tu mayor vergüenza?",
        "¿A quién de aquí le tienes más manía?",
        "¿Cuál es tu secreto más grande?",
        "¿Cuál fue la mentira más grande que has dicho?",
        "¿Tienes algún crush en este grupo?",
        "¿Qué es lo más raro que has comido?",
        "¿Qué es lo peor que has hecho en una fiesta?",
        "¿Has fingido alguna vez estar enfermo para no ir a algo?",
        "¿Cuál es tu peor hábito?",
        "¿Alguna vez has tenido un sueño con alguien de aquí?",
        "¿Cuál ha sido tu peor cita?",
        "¿Qué canción es tu culpa placer favorita?",
        "¿Has robado algo alguna vez, aunque sea pequeño?",
        "¿Cuál es tu mayor miedo?",
        "¿A quién de tus amigos le pedirías consejo sobre amor?",
        "¿Cuál ha sido el momento más embarazoso de tu vida?",
        "¿Has enviado alguna vez un mensaje a la persona equivocada?",
        "¿Qué es lo que más envidia te da de alguien de aquí?",
        "¿Has mentido en una entrevista de trabajo o examen?",
        "¿Cuál es la cosa más rara que has buscado en Google?",
        "¿Tienes algún mote que te dé vergüenza?",
        "¿Qué haría tu yo de 10 años si te viera ahora?",
        "¿Has llorado viendo una peli de dibujos animados?",
        "¿Cuál es el peor regalo que has recibido?",
        "Si pudieras cambiar una cosa de ti mismo, ¿qué sería?",
        "¿Has bloqueado a alguien y luego desbloqueado en secreto?",
        "¿Cuál es la cosa más estúpida que has hecho por amor?",
        "¿Cuándo fue la última vez que lloraste y por qué?",
        "¿Tienes algún pensamiento que nunca contarías a nadie?",
        "¿Qué parte de tu personalidad intentas esconder?",
        "¿Has hecho algo ilegal aunque sea pequeño?",
        "¿Cuál es tu mayor arrepentimiento hasta ahora?",
        "¿Si pudieras leer la mente de alguien de aquí, a quién elegirías?",
        "¿Cuál es el mayor malentendido que has tenido con alguien?",
        "¿Cuánto tiempo tardas en responder mensajes si no quieres hablar?",
        "¿Has fingido que no habías visto un mensaje?",
        "¿Qué es lo peor que has dicho de alguien a sus espaldas?",
        "¿Cuál es la cosa más valiente que has hecho?",
        "¿Tienes algún trauma que hayas superado?",
        "¿En qué mientes más, en las redes o en persona?"
    )

    val retos = listOf(
        "Imita a alguien de este grupo durante 1 minuto",
        "Llama a alguien y cántale cumpleaños feliz",
        "Haz 20 sentadillas ahora mismo",
        "Habla con acento extranjero durante 5 minutos",
        "Escribe un poema sobre la persona a tu izquierda",
        "Actúa como un robot hasta el próximo turno",
        "Haz el baile más ridículo que sepas",
        "Deja que el grupo escriba algo en tu frente con rotulador",
        "Haz 10 flexiones ahora mismo",
        "Manda un mensaje raro a la última persona que has llamado",
        "Imita a un animal y que el grupo adivine cuál",
        "Di un chiste malísimo que hagas reír a alguien",
        "Habla sin parar durante 30 segundos de calcetines",
        "Deja que el grupo te peina como quieran",
        "Llama a alguien y dile que has visto un OVNI",
        "Haz una foto ridícula y ponla de perfil 10 minutos",
        "Habla en susurros durante los próximos 3 turnos",
        "Describe el último sueño que recuerdes con pelos y señales",
        "Haz una reverencia cada vez que alguien te hable hasta el próximo turno",
        "Baila sin música durante 30 segundos",
        "Imita al presentador de un telediario durante 1 minuto",
        "Escribe tu nombre con el codo en el aire",
        "Cuenta hasta 20 en otro idioma (el que sea)",
        "Actúa como si fueras el protagonista de un anuncio de perfume",
        "Haz la voz de tu personaje favorito de dibujos animados",
        "Pon el temporizador a 60 segundos y no pares de bailar",
        "Deja que el grupo elija tu foto de perfil durante 30 minutos",
        "Di 5 cosas positivas de la persona a tu derecha",
        "Habla como Yoda durante 2 turnos",
        "Come algo que te dé asco del frigorifico (si lo hay)"
    )

    val verdadesPicantes = listOf(
        "¿Cuál es el lugar más raro donde has tenido o has pensado tener un encuentro romántico?",
        "¿Cuántas personas has besado y cuántas de ellas merecía la pena?",
        "¿Has mandado alguna vez una foto o mensaje comprometido a quien no debías?",
        "¿Con quién de este grupo te irías si pudieras? Sé honesto/a.",
        "¿Qué es lo más atrevido que has hecho en una noche de fiesta?",
        "¿Has tenido alguna vez sentimientos por alguien que tenía pareja?",
        "¿Cuál es el mayor secreto de tu vida amorosa que nadie sabe?",
        "¿Qué aplicación de citas tienes o has tenido instalada?",
        "¿Has ligado borracho/a con alguien y al día siguiente te has arrepentido?",
        "¿Cuál es el cuerpo de famoso/a que más te atrae?",
        "¿Has hecho alguna vez ghosting y por qué?",
        "¿Qué es lo más atrevido que has escrito en un chat?",
        "¿Cuánto tiempo ha sido la relación más larga que has tenido y por qué terminó?",
        "¿Has sentido alguna vez algo por un amigo/a del novio/novia de alguien de aquí?",
        "¿Cuál es el piropo más atrevido que has recibido o dado?",
        "¿Has besado a más de una persona la misma noche?",
        "¿Cuál es la fantasía más loca que has tenido?",
        "¿Tienes o has tenido un amigo/a con derecho a roce?",
        "¿Cuál es el sitio más atrevido donde has ligado?",
        "¿Has fingido alguna vez que te gustaba alguien para sacar algo?",
        "¿Has tenido algún flechazo con alguien de aquí?",
        "¿Qué tipo de persona te atrae y no lo has contado nunca?",
        "¿Cuánto tardas en decidir si alguien te gusta físicamente?",
        "¿Has mirado el móvil de tu pareja o expareja sin permiso?",
        "¿Cuál ha sido tu mayor decepción en el plano romántico?",
        "Si pudieras pasar una semana con alguien famoso/a, ¿a quién elegirías y por qué?",
        "¿Qué es lo más vergonzoso que te ha pasado ligando?",
        "¿Has actuado de forma celosa aunque no tuvieras pareja?",
        "¿Cuál es la cosa más atrevida que has hecho en un coche?",
        "¿Has enviado fotos comprometidas y te has arrepentido?",
        "¿Cuántas personas te gustan a la vez en este momento?",
        "¿Cuál es la mayor edad de diferencia que has tenido con alguien con quien has salido?",
        "¿Has puesto mote a alguien con quien has ligado? ¿Cuál?",
        "¿Cuál es la conversación de WhatsApp más comprometida que tienes ahora mismo?",
        "¿Has liado algo que todavía no has contado a nadie de aquí?",
        "¿Has intentado ligar con alguien y que te hayan dejado en visto?",
        "¿Cuál es el cumplido más atrevido que te han hecho?",
        "¿Qué parte del cuerpo es la que más te fijas en una persona que te gusta?",
        "¿Has tenido una aventura de verano o de viaje que nadie sepa?",
        "¿Qué es lo que nunca harías con tu pareja pero sí lo pensarías?",
        "¿Tienes a alguien en la mente ahora mismo que sea de este grupo?",
        "¿Cuál es el mayor malentendido romántico que has tenido?",
        "¿Has usado a alguien para olvidarte de otra persona?",
        "¿Cuál es el primer pensamiento que tienes cuando alguien nuevo te gusta?",
        "¿Qué red social usas para cotillear exes?",
        "¿Has tenido o tendrías una cita a ciegas?",
        "¿Cuál es la cosa más escandalosa que has visto en una fiesta ajena?",
        "Si la persona que más te gusta te leyera la mente ahora, ¿qué vería?",
        "¿Cuál es la canción que más asocias a alguien con quien hayas tenido algo?",
        "¿Has confesado tus sentimientos a alguien y salido mal? Cuéntalo.",
        "¿Qué harías si tu ex te escribiera ahora mismo?",
        "¿Has fingido orgasmo o entusiasmo romántico?",
        "¿Cuál es el plan de seducción más elaborado que has tenido?",
        "¿Has tenido pareja oficial y alguien por detrás al mismo tiempo?",
        "¿Con quién tienes más química de este grupo aunque no lo reconozcas?",
        "¿Cuál es la cosa más atrevida que has propuesto o te han propuesto?",
        "¿Qué parte de tu cuerpo es la que más te gusta enseñar?",
        "¿Tienes alguna historia de una noche que nadie sepa?",
        "¿Cuál es el emoji más comprometido que has enviado y a quién?",
        "¿Con qué personaje de serie o película te casarías y con cuál pasarías la noche?",
        "¿Has llegado tarde a algo importante por culpa de estar con alguien?",
        "¿Cuál es la primera persona que se te viene a la cabeza cuando piensas en besar a alguien de este grupo?",
        "¿Qué es lo que nunca has hecho en el plano romántico pero tienes muchas ganas?",
        "¿Cuándo fue la última vez que alguien te puso nervioso/a y por qué?",
        "¿Cuál ha sido la noche más loca de tu vida? (Sin censura)",
        "¿Qué tipo de mensaje nunca borrarías aunque deberías?",
        "¿Con quién de aquí podrías imaginarte algo romántico si no os conocierais?",
        "¿Cuál es el lugar más inusual donde has dado o recibido un beso?",
        "¿Qué es lo primero que miras en alguien que te atrae?",
        "¿Tienes alguna historia con alguien famoso o VIP? Cuéntala.",
        "¿Has hecho algo atrevido en público que te arrepienta o te enorgullezca?",
        "¿Cuál es la aventura más corta que has tenido y qué recuerdas de ella?",
        "¿Qué es lo que más te cuesta confesar a una pareja?",
        "¿Cuál es el plan de fin de semana más picante que has tenido?",
        "¿Has mentido sobre tu edad para impresionar a alguien?",
        "¿Qué es lo más arriesgado que harías por una persona que te guste?",
        "¿Cuándo fue la última vez que alguien te besó sin esperártelo?",
        "¿Tienes algún tatuaje o piercing en un sitio que nadie sepa?",
        "¿Qué situación cotidiana se convierte en romántica para ti?",
        "¿Has hecho algún striptease, aunque sea de broma?",
        "¿Cuál es la mentira romántica que más te ha costado mantener?",
        "¿Qué harías si descubrieras que alguien de este grupo te ha visto de forma diferente?",
        "¿Cuál es la fantasy más curiosa que no te atreves a contar normalmente?",
        "¿Has tenido una conversación de WhatsApp que si la enseñaran te morirías?",
        "¿Cuál es la cosa más atrevida que has hecho en unas vacaciones?",
        "¿Has tenido un amigo imaginario o una relación online? ¿Cuánto duró?",
        "¿Qué persona de aquí te parece más misteriosa en el plano amoroso?",
        "¿Has rechazado a alguien y luego te has arrepentido?",
        "¿Cuál es el plan nocturno más loco que has hecho sin contárselo a tus padres o pareja?",
        "¿Tienes algún secreto romántico que si lo supieran cambiaría algo?",
        "¿Con cuántas personas del grupo podrías imaginarte dando un beso ahora mismo?",
        "¿Qué frase diría tu ex o ligue más reciente sobre ti?",
        "¿Cuál es la razón más superficial por la que has dejado de gustarle alguien?",
        "¿Has intentado reconquistar a alguien sin que lo supiera?",
        "¿Qué es lo que más te arrepientes de haber hecho en una relación?",
        "¿Cuál es la señal más clara de que alguien te gusta que das sin darte cuenta?",
        "¿Has borrado fotos o conversaciones por si alguien te miraba el móvil?",
        "¿Cuál es el piropo más extraño que has recibido?",
        "¿Qué tipo de mensaje de texto nunca enviarías en sobrio/a pero sí en una copa encima?",
        "¿Cuál es la mayor locura que has hecho espontáneamente por alguien?",
        "¿Hay alguien aquí con quien hayas flirteado sin que los demás lo supieran?",
        "¿Cuál es la cosa más escandalosa que has hecho en un baño de fiesta?",
        "¿Has tenido alguna vez una experiencia en una discoteca que no hayas contado?",
        "¿Qué es lo primero en lo que piensas cuando alguien te gusta en serio?",
        "¿Tienes alguna fotografía comprometida de ti mismo/a guardada?",
        "¿Cuál es la conversación de voz o llamada más vergonzosa que recuerdas?",
        "¿Has salido de una cita con excusa falsa porque no te gustaba?",
        "¿Cuál es el mayor secreto de tu vida que todavía no le has contado a nadie aquí?",
        "¿Qué harías si alguien de este grupo te confesara que le gustas?"
    )

    val retosPicantes = listOf(
        "Haz tu mejor cara de seducción mirando a alguien del grupo durante 10 segundos sin reírte",
        "Pon tu mano en el hombro de la persona que más te atrae del grupo y aguanta 30 segundos",
        "Susurra algo atrevido al oído de la persona a tu derecha",
        "Baila de forma seductora durante 30 segundos con la música que ponga el grupo",
        "Manda un mensaje atrevido a la última persona con quien hayas tenido algo",
        "Describe cómo ligarías con la persona a tu izquierda en 30 segundos",
        "Haz tu imitación más picante de una escena de película romántica",
        "Deja que alguien del grupo te dé un masaje en los hombros durante 1 minuto",
        "Di en voz alta el nombre de la persona de aquí que más te llama la atención",
        "Llama a tu ex y di solo 'te echo de menos' y cuelga",
        "Pon una canción romántica y mira fijamente a alguien del grupo durante 30 segundos",
        "Deja que el grupo vote quién del grupo te ligaría con más éxito y acepta el veredicto",
        "Describe con 3 adjetivos a la persona que consideras más atractiva del grupo (sin decir el nombre)",
        "Haz una propuesta atrevida a la persona a tu izquierda (de ficción, claro)",
        "Confiesa en voz alta cuántas veces has pensado en alguien de este grupo de forma romántica",
        "Imita cómo ligaría la persona a tu derecha",
        "Escribe un mensaje atrevido en el chat del grupo y bórralo antes de 30 segundos",
        "Deja que alguien del grupo desbloquee tu móvil y vea el último WhatsApp enviado",
        "Di en voz alta el nombre de la última persona con quien hayas ligado",
        "Canta el estribillo de una canción romántica mirando a alguien del grupo",
        "Haz como si le estuvieras proponiendo algo atrevido a la persona frente a ti",
        "Deja que el grupo lea tu última historia de Instagram sin poder tapar nada",
        "Pon cara de que te acaban de dar el mejor beso de tu vida durante 20 segundos",
        "Di una cosa que te parezca irresistible de alguien del grupo sin revelar quién",
        "Pon el primer meme guarrete que tengas guardado en el móvil",
        "Actúa como si estuvieras en una escena de película romántica durante 1 minuto",
        "Deja que la persona a tu izquierda lea tu último estado de WhatsApp",
        "Confiesa cuántas personas del grupo te parecen atractivas (número, sin decir nombres)",
        "Haz una reverencia seductora a la persona del grupo que más admiras",
        "Describe en 3 palabras la noche perfecta con alguien especial (sin censura)",
        "Llama a alguien random de tu agenda y dile que eres increíble sin más explicaciones",
        "Pon cara de que acabas de recibir el peor mensaje del mundo y mantenla 20 segundos",
        "Di en voz alta cuál es el rasgo físico que más te atrae de alguien en general",
        "Elige a alguien del grupo y dile algo genuinamente atrevido o romántico que nunca le hayas dicho",
        "Imita cómo reaccionarías si alguien te pidiera salir de forma inesperada"
    )

    val context = LocalContext.current
    val db = remember { com.example.pregameapp.data.UserDbHelper(context) }

    // Modo: "normal" o "picante"
    var modo  by remember { mutableStateOf("normal") }
    var tipo  by remember { mutableStateOf("") }
    var texto by remember { mutableStateOf("") }
    var key   by remember { mutableStateOf(0) }
    var usadasVerdad        by remember { mutableStateOf(setOf<String>()) }
    var usadasReto          by remember { mutableStateOf(setOf<String>()) }
    var usadasVerdadPicante by remember { mutableStateOf(setOf<String>()) }
    var usadasRetoPicante   by remember { mutableStateOf(setOf<String>()) }

    fun sacar(t: String) {
        tipo = t
        val personalizadas = db.getCustomQuestions(t)
        val listaCompleta = if (modo == "picante") {
            if (t == "verdad") verdadesPicantes else retosPicantes
        } else {
            if (t == "verdad") verdades + personalizadas else retos + personalizadas
        }
        val usadas = when {
            modo == "picante" && t == "verdad" -> usadasVerdadPicante
            modo == "picante" && t == "reto"   -> usadasRetoPicante
            t == "verdad"                      -> usadasVerdad
            else                               -> usadasReto
        }
        val disponibles = listaCompleta.filter { it !in usadas }
        val siguiente = if (disponibles.isNotEmpty()) disponibles.random() else {
            when {
                modo == "picante" && t == "verdad" -> usadasVerdadPicante = setOf()
                modo == "picante" && t == "reto"   -> usadasRetoPicante   = setOf()
                t == "verdad"                      -> usadasVerdad        = setOf()
                else                               -> usadasReto          = setOf()
            }
            listaCompleta.random()
        }
        when {
            modo == "picante" && t == "verdad" -> usadasVerdadPicante = usadasVerdadPicante + siguiente
            modo == "picante" && t == "reto"   -> usadasRetoPicante   = usadasRetoPicante + siguiente
            t == "verdad"                      -> usadasVerdad        = usadasVerdad + siguiente
            else                               -> usadasReto          = usadasReto + siguiente
        }
        texto = siguiente
        key++
        com.example.pregameapp.ui.SensoryFeedback.vibrateSuccess(context)
    }

    val esPicante = modo == "picante"

    Box(modifier = Modifier.fillMaxSize().background(DeepBlack)) {
        Column(modifier = Modifier.fillMaxSize().statusBarsPadding()) {

            Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, "Volver", tint = TextPrimary)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text("🎭 Verdad o Reto", color = TextPrimary, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            }

            // Selector de modo
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = { modo = "normal"; texto = ""; tipo = "" },
                    modifier = Modifier.weight(1f).height(42.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (!esPicante) TruthGreen else CardDark,
                        contentColor = if (!esPicante) Color.White else TextMuted
                    )
                ) {
                    Text("😊 Normal", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                }
                Button(
                    onClick = { modo = "picante"; texto = ""; tipo = "" },
                    modifier = Modifier.weight(1f).height(42.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (esPicante) Color(0xFFDC2626) else CardDark,
                        contentColor = if (esPicante) Color.White else TextMuted
                    )
                ) {
                    Text("🌶️ Picante", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                }
            }

            if (esPicante) {
                Spacer(modifier = Modifier.height(8.dp))
                Surface(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                    shape = RoundedCornerShape(10.dp),
                    color = Color(0xFF450A0A)
                ) {
                    Text(
                        "🌶️ Modo picante activado · Solo para adultos",
                        color = Color(0xFFFCA5A5),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            Column(
                modifier = Modifier.fillMaxSize().padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                AnimatedContent(
                    targetState = key,
                    transitionSpec = {
                        (slideInVertically { -it } + fadeIn()) togetherWith (slideOutVertically { it } + fadeOut())
                    }
                ) { _ ->
                    if (texto.isEmpty()) {
                        Box(
                            modifier = Modifier.fillMaxWidth().height(260.dp).background(SurfaceDark, RoundedCornerShape(24.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(if (esPicante) "🌶️" else "🎭", fontSize = 56.sp)
                                Spacer(modifier = Modifier.height(12.dp))
                                Text(
                                    if (esPicante) "Modo Picante\nVerdad o Reto" else "Elige\nVerdad o Reto",
                                    color = TextMuted,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    } else {
                        val esVerdad = tipo == "verdad"
                        val gradColors = if (esPicante) {
                            if (esVerdad) listOf(Color(0xFFDC2626), Color(0xFF7F1D1D))
                            else listOf(Color(0xFF9333EA), Color(0xFF4C1D95))
                        } else {
                            if (esVerdad) listOf(TruthGreen, Color(0xFF166534))
                            else listOf(Color(0xFF7C3AED), Color(0xFF4C1D95))
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(260.dp)
                                .background(
                                    Brush.verticalGradient(gradColors),
                                    shape = RoundedCornerShape(24.dp)
                                )
                                .padding(24.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    (if (esPicante) "🌶️ " else "") + if (esVerdad) "VERDAD" else "RETO",
                                    color = Color.White.copy(alpha = 0.7f),
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 2.sp
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    texto,
                                    color = Color.White,
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    lineHeight = 26.sp
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(36.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Button(
                        onClick = { sacar("verdad") },
                        modifier = Modifier.weight(1f).height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (esPicante) Color(0xFFDC2626) else TruthGreen,
                            contentColor = Color.White
                        )
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(if (esPicante) "🌶️" else "✅", fontSize = 18.sp)
                            Text("Verdad", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        }
                    }

                    Button(
                        onClick = { sacar("reto") },
                        modifier = Modifier.weight(1f).height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (esPicante) Color(0xFF9333EA) else NeonPurple,
                            contentColor = Color.White
                        )
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(if (esPicante) "🔥" else "🔥", fontSize = 18.sp)
                            Text("Reto", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        }
                    }
                }
                // Botón "repetir" eliminado según petición
            }
        }
    }
}
