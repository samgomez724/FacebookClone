package me.sgomez.facebookclon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Colores oficiales de Facebook
val FacebookBlue = Color(0xFF1877F2)
private val FacebookLightBlue = Color(0xFF42B72A) // verde para "Crear cuenta"
private val FacebookGray = Color(0xFFF0F2F5)
private val FacebookBorderGray = Color(0xFFCCD0D5)
private val FacebookTextGray = Color(0xFF606770)
private val FacebookDarkText = Color(0xFF1C1E21)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FacebookLoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(80.dp))

        // Logo de Facebook (texto estilizado)
        Text(
            text = "facebook",
            color = FacebookBlue,
            fontSize = 52.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = (-1).sp
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Campo de Email/Teléfono
        FacebookTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = "Correo electrónico o número de teléfono",
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Campo de Contraseña
        FacebookTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = "Contraseña",
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
            isPassword = true,
            passwordVisible = passwordVisible,
            onTogglePasswordVisibility = { passwordVisible = !passwordVisible }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón Iniciar sesión
        Button(
            onClick = {  },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = FacebookBlue),
            shape = RoundedCornerShape(6.dp),
            //enabled = email.isNotBlank() && password.isNotBlank()
        ) {
            Text(
                text = "Iniciar sesión",
                color = Color.White,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Link "¿Olvidaste tu contraseña?"
        TextButton(onClick = {}) {
            Text(
                text = "¿Olvidaste tu contraseña?",
                color = FacebookTextGray,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Divisor "O"
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                color = FacebookBorderGray,
                thickness = 1.dp
            )
            Text(
                text = "o",
                modifier = Modifier.padding(horizontal = 12.dp),
                color = FacebookTextGray,
                fontSize = 14.sp
            )
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                color = FacebookBorderGray,
                thickness = 1.dp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Botón Crear cuenta nueva
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = FacebookLightBlue),
            shape = RoundedCornerShape(6.dp)
        ) {
            Text(
                text = "Crear cuenta nueva",
                color = Color.White,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Footer con marca registrada
        Text(
            text = "Meta © 2024",
            modifier = Modifier.padding(bottom = 24.dp),
            color = FacebookTextGray,
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun FacebookTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    isPassword: Boolean = false,
    passwordVisible: Boolean = false,
    onTogglePasswordVisibility: () -> Unit = {}
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = placeholder,
                color = FacebookTextGray,
                fontSize = 16.sp
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(6.dp),
        visualTransformation = if (isPassword && !passwordVisible)
            PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        trailingIcon = if (isPassword) {
            {
                IconButton(onClick = onTogglePasswordVisibility) {
                    Icon(
                        imageVector = if (passwordVisible)
                            Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = if (passwordVisible)
                            "Ocultar contraseña" else "Mostrar contraseña",
                        tint = FacebookTextGray
                    )
                }
            }
        } else null,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = FacebookBlue,
            unfocusedBorderColor = FacebookBorderGray,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            cursorColor = FacebookBlue
        )
    )
}