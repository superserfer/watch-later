package com.example.watchlater.overview

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.watchlater.R
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
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
        viewModel = ViewModelProvider(this)[OverviewViewModel::class.java]
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_overview, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        adapter = MovieReminderAdapter(MovieReminderListener {  })
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