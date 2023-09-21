package com.example.appmapsedwin.ui.theme.ui

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appmapsedwin.ui.theme.MainActivityMaps
import com.example.appmapsedwin.R

@Composable
fun PantallaSettings(){
    val context = LocalContext.current
    Box(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth()){
        Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = 30.dp,
                    bottomEnd = 30.dp
                )
            )
    ) {
        Image(
            painter = painterResource(id = R.mipmap.ic_portada),
            contentDescription = "Imagen",
            contentScale = ContentScale.Crop, // Escalar la imagen para que llene el espacio del Box
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight()

        )
    }
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally){
                Spacer(modifier = Modifier.height(180.dp) )
                Column (modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally){
                    Image(
                        painter = painterResource(id = R.mipmap.ic_perfil),
                        contentDescription = "Logo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(200.dp)

                    )
                    Text(text = "Informacion Personal", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Text(text = "EDWIN IVAN MEDINA MONTES", fontSize = 10.sp)
                    Text(text = "Telefono: 7713637591", fontSize = 10.sp)
                    Text(text = "Gmail: edwinmedin648@gmail.com", fontSize = 10.sp)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Tecnologias Utilizadas", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    scrollView()
                    Spacer(modifier = Modifier.height(50.dp))
                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = Blue),
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

        }
    }

@Composable
fun scrollView(){
    Column(
        modifier = Modifier
            .height(200.dp)
            .width(300.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        Row (verticalAlignment = Alignment.CenterVertically){
            Image(painter = painterResource(id = R.mipmap.ic_perfil),contentDescription = "Logo",)
            Text(text = "Kotlin")
        }
        Row (verticalAlignment = Alignment.CenterVertically){
            Image(painter = painterResource(id = R.mipmap.ic_perfil),contentDescription = "Logo",)
            Text(text = "Arquitectura MVVM")
        }
        Row (verticalAlignment = Alignment.CenterVertically){
            Image(painter = painterResource(id = R.mipmap.ic_perfil),contentDescription = "Logo",)
            Text(text = "Notificaiones Push del Sistema")
        }
        Row (verticalAlignment = Alignment.CenterVertically){
            Image(painter = painterResource(id = R.mipmap.ic_perfil),contentDescription = "Logo",)
            Text(text = "Google Maps")
        }
        Row (verticalAlignment = Alignment.CenterVertically){
            Image(painter = painterResource(id = R.mipmap.ic_perfil),contentDescription = "Logo",)
            Text(text = "XML")
        }
        Row (verticalAlignment = Alignment.CenterVertically){
            Image(painter = painterResource(id = R.mipmap.ic_perfil),contentDescription = "Logo",)
            Text(text = "Retrofit")
        }
    }
}
