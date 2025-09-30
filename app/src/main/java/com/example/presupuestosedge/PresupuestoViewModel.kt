package com.example.presupuestosedge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PresupuestoViewModel @Inject constructor(
    private val repository: TransaccionRepository
) : ViewModel() {
    private val _transacciones = MutableLiveData<List<Transaccion>>()
    val transacciones: LiveData<List<Transaccion>> = _transacciones
    private val _iaAlert = MutableLiveData<String>()
    val iaAlert: LiveData<String> = _iaAlert

    init {
        loadTransacciones()
    }

    fun saveTransaccion(monto: Double, descripcion: String, categoria: String) {
        viewModelScope.launch {
            val nuevaTransaccion = Transaccion(
                fecha = System.currentTimeMillis(),
                monto = monto,
                descripcion = descripcion,
                categoria = categoria
            )
            repository.insertTransaccion(nuevaTransaccion)

            loadTransacciones()
        }
    }
    fun loadTransacciones() {
        viewModelScope.launch {
            val list = withContext(Dispatchers.IO) {
                repository.getAllTransacciones()
            }
            _transacciones.postValue(list)

            runIaAnalysis(list)
        }
    }
    public fun runIaAnalysis(transacciones: List<Transaccion>) {
        viewModelScope.launch(Dispatchers.Default) {
            val alert = repository.analyzePattern(transacciones) // Changed from analyzePatterns
            _iaAlert.postValue(alert)
        }
    }
}