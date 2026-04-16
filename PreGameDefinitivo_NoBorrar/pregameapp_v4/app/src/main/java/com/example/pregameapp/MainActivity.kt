package com.example.pregameapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pregameapp.data.SessionManager
import com.example.pregameapp.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val session = SessionManager(this)
        if (!session.isLoggedIn()) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        setContent {
            PreGameTheme {
                MainScreen(
                    username = session.getUsername(),
                    onLogout = {
                        session.clearSession()
                        startActivity(Intent(this, LoginActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        })
                        finish()
                    },
                    onImpostor   = { startActivity(Intent(this, ImpostorActivity::class.java)) },
                    onTruthDare  = { startActivity(Intent(this, TruthDareActivity::class.java)) },
                    onConcurso   = { startActivity(Intent(this, ConcursoActivity::class.java)) },
                    onMimica     = { startActivity(Intent(this, MimicaActivity::class.java)) },
                    onCustom     = { startActivity(Intent(this, CustomQuestionsActivity::class.java)) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    username: String,
    onLogout: () -> Unit,
    onImpostor: () -> Unit,
    onTruthDare: () -> Unit,
    onConcurso: () -> Unit,
    onMimica: () -> Unit,
    onCustom: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlack)
    ) {
        // Fondo decorativo
        Box(
            modifier = Modifier
                .size(500.dp)
                .offset(x = (-100).dp, y = (-100).dp)
                .background(
                    Brush.radialGradient(
                        colors = listOf(NeonPurple.copy(alpha = 0.12f), Color.Transparent)
                    ),
                    shape = RoundedCornerShape(50)
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            // TopBar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        "¡Hola, $username! 👋",
                        color = TextPrimary,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text("¿A qué jugamos hoy?", color = TextMuted, fontSize = 13.sp)
                }
                IconButton(
                    onClick = onLogout,
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = CardDark
                    )
                ) {
                    Icon(Icons.Default.ExitToApp, "Cerrar sesión", tint = TextMuted)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Hero banner
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                color = Color.Transparent
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(
                                    NeonPurple.copy(alpha = 0.8f),
                                    NeonViolet.copy(alpha = 0.6f)
                                )
                            ),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(24.dp)
                ) {
                    Column {
                        Text("🎮 PreGame", color = Color.White.copy(alpha = 0.8f), fontSize = 13.sp)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            "El hub definitivo\nde juegos de grupo",
                            color = Color.White,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold,
                            lineHeight = 28.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "4 juegos · Diversión garantizada",
                            color = Color.White.copy(alpha = 0.7f),
                            fontSize = 12.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                "JUEGOS",
                color = TextMuted,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Grid de juegos 2x2
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                GameCard(
                    modifier     = Modifier.weight(1f),
                    emoji        = "🕵️",
                    title        = "Impostor",
                    description  = "¿Quién es el\nimpostor?",
                    gradient     = listOf(ImpostorRed, Color(0xFF991B1B)),
                    drawableRes  = R.drawable.game_impostor,
                    onClick      = onImpostor
                )
                GameCard(
                    modifier     = Modifier.weight(1f),
                    emoji        = "🎭",
                    title        = "Verdad o Reto",
                    description  = "¿Verdad o te\natreves?",
                    gradient     = listOf(TruthGreen, Color(0xFF166534)),
                    drawableRes  = R.drawable.game_truth_dare,
                    onClick      = onTruthDare
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                GameCard(
                    modifier     = Modifier.weight(1f),
                    emoji        = "🧠",
                    title        = "Concurso",
                    description  = "Pon a prueba\ntu cultura",
                    gradient     = listOf(ConcursoBlue, Color(0xFF1E40AF)),
                    drawableRes  = R.drawable.game_concurso,
                    onClick      = onConcurso
                )
                GameCard(
                    modifier     = Modifier.weight(1f),
                    emoji        = "🎬",
                    title        = "Mímica",
                    description  = "Actúa sin\nhablar",
                    gradient     = listOf(MimicaOrange, Color(0xFF9A3412)),
                    drawableRes  = R.drawable.game_mimica,
                    onClick      = onMimica
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Botón de Personalización
            Surface(
                onClick = onCustom,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = CardDark
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(NeonPurple.copy(alpha = 0.2f), RoundedCornerShape(10.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("⚙️", fontSize = 20.sp)
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text("Personalizar Preguntas", color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                        Text("Añade tus propias verdades y retos", color = TextMuted, fontSize = 12.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun GameCard(
    modifier: Modifier = Modifier,
    emoji: String,
    title: String,
    description: String,
    gradient: List<Color>,
    @DrawableRes drawableRes: Int,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = modifier.height(160.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(colors = gradient),
                    shape = RoundedCornerShape(20.dp)
                )
        ) {
            // Imagen decorativa en esquina superior derecha
            Image(
                painter = painterResource(id = drawableRes),
                contentDescription = null,
                modifier = Modifier
                    .size(90.dp)
                    .align(Alignment.TopEnd)
                    .offset(x = 10.dp, y = (-4).dp),
                contentScale = ContentScale.Fit
            )
            // Texto sobre la imagen
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(emoji, fontSize = 32.sp)
                Column {
                    Text(
                        title,
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Text(
                        description,
                        color = Color.White.copy(alpha = 0.75f),
                        fontSize = 11.sp,
                        lineHeight = 15.sp
                    )
                }
            }
        }
    }
}
