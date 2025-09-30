package com.example.presupuestosedge

// Removed import com.example.presupuestosedge.AppDatabase as it's passed via constructor
// but AppDatabase itself isn't directly used if only db.transaccionDao() is needed.
// However, db is still used, so the import might still be needed if db type is explicit.
// For now, assuming it's fine as db is type AppDatabase from constructor.

class TransaccionRepository(
    private val db: AppDatabase,
    private val gastoAnalyzer: GastoAnalyzer // Added GastoAnalyzer
) {

    private val transaccionDao = db.transaccionDao()

    suspend fun insertTransaccion(transaccion: Transaccion) {
        transaccionDao.insert(transaccion)
    }

    fun getAllTransacciones(): List<Transaccion> {
        return transaccionDao.getAll()
    }

    suspend fun getPendingSync(): List<Transaccion> {
        return transaccionDao.getPendingSync()
    }

    suspend fun markAsSynced(id: Int) {
        transaccionDao.markAsSynced(id)
    }

    fun analyzePattern(transacciones: List<Transaccion>): String {
        return gastoAnalyzer.analyze(transacciones) // Use GastoAnalyzer
    }
}