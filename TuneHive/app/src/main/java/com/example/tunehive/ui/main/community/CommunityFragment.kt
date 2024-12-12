package com.example.tunehive.ui.main.community

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tunehive.R
import com.example.tunehive.data.response.Post
import com.example.tunehive.databinding.FragmentCommunityBinding
import com.example.tunehive.ui.adapter.PostAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CommunityFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var postAdapter: PostAdapter
    private lateinit var posts: List<Post> // Replace 'Post' with your actual data model class
    private lateinit var fab: FloatingActionButton
    private lateinit var btnUploadLagu: FloatingActionButton
    private lateinit var btnPosting: FloatingActionButton
    private lateinit var overlayBackground: View
    private lateinit var buttonsContainer: View
    private lateinit var btnUploadLaguContainer: View
    private lateinit var btnPostingContainer: View

    private val args: CommunityFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the fragment layout
        val binding = FragmentCommunityBinding.inflate(inflater, container, false)

        // Initialize RecyclerView
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        postAdapter = PostAdapter()
        recyclerView.adapter = postAdapter

        // Sample data for posts
        posts = listOf(
            Post("Danang Ihsan", "12 Apr 20", "Komen yang udah dengerin lagu terbaru kita"),
            Post("Danang Ihsan", "12 Apr 20", "Komen yang udah dengerin lagu terbaru kita"),
            Post("Danang Ihsan", "12 Apr 20", "Komen yang udah dengerin lagu terbaru kita"),
            // Add more posts as necessary
        )
        if (args.postText.isNotEmpty()) {
            posts = posts + Post("Danang Ihsan", "12 Apr 20", args.postText) // Add the new post
        }
        postAdapter.submitList(posts)

        // Initialize the FAB and other buttons
        fab = binding.fab
        btnUploadLagu = binding.btnUploadLagu
        btnPosting = binding.btnPosting
        overlayBackground = binding.overlayBackground
        buttonsContainer = binding.buttonsContainer
        btnUploadLaguContainer = binding.btnUploadLaguContainer
        btnPostingContainer = binding.btnPostingContainer

        // Set up the Floating Action Button (FAB)
        fab.setOnClickListener {
            if (fab.tag != "close") {
                showButtonsAndOverlay()
            } else {
                // If the FAB is in "close" state, hide the buttons and overlay
                hideButtonsAndOverlay()
            }
        }

        btnPosting.setOnClickListener {
            // Navigate to PostFragment
            findNavController().navigate(R.id.action_navigation_community_to_postFragment)
        }

        btnUploadLagu.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_community_to_uploadMusicFragment)
        }

        return binding.root
    }

    private fun showButtonsAndOverlay() {
        // Show the overlay and buttons
        overlayBackground.visibility = View.VISIBLE
        val fadeIn = ObjectAnimator.ofFloat(overlayBackground, "alpha", 0f, 1f)
        fadeIn.duration = 300
        fadeIn.start()

        // Show the "Upload Lagu" container and animate it to appear
        btnUploadLaguContainer.apply {
            visibility = View.VISIBLE
            animate().translationY(-0f).setDuration(300).start()
        }

        // Show the "Posting" container and animate it to appear
        btnPostingContainer.apply {
            visibility = View.VISIBLE
            animate().translationY(-0f).setDuration(300).start()
        }

        // Change FAB icon to "close" (optional)
        fab.setImageResource(R.drawable.ic_close) // Change FAB icon to close button
        fab.tag = "close"

        // Optionally, set click action to hide the buttons and overlay
        overlayBackground.setOnClickListener {
            hideButtonsAndOverlay()
        }
    }

    private fun hideButtonsAndOverlay() {
        // Hide the overlay and buttons
        val fadeOut = ObjectAnimator.ofFloat(overlayBackground, "alpha", 1f, 0f)
        fadeOut.duration = 300
        fadeOut.start()

        btnUploadLaguContainer.apply {
            visibility = View.GONE
            animate().translationY(0f).setDuration(300).start()
        }
        btnPostingContainer.apply {
            visibility = View.GONE
            animate().translationY(0f).setDuration(300).start()
        }

        // Reset FAB icon back to "add" (optional)
        fab.setImageResource(R.drawable.ic_add)
        fab.tag = null
    }
}
