package com.example.watchlater.create

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.watchlater.BuildConfig
import com.example.watchlater.MovieReminder
import com.example.watchlater.R
import com.example.watchlater.api.SimklApi
import com.example.watchlater.databinding.FragmentCreateBinding
import com.example.watchlater.utils.sendNotification
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class CreateFragment : Fragment() {

    private lateinit var viewModel: CreateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(requireActivity(), CreateViewModel.Factory(requireActivity().application))[CreateViewModel::class.java]
        val binding = FragmentCreateBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.selectMovie.setOnClickListener {
            findNavController().navigate(CreateFragmentDirections.actionCreateFragmentToSelectMovieFragment())
        }

        binding.saveButton.setOnClickListener {
            if (viewModel.hasSetMovie.value == true && viewModel.hasSetDate.value == true) {
                viewModel.saveMovieReminder()
                Toast.makeText(requireContext(), "MovieReminder Saved!", Toast.LENGTH_SHORT).show()
                sendNotification(requireContext(),
                MovieReminder(
                    title = "Test",
                    releaseYear = 2012,
                    reminderDataInput = "2022 12 1",
                    posterUrl = "https://developer.android.com/static/training/material/images/fab.png"
                )
                )
                findNavController().navigate(CreateFragmentDirections.actionCreateFragmentToOverviewFragment())
            } else {
                Toast.makeText(requireContext(), "Please Provide the Movie & Date!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.datePicker.setOnDateChangedListener { _, year, month, day ->
            viewModel.setDate(year, month, day)
        }

        return binding.root
    }
}