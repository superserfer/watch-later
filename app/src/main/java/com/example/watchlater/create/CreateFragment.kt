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
import com.example.watchlater.R
import com.example.watchlater.api.SimklApi
import com.example.watchlater.databinding.FragmentCreateBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class CreateFragment : Fragment() {

    private lateinit var viewModel: CreateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[CreateViewModel::class.java]
        val binding = FragmentCreateBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.selectMovie.setOnClickListener {
            findNavController().navigate(CreateFragmentDirections.actionCreateFragmentToSelectMovieFragment())
        }

        binding.saveButton.setOnClickListener {
            viewModel.saveMovieReminder()
        }

        binding.datePicker.setOnDateChangedListener { _, year, month, day ->
            viewModel.setDate(year, month, day)
        }

        viewModel.movieSaved.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(requireContext(), "MovieReminder Saved!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(CreateFragmentDirections.actionCreateFragmentToOverviewFragment())
            } else {
                Toast.makeText(requireContext(), "Something went wrong!", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }
}