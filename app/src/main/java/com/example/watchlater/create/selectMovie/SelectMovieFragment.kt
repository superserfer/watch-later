package com.example.watchlater.create.selectMovie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.watchlater.create.CreateViewModel
import com.example.watchlater.databinding.FragmentSelectMovieBinding

class SelectMovieFragment : Fragment() {

    private lateinit var viewModel: CreateViewModel
    private lateinit var adapter: MovieAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(requireActivity(), CreateViewModel.Factory(requireActivity().application))[CreateViewModel::class.java]
        val binding = FragmentSelectMovieBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchMovie(query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        adapter = MovieAdapter(MovieListener {
            viewModel.setMovie(it)
            findNavController().navigate(SelectMovieFragmentDirections.actionSelectMovieFragmentToCreateFragment())
        })

        viewModel.searchedMovie.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }

        binding.movieSelectRecycler.adapter = adapter
        return binding.root
    }
}