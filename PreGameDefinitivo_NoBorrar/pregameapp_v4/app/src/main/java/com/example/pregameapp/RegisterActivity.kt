package com.example.pregameapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pregameapp.data.SessionManager
import com.example.pregameapp.data.UserDbHelper
import com.example.pregameapp.ui.theme.*

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PreGameTheme {
                RegisterScreen(
                    onRegisterSuccess = {
                        startActivity(Intent(this, MainActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        })
                        finish()
                    },
                    onBack = { finish() }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(onRegisterSuccess: () -> Unit, onBack: () -> Unit) {
    val context  = LocalContext.current
    val db       = remember { UserDbHelper(context) }
    val session  = remember { SessionManager(context) }

    var username  by remember { mutableStateOf("") }
    var email     by remember { mutableStateOf("") }
    var password  by remember { mutableStateOf("") }
    var confirm   by remember { mutableStateOf("") }
    var showPass  by remember { mutableStateOf(false) }
    var errorMsg  by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlack)
    ) {
        Box(
            modifier = Modifier
                .size(350.dp)
                .align(Alignment.TopEnd)
                .offset(x = 80.dp, y = (-60).dp)
                .background(
                    Brush.radialGradient(
                        colors = listOf(NeonViolet.copy(alpha = 0.2f), Color.Transparent)
                    ),
                    shape = RoundedCornerShape(50)
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 28.dp)
                .padding(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        "Volver",
                        tint = TextPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("🎮", fontSize = 48.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Crear cuenta",
                color = TextPrimary,
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = (-1).sp
            )
            Text(
                "Únete a PreGame y empieza a jugar",
                color = TextMuted,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                color = SurfaceDark,
                tonalElevation = 4.dp
            ) {
                Column(modifier = Modifier.padding(24.dp)) {

                    val fieldColors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor   = NeonPurple,
                        unfocusedBorderColor = BorderDark,
                        focusedLabelColor    = NeonPurple,
                        focusedTextColor     = TextPrimary,
                        unfocusedTextColor   = TextPrimary,
                        cursorColor          = NeonPurple
                    )

                    OutlinedTextField(
                        value = username,
                        onValueChange = { username = it; errorMsg = "" },
                        label = { Text("Nombre de usuario") },
                        leadingIcon = { Icon(Icons.Default.Person, null, tint = NeonPurple) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = fieldColors
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it; errorMsg = "" },
                        label = { Text("Correo electrónico") },
                        leadingIcon = { Icon(Icons.Default.Email, null, tint = NeonPurple) },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = fieldColors
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it; errorMsg = "" },
                        label = { Text("Contraseña") },
                        leadingIcon  = { Icon(Icons.Default.Lock, null, tint = NeonPurple) },
                        trailingIcon = {
                            IconButton(onClick = { showPass = !showPass }) {
                                Icon(
                                    if (showPass) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                    null, tint = TextMuted
                                )
                            }
                        },
                        visualTransformation = if (showPass) VisualTransformation.None else PasswordVisualTransformation(),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = fieldColors
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = confirm,
                        onValueChange = { confirm = it; errorMsg = "" },
                        label = { Text("Confirmar contraseña") },
                        leadingIcon = { Icon(Icons.Default.Lock, null, tint = NeonPurple) },
                        visualTransformation = PasswordVisualTransformation(),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = fieldColors
                    )

                    AnimatedVisibility(visible = errorMsg.isNotEmpty()) {
                        Text(
                            errorMsg,
                            color = ImpostorRed,
                            fontSize = 13.sp,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            when {
                                username.isBlank() -> errorMsg = "Introduce un nombre de usuario"
                                email.isBlank()    -> errorMsg = "Introduce tu correo"
                                !email.contains("@") -> errorMsg = "Correo no válido"
                                password.length < 6  -> errorMsg = "La contraseña debe tener al menos 6 caracteres"
                                password != confirm  -> errorMsg = "Las contraseñas no coinciden"
                                db.userExists(email.trim()) -> errorMsg = "Ya existe una cuenta con ese correo"
                                else -> {
                                    isLoading = true
                                    val ok = db.register(username.trim(), email.trim(), password)
                                    if (ok) {
                                        val user = db.login(email.trim(), password)
                                        user?.let { session.saveSession(it.id, it.username, it.email) }
                                        onRegisterSuccess()
                                    } else {
                                        errorMsg = "Error al crear la cuenta. Inténtalo de nuevo."
                                    }
                                    isLoading = false
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth().height(52.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = NeonPurple,
                            contentColor   = Color.White
                        ),
                        enabled = !isLoading
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator(color = Color.White, modifier = Modifier.size(20.dp))
                        } else {
                            Text("Crear cuenta", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("¿Ya tienes cuenta?", color = TextMuted, fontSize = 14.sp)
                TextButton(onClick = onBack) {
                    Text(
                        "Inicia sesión",
                        color = NeonPurple,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}
