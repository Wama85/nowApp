package com.softwama.now.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat

@Composable
fun WhatsAppButton(
    modifier: Modifier = Modifier,
    phoneNumber: String = "+59172265286", // Número por defecto
    message: String = "Hola, necesito ayuda", // Mensaje por defecto
    icon: ImageVector = Icons.Filled.Chat,
    backgroundColor: Color = Color(0xFF25D366), // Color verde de WhatsApp
    iconColor: Color = Color.White
) {
    val context = LocalContext.current

    Box(
        modifier = modifier
            .size(60.dp)
            .shadow(4.dp, CircleShape)
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable {
                // Abrir WhatsApp con el número y mensaje
                openWhatsApp(context, phoneNumber, message)
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "Contactar por WhatsApp",
            tint = iconColor,
            modifier = Modifier.size(30.dp)
        )
    }
}

fun openWhatsApp(context: android.content.Context, phoneNumber: String, message: String) {
    try {
        val url = "https://api.whatsapp.com/send?phone=$phoneNumber&text=${message.replace(" ", "%20")}"
        val intent = android.content.Intent(android.content.Intent.ACTION_VIEW, android.net.Uri.parse(url))
        ContextCompat.startActivity(context, intent, null)
    } catch (e: Exception) {
        // Si WhatsApp no está instalado
        val marketIntent = android.content.Intent(android.content.Intent.ACTION_VIEW,
            android.net.Uri.parse("market://details?id=com.whatsapp"))
        ContextCompat.startActivity(context, marketIntent, null)
    }
}