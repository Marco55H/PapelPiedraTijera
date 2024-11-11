package com.polo.papelpiedratijera

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.polo.papelpiedratijera.ui.theme.PapelPiedraTijeraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PapelPiedraTijeraTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "PantallaInicial"
                ) {
                    composable("PantallaInicial") { PantallaInicial(navController) }

                    composable("paginaJugable/{nombre}") { backStackEntry ->
                        paginaJugable(
                            nombre = backStackEntry.arguments?.getString("nombre"),
                            navController = navController
                        ) }

                    composable("paginaPelea/{elegido}/{elegidoMaquina}/{navController}/{nombre}") { backStackEntry ->
                        paginaPelea(
                            jugador = backStackEntry.arguments?.getString("elegido"),
                            maquina = backStackEntry.arguments?.getString("elegidoMaquina"),
                            nombre = backStackEntry.arguments?.getString("nombre"),
                            navController = navController

                        )
                    }

                }
            }
        }
    }
}





