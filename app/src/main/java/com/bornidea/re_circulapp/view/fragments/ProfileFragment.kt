package com.bornidea.re_circulapp.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bornidea.re_circulapp.R
import com.bornidea.re_circulapp.databinding.FragmentProfileBinding
import com.bornidea.re_circulapp.model.utils.Constants.METODO
import com.bornidea.re_circulapp.model.utils.Constants.USER
import com.bornidea.re_circulapp.view.activities.Login
import com.bornidea.re_circulapp.view.activities.Registro.Companion.METODO_EMAIL
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

        /**Cerrar sesión*/
        binding.btLogout.setOnClickListener {
            val preferences =
                requireActivity().getSharedPreferences(USER, AppCompatActivity.MODE_PRIVATE)
            val metodo = preferences.getString(METODO, "")
            if (metodo == METODO_EMAIL || metodo == "") {
                val editor = preferences.edit()
                editor.putBoolean("isActive", false)
                editor.apply()

                startActivity(
                    Intent(
                        requireContext(),
                        Login::class.java
                    ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                )
            } else {
                val editor = preferences.edit()
                editor.putBoolean("isActive", false)
                editor.apply()

                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
                val googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
                googleSignInClient.signOut()

                startActivity(
                    Intent(
                        requireContext(),
                        Login::class.java
                    ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                )
            }
        }

        return binding.root
    }
}