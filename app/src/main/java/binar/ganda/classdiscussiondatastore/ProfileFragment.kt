package binar.ganda.classdiscussiondatastore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment() {

    private lateinit var userManager: UserManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userManager = UserManager(requireContext())

        userManager.userUsername.asLiveData().observe(viewLifecycleOwner) {
            tvUsername.text = it
        }

        userManager.userName.asLiveData().observe(viewLifecycleOwner) {
            tvNama.text = it
        }

        userManager.userAddress.asLiveData().observe(viewLifecycleOwner) {
            tvAddress.text = it
        }
        userManager.userUmur.asLiveData().observe(viewLifecycleOwner) {
            tvUmur.text = it
        }
    }


}