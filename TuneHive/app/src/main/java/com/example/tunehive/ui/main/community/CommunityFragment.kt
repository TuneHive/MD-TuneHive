package com.example.tunehive.ui.main.community

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tunehive.R
import com.example.tunehive.data.response.Post
import com.example.tunehive.data.response.PostResponseItem
import com.example.tunehive.data.retrofit.ApiConfig
import com.example.tunehive.databinding.FragmentCommunityBinding
import com.example.tunehive.ui.adapter.PostAdapter
import com.example.tunehive.ui.main.TokenViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommunityFragment : Fragment() {

    private lateinit var communityViewModel: CommunityViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var postAdapter: PostAdapter
    private var posts: List<PostResponseItem> = listOf()
    private lateinit var fab: FloatingActionButton
    private lateinit var btnUploadLagu: FloatingActionButton
    private lateinit var btnPosting: FloatingActionButton
    private lateinit var overlayBackground: View
    private lateinit var buttonsContainer: View
    private lateinit var btnUploadLaguContainer: View
    private lateinit var btnPostingContainer: View

//    private lateinit var tokenViewModel: TokenViewModel
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

//        tokenViewModel = ViewModelProvider(requireActivity()).get(TokenViewModel::class.java)
//        fetchPostsFromApi()
        communityViewModel = ViewModelProvider(this).get(CommunityViewModel::class.java)

        communityViewModel.posts.observe(viewLifecycleOwner, Observer{
            postAdapter.submitList(it)
        })

        communityViewModel.fetchPosts()

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

        postAdapter.setOnItemClickListener { post ->
            val action = CommunityFragmentDirections.actionNavigationCommunityToCommentFragment(
                postId = post.id ?: 0,
                postText = post.content ?: "",
                userAvatar = "ic_person_white",  // assuming there's an avatar URL
                userName = post.user?.fullname ?: "",
                likeCount = post.likeCount ?: 0
            )
            findNavController().navigate(action)
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
