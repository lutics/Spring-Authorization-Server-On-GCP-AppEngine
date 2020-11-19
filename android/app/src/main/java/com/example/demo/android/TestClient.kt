package com.example.demo.android

import okhttp3.Credentials
import retrofit2.Response
import retrofit2.http.*

interface TestClient {

    @POST
    @FormUrlEncoded
    suspend fun login(
        @Url url: String,
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<Void>

    @GET
    suspend fun authorize(
        @Url url: String,
        @Query("client_id") clientId: String = "id",
        @Query("response_type") responseType: String = "code",
        @Query("redirect_uri") redirectUri: String = "android-app://${BuildConfig.APPLICATION_ID}/oauth2/redirect",
    ): Response<Void>

    @POST
    suspend fun token(
        @Url url: String,
        @Header("Authorization") authorization: String = Credentials.basic("id", "secret"),
        @Query("grant_type") grantType: String = "authorization_code",
        @Query("redirect_uri") redirectUri: String = "android-app://${BuildConfig.APPLICATION_ID}/oauth2/redirect",
        @Query("code") code: String?
    ): String
}