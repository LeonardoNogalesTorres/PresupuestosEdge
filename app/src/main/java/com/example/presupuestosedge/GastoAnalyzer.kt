package com.example.presupuestosedge

import android.content.Context
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.channels.FileChannel

class GastoAnalyzer(private val context: Context) {

    private var interpreter: Interpreter? = null
    private val modelFileName = "budget_model.tflite"

    init {
        loadModel()
    }
    private fun loadModel() {
        try {
            val fileDescriptor = context.assets.openFd(modelFileName)
            val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
            val fileChannel = inputStream.channel
            val startOffset = fileDescriptor.startOffset
            val declaredLength = fileDescriptor.declaredLength

            val modelBuffer = fileChannel.map(
                FileChannel.MapMode.READ_ONLY,
                startOffset,
                declaredLength
            )
            interpreter = Interpreter(modelBuffer)

            println("IA en el Edge: Modelo $modelFileName cargado exitosamente.")
        } catch (e: Exception) {
            e.printStackTrace()
            println("IA en el Edge: ERROR al cargar el modelo. Análisis deshabilitado.")
        }
    }

    fun analyze(transacciones: List<Transaccion>): String {
        if (interpreter == null) {
            return "El análisis de presupuesto está inactivo (IA no cargada)."
        }

        // ------------------------------------------------------------------
        // PASO 1: Procesar los datos de las transacciones para el modelo.
        // Aquí convertirías 'transacciones' en un Array o Tensor float/int.
        // Por ahora, solo es una simulación:
        val totalGasto = transacciones.sumOf { it.monto }

        // ------------------------------------------------------------------
        // PASO 2: Ejecutar la inferencia del modelo TFLite.
        // val inputBuffer = ...
        // val outputBuffer = ...
        // interpreter.run(inputBuffer, outputBuffer)

        // ------------------------------------------------------------------
        // PASO 3: Interpretar la salida del modelo.
        if (totalGasto > 1000) { // Ejemplo de lógica simple
            return "¡Alerta de Presupuesto! Su gasto total (${totalGasto}) supera la media histórica."
        } else {
            return "El patrón de gasto es saludable. No se detectaron anomalías."
        }
    }

    fun close() {
        interpreter?.close()
        interpreter = null
    }
}