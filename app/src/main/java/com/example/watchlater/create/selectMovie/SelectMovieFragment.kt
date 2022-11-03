package com.example.watchlater.create.selectMovie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.watchlater.R
import com.example.watchlater.create.CreateViewModel
import com.example.watchlater.databinding.FragmentSelectMovieBinding

class SelectMovieFragment : Fragment() {

    private lateinit var viewModel: CreateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[CreateViewModel::class.java]
        val binding = FragmentSelectMovieBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        // Inflate the layout for this fragment
        // TODO: Create RecyclerView for searchResults
        return binding.root
    }
}