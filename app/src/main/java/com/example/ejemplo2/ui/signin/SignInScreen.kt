package com.example.ejemplo2.ui.signin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.example.ejemplo2.R

@Composable
fun SignInScreen(viewModel: SignInViewModel, exitoiniciarSesion: () -> Unit,
    registrado: () -> Unit) {
    val state = viewModel.uiState
    if (state.exitoIniciarSesion) {
        exitoiniciarSesion()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 28.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Image(painter = painterResource(id = R.drawable.logo_vaquita_virtual), contentDescription = "Logo Vaquita Virtual",
            modifier = Modifier.size(105.dp))
        Spacer(modifier = Modifier.height(18.dp))
        Text(text = "Bienvenido", fontSize = 34.sp,
            fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Inicia sesión para continuar", style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(modifier = Modifier.height(36.dp))
        OutlinedTextField(value = state.email, onValueChange = viewModel::emailCambiado,
            label = { Text("Correo electrónico") },
            placeholder = { Text("ejemplo@correo.com") },
            leadingIcon = { Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Correo")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(14.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email))
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = state.contrasena, onValueChange = viewModel::contrasenaCambiada,
            label = { Text("Contraseña") },
            leadingIcon = { Icon(imageVector = Icons.Default.Lock,
                    contentDescription = "Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(14.dp),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password))

        if (state.error != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.Start)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = viewModel::iniciarSesion,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(16.dp),
            enabled = !state.cargando) {
            if (state.cargando) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = MaterialTheme.colorScheme.onPrimary,
                    strokeWidth = 2.dp)
            } else {
                Text(text = "Ingresar", fontSize = 17.sp, fontWeight = FontWeight.SemiBold)
            }
        }

        Spacer(modifier = Modifier.height(22.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "¿No tienes una cuenta?",
                color = MaterialTheme.colorScheme.onSurfaceVariant)

            TextButton(onClick = registrado) {
                Text(
                    text = "Crear cuenta",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}