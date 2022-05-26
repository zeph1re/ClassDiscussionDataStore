package binar.ganda.classdiscussiondatastore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.asLiveData
import androidx.navigation.Navigation
import binar.ganda.classdiscussiondatastore.model.ResponseAllUserItem
import binar.ganda.classdiscussiondatastore.network.ApiClient
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginFragment : Fragment() {


    private var userManager : UserManager?= null
    lateinit var email : String
    lateinit var password : String
    lateinit var listUser : List<ResponseAllUserItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userManager = UserManager(requireContext())

        userManager?.userUsername?.asLiveData()?.observe(viewLifecycleOwner){
            if (it != ""){
                Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_homeFragment)
            }
        }

        btnLogin.setOnClickListener {
            if  (loginEmail.text.isNotEmpty() && loginPassword.text.isNotEmpty()){
                email = loginEmail.text.toString()
                password = loginPassword.text.toString()

                login(email, password)

            } else {
                Toast.makeText(requireContext(),"Field Email dan Password harus diisi",Toast.LENGTH_LONG).show()
            }
        }


    }

    fun login(username : String, password : String){
        ApiClient.INSTANCE.getAllUser(username)
            .enqueue(object : retrofit2.Callback<List<ResponseAllUserItem>>{
                override fun onResponse(
                    call: Call<List<ResponseAllUserItem>>,
                    response: Response<List<ResponseAllUserItem>>
                ) {
                    if (response.isSuccessful){
                        if (response.body()?.isEmpty() == true){
                            Toast.makeText(requireContext(), "Unknowed User", Toast.LENGTH_SHORT).show()
                        } else {
                            when {
                                response.body()?.size!! > 1 -> {
                                    Toast.makeText(requireContext(), "Mohon Masukkan Data", Toast.LENGTH_SHORT).show()
                                }
                                username!= response.body()!![0].username   -> {
                                    Toast.makeText(requireContext(), "Email tidak terdaftar", Toast.LENGTH_SHORT).show()
                                }
                                password!= response.body()!![0].password   -> {
                                    Toast.makeText(requireContext(), "Password tidak terdaftar", Toast.LENGTH_SHORT).show()
                                }
                                else -> {
                                    detailUser(listUser)
                                    Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_homeFragment)
                                }
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<List<ResponseAllUserItem>>, t: Throwable) {
                    Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
                }

            })
    }

    fun detailUser(listLogin : List<ResponseAllUserItem>) {
        for (i in listLogin.indices){
            GlobalScope.launch {
                userManager!!.saveData(
                    listLogin[i].username,
                    listLogin[i].password,
                    listLogin[i].name,
                    listLogin[i].umur.toString(),
                    listLogin[i].image,
                    listLogin[i].address,
                    listLogin[i].id,
                )
            }
        }
    }
}