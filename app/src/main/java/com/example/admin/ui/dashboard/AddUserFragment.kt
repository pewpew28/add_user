package com.example.admin.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.admin.DatabaseRoom.Database.AppDatabase
import com.example.admin.DatabaseRoom.Entity.User
import com.example.admin.DatabaseRoom.ViewModel.UserViewModel
import com.example.admin.DatabaseRoom.ViewModel.UserViewModelFactory
import com.example.admin.databinding.FragmentAddUserBinding


class AddUserFragment : Fragment() {
    private lateinit var binding: FragmentAddUserBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddUserBinding.inflate(inflater, container, false)

        userViewModel = ViewModelProvider(
            requireActivity(),
            UserViewModelFactory(AppDatabase.getInstance(requireContext()).userDao())
        ).get(UserViewModel::class.java)

        binding.btnSimpan.setOnClickListener {
            val name = binding.etNama.text.toString()
            val email = binding.etEmail.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty()) {
                userViewModel.insertUser(User(name = name, email = email))
                binding.etNama.text.clear()
                binding.etEmail.text.clear()
            }
        }

        return binding.root
    }
}