package com.example.presupuestosedge

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class SyncWorker(appContext: Context, workerParams: WorkerParameters) : CoroutineWorker(appContext, workerParams) {
    private val db = AppDatabase.getDatabase(appContext)
    private val repository = TransaccionRepository(db)
    private val firestore = FirebaseFirestore.getInstance()
    private val transaccionesCollection = firestore.collection("transacciones_users")

    override suspend fun doWork(): Result {

        val pendingTransactions = repository.getPendingSync()

        if (pendingTransactions.isEmpty()) {
            return Result.success()
        }

        try {
            for (transaccion in pendingTransactions) {
                val data = hashMapOf(
                    "monto" to transaccion.monto,
                    "descripcion" to transaccion.descripcion,
                    "categoria" to transaccion.categoria,
                    "fecha" to transaccion.fecha,
                    "lastModified" to transaccion.lastModified
                )

                transaccionesCollection.document(transaccion.id.toString())
                    .set(data)
                    .await()
            }

            for (transaccion in pendingTransactions) {
                repository.markAsSynced(transaccion.id)
            }

            return Result.success()

        } catch (e: Exception) {
            e.printStackTrace()
            return Result.retry()
        }
    }
}