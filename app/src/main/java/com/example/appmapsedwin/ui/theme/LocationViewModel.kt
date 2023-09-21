package com.example.appmapsedwin.ui.theme

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmapsedwin.mvvm.model.LocationData
import com.example.appmapsedwin.mvvm.repository.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocationViewModel: ViewModel() {
    private val apiService = ApiService.locationApi

    fun sendLocation(latitude: Double, longitude: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiService.sendLocation(LocationData(latitude, longitude))
            if (response.isSuccessful) {
                Log.d("Si agarro", "Si agarro $latitude, $longitude")
                // La solicitud se realizó correctamente
                val responseBody = response.body()
                // Puedes usar 'responseBody' para obtener más información de la respuesta
            } else {
                // Hubo un error en la solicitud
                val errorBody = response.errorBody()?.string()
                // 'errorBody' contiene el cuerpo de la respuesta de error del servidor
            }
        }
    }
}