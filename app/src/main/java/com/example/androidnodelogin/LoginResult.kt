package com.example.androidnodelogin

data class LoginResult(
    val name: String,
    val email: String
)

data class SignupRequest(
    val username: String,
    val password: String,
    val email: String
)
