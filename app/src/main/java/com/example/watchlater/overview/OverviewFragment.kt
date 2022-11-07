package com.example.watchlater.overview

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.watchlater.R
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.watchlater.MainActivity
import com.example.watchlater.MovieReminder
import com.example.watchlater.databinding.FragmentOverviewBinding

class OverviewFragment : Fragment() {

    companion object {
        fun newInstance() = OverviewFragment()
    }

    private lateinit var binding: FragmentOverviewBinding
    private lateinit var adapter: MovieReminderAdapter
    private lateinit var viewModel: OverviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val movieReminder = requireActivity().intent.getSerializableExtra(MainActivity.EXTRA_MovieReminderDataItem) as MovieReminder?
        if (movieReminder != null) {
            findNavController().navigate(OverviewFragmentDirections.actionOverviewFragmentToDetailFragment(movieReminder))
        }

        viewModel = ViewModelProvider(this, OverviewViewModel.Factory(requireActivity().application))[OverviewViewModel::class.java]
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_overview, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        adapter = MovieReminderAdapter(MovieReminderListener {
            findNavController().navigate(OverviewFragmentDirections.actionOverviewFragmentToDetailFragment(it))
        })

        binding.movieReminderRecycler.adapter = adapter

        viewModel.movieReminders.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }

        binding.addMovieReminderFab.setOnClickListener {
            findNavController().navigate(OverviewFragmentDirections.actionOverviewFragmentToCreateFragment())
        }

        return binding.root
    }

}