package com.yoesuv.composenetworking.networks

import io.ktor.client.HttpClient
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.utils.io.errors.IOException
import kotlinx.serialization.SerializationException

class ApiService(private val client: HttpClient) {

    companion object {
        private const val END_POINT_PLACES =
            "https://gist.githubusercontent.com/yoesuv/3e17a05acc63f4e696ba4cb15d3b9f2f/raw/3e879b1c5cfe652ca25a18019ca4bc219f20177d/daftar_tempat.json"
        private const val END_POINT_GALLERY =
            "https://gist.githubusercontent.com/yoesuv/9a306b2244e2878201af1669459e127a/raw/7c7ffbc44c8e9d35731b5197c7e7f00c777e416e/daftar_gallery.json"
    }

    suspend fun <T> call(apiCall: suspend () -> T): NetworkResult<T> {
        return try {
            val data = apiCall()
            NetworkResult.Success(data)
        } catch (e: ClientRequestException) {
            NetworkResult.HttpError(e.response.status.value, e.message)
        } catch (e: SerializationException) {
            NetworkResult.ParseError("Parsing Failed : ${e.message}")
        } catch (e: IOException) {
            NetworkResult.NetworkError("Network Error : ${e.message}")
        } catch (e: Exception) {
            NetworkResult.GenericError(e)
        }
    }

    suspend fun getListPlaces(): HttpResponse = client.get(END_POINT_PLACES)
    suspend fun getGallery(): HttpResponse = client.get(END_POINT_GALLERY)

}