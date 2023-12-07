package com.example.admin.ui.home

import UserAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.admin.DatabaseRoom.Database.AppDatabase
import com.example.admin.DatabaseRoom.Entity.User
import com.example.admin.DatabaseRoom.ViewModel.UserViewModel
import com.example.admin.DatabaseRoom.ViewModel.UserViewModelFactory
import com.example.admin.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), UserAdapter.OnDeleteClickListener {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        userAdapter = UserAdapter(this) // Meneruskan `this` sebagai `OnDeleteClickListener`

        binding.rvUser.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = userAdapter
        }

        userViewModel = ViewModelProvider(
            requireActivity(),
            UserViewModelFactory(AppDatabase.getInstance(requireContext()).userDao())
        ).get(UserViewModel::class.java)
        userViewModel.allUsers.observe(viewLifecycleOwner, Observer { users ->
            userAdapter.submitList(users)
        })

        return binding.root
    }

    override fun onDeleteClick(user: User) {
        userViewModel.deleteUser(user)
    }
}