package com.example.watchlater.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.watchlater.R
import com.example.watchlater.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this, DetailViewModel.Factory(requireActivity().application))[DetailViewModel::class.java]
        val binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel

        val movieReminder = DetailFragmentArgs.fromBundle(requireArguments()).selectedMovieReminder
        Picasso.get().load(movieReminder.posterUrl).into(binding.imageView)
        binding.movieReminder = movieReminder

        viewModel.navigateBack.observe(viewLifecycleOwner) {
            if (it == true) {
                findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToOverviewFragment())
            }
        }

        return binding.root
    }
}