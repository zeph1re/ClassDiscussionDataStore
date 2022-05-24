package binar.ganda.classdiscussiondatastore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {


    lateinit var email : String
    lateinit var password : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLogin.setOnClickListener {
            if  (loginEmail.text.isNotEmpty() && loginPassword.text.isNotEmpty()){
                email = loginEmail.text.toString()
                password = loginPassword.text.toString()

                

            } else {
                Toast.makeText(requireContext(),"Field Email dan Password harus diisi",Toast.LENGTH_LONG).show()
            }
            Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_homeFragment)
        }


    }
}