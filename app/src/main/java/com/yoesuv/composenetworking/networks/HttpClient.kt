package com.yoesuv.composenetworking.networks

import android.util.Log
import com.yoesuv.composenetworking.data.separator
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.client.statement.request
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private const val NETWORK_TIME_OUT = 6_000L

val httpClientAndroid = HttpClient(Android) {
    install(ContentNegotiation) {
        register(
            ContentType.Text.Any, KotlinxSerializationConverter(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        )
        json(
            Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            }
        )
    }

    install(Logging) {
        logger = Logger.ANDROID
        level = LogLevel.ALL
    }

    install(HttpTimeout) {
        requestTimeoutMillis = NETWORK_TIME_OUT
        connectTimeoutMillis = NETWORK_TIME_OUT
        socketTimeoutMillis = NETWORK_TIME_OUT
    }

    install(ResponseObserver) {
        onResponse { response ->
            val code = response.status.value
            val url = response.request.url
            val headers = response.headers.entries()
            Log.d("TAG_DEBUG", "$separator BEGIN RESPONSE $separator")
            Log.d("TAG_DEBUG", headers.joinToString("\n") { "${it.key}: ${it.value}" })
            Log.d("TAG_DEBUG", "STATUS : $code URL: $url")
            Log.d("TAG_DEBUG", "BODY : ${response.body<String>()}")
            Log.d("TAG_DEBUG", "$separator END RESPONSE $separator")
        }
    }

    install(DefaultRequest) {
        header(HttpHeaders.ContentType, ContentType.Application.Json)
    }

    defaultRequest {
        contentType(ContentType.Application.Json)
        accept(ContentType.Application.Json)
    }
}