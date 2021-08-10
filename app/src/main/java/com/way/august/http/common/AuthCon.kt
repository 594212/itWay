package com.way.august.http.common

import com.google.gson.annotations.SerializedName

data class RequestLog(
    val email: String,
    val password: String
)
data class ResponseAuth(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("token_type")
    val tokenType: String,
    @SerializedName("expires_in")
    val expiresIn: Int
)

data class RequestReg(
    val email: String,
    val name: String,
    val password: String)