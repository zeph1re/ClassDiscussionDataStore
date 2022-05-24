package binar.ganda.classdiscussiondatastore.network

import binar.ganda.classdiscussiondatastore.model.ResponseAllUserItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("user")
    fun getAllUser() : Call<List<ResponseAllUserItem>>
}