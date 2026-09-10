package com.example.ejemplo2.ui.theme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val AppColorScheme = lightColorScheme(
    primary = VerdePrimario,
    secondary = TurquesaSecundario,
    tertiary = Azul,
    background = FondoApp,
    surface = SuperficiePrimario,
    onPrimary = SuperficiePrimario,
    onSecondary = SuperficiePrimario,
    onBackground = TextoPrimario,
    onSurface = TextoPrimario,
    error = ErrorRojo)
@Composable
fun Ejemplo2Theme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = AppColorScheme,
        typography = Typography,
        content = content
    )
}