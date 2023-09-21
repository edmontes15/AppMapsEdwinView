package com.example.appmapsedwin.ui.theme

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appmapsedwin.MainActivityMaps
import com.example.appmapsedwin.R

@Composable
fun PantallaMaps(){
    val context = LocalContext.current
    Box (modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()){
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(
                        color = Color.Blue,
                        shape = RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 0.dp,
                            bottomStart = 30.dp,
                            bottomEnd = 30.dp
                        ),
                    )
            ) {
                Column (modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally){
                    Text(text = "MAPS2U", textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, color = Color.Black,fontSize = 20.sp )
                    Image(
                        painter = painterResource(id = R.mipmap.ic_maps),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .size(250.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(200.dp) )
            Button(
                onClick = {
                    val intent = Intent(context, MainActivityMaps::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .fillMaxWidth() // Ocupa todo el ancho disponible
                    .height(48.dp)
            ) {
                Text(
                    text = "MAPS",
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }

        }

    }
    /*Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column (modifier = Modifier.fillMaxWidth().fillMaxHeight().background(color = Color.Green), horizontalAlignment = Alignment.CenterHorizontally){
            Surface(
                modifier = Modifier
                    .height(300.dp)
                    .width(500.dp)
                    .padding(0.dp)/*Padding for surface*/,
                color = Color(0xFFA1E2EB),
                shape = RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp)
            ) {
                Spacer(modifier = Modifier.height(150.dp))
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Image(
                        painter = painterResource(id = R.mipmap.ic_maps_foreground),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .size(100.dp)
                            .background(Color.White) // Opcional: puedes agregar un fondo blanco a la imagen
                    )
                }
            }
            Text(text = "DELIVERY2U", textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, color = Color.Black)
            Spacer(modifier = Modifier.height(150.dp))
            Button(
                onClick = {
                    val intent = Intent(context, MainActivityMaps::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .fillMaxWidth() // Ocupa todo el ancho disponible
                    .height(48.dp)
            ) {
                Text(
                    text = "Maps",
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
        }

    }
    Column(
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