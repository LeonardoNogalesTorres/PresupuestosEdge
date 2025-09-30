package com.example.presupuestosedge
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transacciones")
data class Transaccion(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val fecha: Long,
    val monto: Double,
    val descripcion: String,
    val categoria: String,

    val isSynced: Boolean = false,

    val lastModified: Long = System.currentTimeMillis()
)