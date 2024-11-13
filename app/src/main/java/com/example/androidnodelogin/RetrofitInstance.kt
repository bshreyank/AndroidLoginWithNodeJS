package com.example.androidnodelogin

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RetrofitInterface {

    @POST("/login")
    @FormUrlEncoded
    suspend fun executeLogin(
        @Field("username") username: String,
        @Field("password") password: String
    ): LoginResult

    @POST("/signup")
    @FormUrlEncoded
    suspend fun executeSignup(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("email") email: String
    ): SignupRequest
}
