package binar.ganda.classdiscussiondatastore.network

import binar.ganda.classdiscussiondatastore.model.ResponseAllUserItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("user")
    fun getAllUser(
        @Query("username" ) username :String
    ) : Call<List<ResponseAllUserItem>>
}