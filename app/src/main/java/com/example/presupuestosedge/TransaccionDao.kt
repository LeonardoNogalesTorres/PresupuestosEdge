package com.example.presupuestosedge

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TransaccionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(transaccion: Transaccion)

    @Update
    suspend fun update(transaccion: Transaccion)

    @Query("SELECT * FROM transacciones WHERE isSynced = 0")
    suspend fun getPendingSync(): List<Transaccion>

    @Query("UPDATE transacciones SET isSynced = 1 WHERE id = :id")
    suspend fun markAsSynced(id: Int)

    @Query("SELECT * FROM transacciones ORDER BY fecha DESC")
    fun getAll(): List<Transaccion>

}