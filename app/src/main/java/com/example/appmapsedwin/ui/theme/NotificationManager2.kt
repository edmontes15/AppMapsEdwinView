package com.example.appmapsedwin.ui.theme

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.appmapsedwin.R
import java.util.Date

class NotificationManager2 (private val context: Context) {

    companion object {
        const val CHANNEL_ID = "YourChannelId"
    }

    fun sendNotification(latitude: Double, longitude: Double, date: Date) {
        // Obtener una instancia de NotificationManagerCompat desde applicationContext
        val notificationManager = NotificationManagerCompat.from(context)

        // Crear un canal de notificación (necesario en Android 8.0 y versiones posteriores)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Channel Name",
                importance
            )
            notificationManager.createNotificationChannel(channel)
        }

        // Crear un intent para abrir la actividad principal cuando se toque la notificación
        val intent = Intent(context, MainActivityMaps::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        // Construir la notificación
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle("Nueva notificación")
            .setContentText("Latitud: $latitude, Longitud: $longitude, date: $date")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setOngoing(true)
            .setSmallIcon(R.drawable.ic_moto)

        // Enviar la notificación
        notificationManager.notify(1, builder.build())
    }
}