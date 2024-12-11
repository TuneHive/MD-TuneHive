package com.example.tunehive.ui.main.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tunehive.databinding.FragmentHomeBinding
import com.example.tunehive.ui.adapter.SongAdapter
import com.example.tunehive.ui.main.TokenViewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var songAdapter: SongAdapter
    private lateinit var recentSearchAdapter: SongAdapter
    private  val tokenViewModel: TokenViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        songAdapter = SongAdapter()
        binding.mostPopularRecyclerView.adapter = songAdapter
        binding.mostPopularRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Initialize the adapter for recent searches
        recentSearchAdapter = SongAdapter()
        binding.recentSearchesRecyclerView.adapter = recentSearchAdapter
        binding.recentSearchesRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        homeViewModel.filteredSongs.observe(viewLifecycleOwner) { songs ->
            songAdapter.submitList(songs)
        }

        homeViewModel.recentSearches.observe(viewLifecycleOwner) { recentSearches ->
            if (recentSearches.isEmpty()) {
                binding.recentSearchesLayout.visibility = View.GONE
            } else {
                binding.recentSearchesLayout.visibility = View.VISIBLE
                recentSearchAdapter.submitList(recentSearches)
            }
            toggleSectionsVisibility(binding.recentSearchesLayout.visibility == View.VISIBLE) // Toggle visibility based on Recent Searches
        }

        // Handle the visibility of the Recent Searches layout
        binding.searchBar.setOnClickListener {
            toggleRecentSearchesVisibility()
        }

        // Handle the text change for search functionality
        binding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                homeViewModel.search(s.toString())
                toggleSectionsVisibility(binding.recentSearchesLayout.visibility == View.VISIBLE) // Update visibility while searching
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        // Handle song clicks (add to recent searches)
        songAdapter.setOnItemClickListener { song ->
            homeViewModel.addRecentSearch(song)
        }

        // Load initial data
        homeViewModel.search("")
    }

    private fun toggleRecentSearchesVisibility() {
        // Toggle the visibility of the recent searches section
        if (binding.recentSearchesLayout.visibility == View.GONE) {
            binding.recentSearchesLayout.visibility = View.VISIBLE
        } else {
            binding.recentSearchesLayout.visibility = View.GONE
        }
        toggleSectionsVisibility(binding.recentSearchesLayout.visibility == View.VISIBLE)
    }

    private fun toggleSectionsVisibility(isRecentSearchesVisible: Boolean) {
        // Hide Most Popular and Recommendation sections if Recent Searches is visible
        if (isRecentSearchesVisible) {
            binding.mostPopularLabel.visibility = View.GONE
            binding.viewAllText.visibility = View.GONE
            binding.mostPopularRecyclerView.visibility = View.GONE
            binding.recommendationLabel.visibility = View.GONE
            binding.recommendationRecyclerView.visibility = View.GONE
        } else {
            binding.mostPopularLabel.visibility = View.VISIBLE
            binding.mostPopularRecyclerView.visibility = View.VISIBLE
            binding.recommendationLabel.visibility = View.VISIBLE
            binding.recommendationRecyclerView.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
