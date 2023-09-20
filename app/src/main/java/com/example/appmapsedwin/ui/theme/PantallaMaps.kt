package com.example.appmapsedwin.ui.theme

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.appmapsedwin.MainActivityMaps

@Composable
fun PantallaMaps(){
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
            Surface(
                modifier = Modifier
                    .height(300.dp)
                    .width(500.dp)
                    .padding(1.dp)/*Padding for surface*/,
                color = Color(0xFFA1E2EB),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(text = "Basic Surface",Modifier.padding(16.dp)/*Padding for Text*/)
            }
            Text(text = "DELIVERY2U")
            Button(onClick = {
                val intent = Intent(context, MainActivityMaps::class.java)
                context.startActivity(intent)
            }) {
                Text(text = "DELIVERY", textAlign = TextAlign.Center )
            }
        }

    }
    /*Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "DELIVERY2U");
        Button(onClick = {
            val intent = Intent(context, MainActivityMaps::class.java)
            context.startActivity(intent)
        }) {
            Text(text = "DELIVERY")
        }
    }*/
}