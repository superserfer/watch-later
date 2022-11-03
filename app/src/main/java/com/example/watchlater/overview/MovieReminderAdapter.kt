package com.example.watchlater.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.watchlater.MovieReminder
import com.example.watchlater.databinding.ListItemMovieReminderBinding
import com.squareup.picasso.Picasso

class MovieReminderAdapter(private val clickListener: MovieReminderListener) : ListAdapter<MovieReminder, MovieReminderAdapter.ViewHolder>(MovieReminderDiffCallback()) {

    class ViewHolder private constructor(private val binding: ListItemMovieReminderBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: MovieReminderListener, item: MovieReminder) {
            binding.movieReminder = item
            binding.clickListener = clickListener
            Picasso.get().load(item.posterUrl).into(binding.poster)
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemMovieReminderBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener, item)
    }
}

class MovieReminderDiffCallback : DiffUtil.ItemCallback<MovieReminder>() {
    override fun areItemsTheSame(oldItem: MovieReminder, newItem: MovieReminder): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieReminder, newItem: MovieReminder): Boolean {
        return oldItem == newItem
    }
}

class MovieReminderListener(val clickListener: (movieReminder: MovieReminder) -> Unit) {
    fun onClick(movieReminder: MovieReminder) = clickListener(movieReminder)
}