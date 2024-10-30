package com.dicoding.applicationdicodingevent.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.dicoding.applicationdicodingevent.data.FavoriteRepository
import com.dicoding.applicationdicodingevent.data.local.room.AppDatabase
import com.dicoding.applicationdicodingevent.data.response.ListEventsItem
import com.dicoding.applicationdicodingevent.databinding.FragmentFavoriteBinding
import com.dicoding.applicationdicodingevent.ui.ColHomeAdapter
import com.dicoding.applicationdicodingevent.ui.DetailEventActivity


class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var viewModel: FavoriteViewModel
    private lateinit var adapter: ColHomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ColHomeAdapter { event ->
            val intent = Intent(requireContext(), DetailEventActivity::class.java)
            intent.putExtra("EVENT_ID", event.id)
            startActivity(intent)
        }
        binding.FavoriteEvent.adapter = adapter
        binding.FavoriteEvent.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        val repository = FavoriteRepository(AppDatabase.getDatabase(requireContext()).favoriteEventDao())
        val factory = FavoriteViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(FavoriteViewModel::class.java)

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
        viewModel.getFavoriteEvents().observe(viewLifecycleOwner) { favoriteEvents ->
            adapter.submitList(favoriteEvents.map { event ->
                ListEventsItem(
                    id = event.id,
                    name = event.name,
                    mediaCover = event.mediaCover
                )
            })
        }
    }
}