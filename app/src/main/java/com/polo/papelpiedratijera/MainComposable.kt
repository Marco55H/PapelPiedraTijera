package com.polo.papelpiedratijera

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.compose.ui.platform.LocalContext
import kotlin.random.Random



@Composable
fun PantallaInicial( navController: NavHostController) {

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){

        var nombre by remember { mutableStateOf("") }

        Text(
            text = "Bienvenido!"
        )

        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("¿Cómo te llamas?") }
        )



        Button(onClick = {navController.navigate("paginaJugable/$nombre")}) { Text(text = "Empezar a jugar") }

    }

}

@Composable
fun paginaJugable(nombre : String? ,navController: NavHostController) {

    var valBoton by remember { mutableStateOf(value=false) }
    val brush = Brush.verticalGradient(listOf(Color.Red, Color.Yellow))
    var elegido by remember { mutableStateOf("") }
    var maquina by remember { mutableStateOf(0) }
    var elegidoMaquina by remember { mutableStateOf("") }
    val nombreJugador: String = nombre.toString()

    Log.d("PapelPiedraTijera", "NOMBRE: $nombre")

     maquina = Random.nextInt(1, 4)

    if(maquina==1){
        elegidoMaquina="tijera"
    }else if(maquina==2){
        elegidoMaquina="piedra"
    }else{
        elegidoMaquina="papel"
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(brush),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ){

        var modifier = Modifier
            .width(100.dp)
            .height(100.dp)

        Image(
            painter = painterResource(id = R.drawable.robot),
            contentDescription = "",
            modifier
        )
        Row() {
            Image(
                painter = painterResource(id = R.drawable.piedra),
                contentDescription = "",
                modifier
            )

            Image(
                painter = painterResource(id = R.drawable.papel),
                contentDescription = "",
                modifier
            )

            Image(
                painter = painterResource(id = R.drawable.tijera),
                contentDescription = "",
                modifier
            )
        }

        if( elegido.isNotEmpty()){
            valBoton=true
        }

        TextButton(onClick = { navController.navigate("paginaPelea/$elegido/$elegidoMaquina/$navController/$nombre") }, enabled=valBoton) {

            Image(painter = painterResource(id = R.drawable.versus), contentDescription = "")

        }

        Text(text = nombreJugador)

        Row(verticalAlignment = Alignment.Top) {
            Image(
                painter = painterResource(id = R.drawable.piedra),
                contentDescription = "",
                if (elegido=="piedra") {
                    modifier
                        .clip(shape = RoundedCornerShape(100.dp))
                        .background(Color.Green)
                }else {
                    modifier.clickable { elegido = "piedra" }
                }
            )

            Image(
                painter = painterResource(id = R.drawable.papel),
                contentDescription = "",
                if (elegido=="papel") {
                    modifier
                        .clip(shape = RoundedCornerShape(100.dp))
                        .background(Color.Green)
                }else {
                    modifier.clickable { elegido = "papel" }
                }
            )


            Image(
                painter = painterResource(id = R.drawable.tijera),
                contentDescription = "",

                if (elegido=="tijera") {
                    modifier
                        .clip(shape = RoundedCornerShape(100.dp))
                        .background(Color.Green)
                }else {
                    modifier.clickable { elegido = "tijera" }
                }
            )
        }

        Image(
            painter = painterResource(id = R.drawable.persona),
            contentDescription = "",
            modifier
        )

        Log.d("PapelPiedraTijera", "La máquina eligió: $elegido   $elegidoMaquina")    }
}

@Composable
fun paginaPelea(maquina:String?, jugador:String?, navController: NavController, nombre: String?){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Red),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    )
    {
        val context = LocalContext.current
        var incrementoRealizado by remember { mutableStateOf(false) }
        var acabado by remember { mutableStateOf(false) }
        var modifier1 = Modifier
            .width(100.dp)
            .height(100.dp)

        var modifier2 = Modifier
            .width(200.dp)
            .height(200.dp)

        var imgMaquina=0
        var imgPersona=0
        var resultado="";

        when (maquina) {
            "piedra" -> imgMaquina = R.drawable.piedra
            "papel" -> imgMaquina = R.drawable.papel
            "tijera" ->imgMaquina = R.drawable.tijera
        }

        when (jugador) {
            "piedra" -> imgPersona = R.drawable.piedra
            "papel" -> imgPersona = R.drawable.papel
            "tijera" -> imgPersona = R.drawable.tijera
        }

        Image(
            painter = painterResource(id=R.drawable.robot),
            contentDescription = "",
            modifier1
        )

        Image(
            painter = painterResource(imgMaquina),
            contentDescription = "",
            modifier2
        )

        if(!incrementoRealizado) {
            when {
                jugador == maquina -> resultado = "¡Es un EMPATE!"
                (jugador == "piedra" && maquina == "tijera") ||
                        (jugador == "tijera" && maquina == "papel") ||
                        (jugador == "papel" && maquina == "piedra") -> {
                    resultado = "Ha ganado $nombre"
                    Juego.jugadorVictorias++
                    if (Juego.jugadorVictorias == 5) {
                        acabado=true
                        Toast.makeText(context, "¡Enhorabuena $nombre has alcanzado 5 victorias!", Toast.LENGTH_SHORT).show()
                    }
                }

                else -> {
                    resultado = "Ha ganado la maquina"
                    Juego.maquinaVictorias++
                    if (Juego.maquinaVictorias == 5) {
                        acabado=true
                        Toast.makeText(context, "¡La máquina ha alcanzado 5 victorias!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            incrementoRealizado=true;
        }
        // Mostrar el resultado
        Text(text = resultado, color = Color.White, modifier = Modifier.padding(16.dp))


        Image(
            painter = painterResource(imgPersona),
            contentDescription = "",
            modifier2
        )

        Image(
            painter = painterResource(id=R.drawable.persona),
            contentDescription = "",
            modifier1
        )

        Text(text = "Victorias del $nombre: ${Juego.jugadorVictorias}")
        Text(text = "Victorias de la máquina: ${Juego.maquinaVictorias}")

        Button(onClick = { navController.popBackStack() }, enabled = !acabado) {
            Text("Volver")
        }


    }

}