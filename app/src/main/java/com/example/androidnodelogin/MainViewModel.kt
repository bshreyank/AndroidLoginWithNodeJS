package com.example.androidnodelogin

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.lifecycle.viewModelScope
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val BASE_URL = "http://192.168.0.102:3000"
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val retrofitInterface: RetrofitInterface =
        retrofit.create(RetrofitInterface::class.java)

    fun login(
        email: String,
        password: String,
        onResult: (LoginResult?) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = retrofitInterface.executeLogin(email, password)
                withContext(Dispatchers.Main) {
                    onResult(result)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        getApplication(),
                        "Login failed: ${e.message}",
                        Toast.LENGTH_LONG
                    ).show()
                    onResult(null)
                }
            }
        }
    }

    fun signup(
        name: String,
        email: String,
        password: String,
        onResult: (Boolean) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                retrofitInterface.executeSignup(name, email, password)
                withContext(Dispatchers.Main) {
                    onResult(true)
                    Toast.makeText(getApplication(), "Signed up successfully", Toast.LENGTH_LONG)
                        .show()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        getApplication(),
                        "Signup failed: ${e.message}",
                        Toast.LENGTH_LONG
                    ).show()
                    onResult(false)
                }
            }
        }
    }
}
