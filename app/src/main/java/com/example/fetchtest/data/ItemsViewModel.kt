package com.example.fetchtest.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fetchtest.RetrofitClient.amazonApiService

class ItemsViewModel : ViewModel() {
    private val _itemsDataObject = MutableLiveData<List<OneItem>>()
    val itemsDataObject: LiveData<List<OneItem>> get() = _itemsDataObject


    suspend fun getData(): Boolean {
        var successfulResponse = false
        try {
            val response = amazonApiService.getItems()
            if (response.isSuccessful) {
                successfulResponse = true
                _itemsDataObject.value = response.body()
            } else {
                Log.d("ItemsViewModel", "response NOT successful")
                successfulResponse = false
                val errorBody = response.errorBody()?.string()
                Log.d("ItemsViewModel", "HTTP error: ${response.code()} - $errorBody")
            }
        } catch (e: Exception) {
            Log.d("ItemsViewModel", "First Exception: ${e.message}")
        } catch (e: Exception) {
            Log.d("ItemsViewModel", "Second Exception: ${e.message}")

        }
            return successfulResponse;
        }

    }