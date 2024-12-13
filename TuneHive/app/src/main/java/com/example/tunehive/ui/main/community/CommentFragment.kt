package com.example.tunehive.ui.main.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tunehive.R
import com.example.tunehive.data.response.CommentResponseItem
import com.example.tunehive.data.retrofit.ApiConfig
import com.example.tunehive.databinding.FragmentCommentBinding
import com.example.tunehive.ui.adapter.CommentAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.widget.EditText
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class CommentFragment : Fragment() {

    private lateinit var binding: FragmentCommentBinding
    private lateinit var commentsRecyclerView: RecyclerView
    private lateinit var commentAdapter: CommentAdapter
    private lateinit var commentInput: EditText
    private lateinit var replyButton: Button
    private lateinit var profilePicture: ImageView
    private lateinit var userName: TextView
    private lateinit var likeCount: TextView

    private val args: CommentFragmentArgs by navArgs() // Fetching post content passed from CommunityFragment

//    private var postId: Int = 0 // This will be passed from CommunityFragment
//    private var postText: String = ""
//    private var profilePicUrl: String? = args.userAvatar
//    private var usernameText: String? = args.userName
//    private var likeCountText: Int = args.likeCount
    private var commentsList: List<CommentResponseItem> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout
        binding = FragmentCommentBinding.inflate(inflater, container, false)

        // Get the post content passed from CommunityFragment
        val postText = args.postText // This is the post content passed to the fragment
        val postId = args.postId // The post ID to fetch comments
        val userAvatar = args.userAvatar // Assuming you have this
        val userNameText = args.userName // Assuming you have this
        val likeCountText = args.likeCount

        // Set up views
        commentsRecyclerView = binding.commentsRecyclerView
        commentInput = binding.commentInput
        replyButton = binding.replyButton
        profilePicture = binding.profilePicture
        userName = binding.userName
        likeCount = binding.likeCount

//        commentsRecyclerView.layoutManager = LinearLayoutManager(context)
//        commentAdapter = CommentAdapter()
//        commentsRecyclerView.adapter = commentAdapter

        // Display post content
//        binding.postText.text = postText
//        userName.text = usernameText
//        likeCount.text = likeCountText.toString()

        userName.text = userNameText
        likeCount.text = args.likeCount.toString()

        Glide.with(this)
            .load(userAvatar) // Load the image from URL
            .placeholder(R.drawable.ic_person_white) // Placeholder image while loading
            .into(profilePicture)

        // Fetch comments for the post
        fetchCommentsFromApi()

        // Handle comment submission
        replyButton.setOnClickListener {
            val commentText = commentInput.text.toString()
            if (commentText.isNotEmpty()) {
                createComment(args.postId, commentText)
            } else {
                Toast.makeText(context, "Please write a comment", Toast.LENGTH_SHORT).show()
            }
        }

        // Handle back button click
        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_commentFragment_to_communityFragment)
        }

        return binding.root
    }

    private fun fetchCommentsFromApi() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    ApiConfig.getApiService().getComments(args.postId)
                }
                commentsList = response
                commentAdapter.submitList(commentsList)
            } catch (e: Exception) {
                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createComment(postId: Int, commentText: String) {
        val comment = CommentResponseItem(content = commentText) // Create comment object

        // Launching a coroutine to call the suspend function
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    ApiConfig.getApiService().createComment(postId, comment)
                }

                if (response.isSuccessful) {
                    commentsList = commentsList + response.body()!!
                    commentAdapter.submitList(commentsList) // Update RecyclerView with new comment
                    commentInput.text.clear() // Clear the input field
                } else {
                    Toast.makeText(context, "Failed to create comment", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
