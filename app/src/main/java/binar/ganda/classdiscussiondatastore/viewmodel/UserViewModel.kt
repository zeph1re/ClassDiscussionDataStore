package binar.ganda.classdiscussiondatastore.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import binar.ganda.classdiscussiondatastore.model.ResponseAllUserItem
import binar.ganda.classdiscussiondatastore.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel(){

    var liveDataUser : MutableLiveData<List<ResponseAllUserItem>> = MutableLiveData()

    fun getLivedataUser() : MutableLiveData<List<ResponseAllUserItem>> {
        return liveDataUser
    }

    fun callApiUser() {
        with(ApiClient){
            INSTANCE.getAllUser()
                .enqueue(object : Callback<List<ResponseAllUserItem>> {
                    override fun onResponse(
                        call: Call<List<ResponseAllUserItem>>,
                        response: Response<List<ResponseAllUserItem>>
                    ){
                        if (response.isSuccessful){
                            liveDataUser.postValue(response.body())
                        } else {
                            liveDataUser.postValue(null)
                        }
                    }

                    override fun onFailure(call: Call<List<ResponseAllUserItem>>, t: Throwable) {
                        liveDataUser.postValue(null)
                    }

                })
        }
    }
}