package com.example.presupuestosedge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding // Added import
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview // Added import
import com.example.presupuestosedge.ui.theme.PresupuestosEdgeTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.compose.hiltViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        scheduleSyncWorker(applicationContext)

        setContent {
            PresupuestosEdgeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    PresupuestoAppScreen(
                        modifier = Modifier.fillMaxSize().padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PresupuestoAppScreen(
    viewModel: PresupuestoViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val transacciones by viewModel.transacciones.observeAsState(initial = emptyList())
    val iaAlert by viewModel.iaAlert.observeAsState(initial = "Cargando análisis IA...")

    Text(
        text = "Estado del Sistema:\n" +
                "Total Transacciones Locales (Edge): ${transacciones.size}\n" +
                "Alerta IA: ${iaAlert}\n" +
                "Sistema de Sincronización WorkManager iniciado.",
        modifier = modifier
    )

}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PresupuestosEdgeTheme {
        Greeting("Hilt Connectado")
    }
}