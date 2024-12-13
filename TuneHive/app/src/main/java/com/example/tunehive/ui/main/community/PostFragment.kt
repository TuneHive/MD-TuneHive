package com.example.tunehive.ui.main.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tunehive.R

class PostFragment : Fragment() {

    private lateinit var postViewModel: PostViewModel
    private lateinit var editText: EditText
    private lateinit var postButton: Button
    private lateinit var backButton: AppCompatImageButton

    private val args: PostFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = inflater.inflate(R.layout.fragment_post, container, false)

        postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)

        // Initialize the EditText and Button
        editText = binding.findViewById(R.id.editTextPost)
        postButton = binding.findViewById(R.id.btnPost)
        backButton = binding.findViewById(R.id.backButton)

        val userName = args.userName
        val likeCount = args.likeCount
        val userId = args.userId

        // Handle the "Post" button click
        postButton.setOnClickListener {
            // Perform the posting action
            val postText = editText.text.toString()

            // Check if postText is not empty
            if (postText.isNotEmpty() && userId > 0) {
                // Make the API call to post the content
                postViewModel.createPost(postText, userName, userId)
            } else {
                val errorMsg = if (userId <= 0) {
                    "Invalid user ID"
                } else {
                    "Please enter post content"
                }
                Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
            }
        }
        postViewModel.postCreationResponse.observe(viewLifecycleOwner, Observer { response ->
            if (response != null && response.isSuccessful) {
                // Post successfully created
                Toast.makeText(context, "Post created", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()  // Navigate back to the previous fragment (CommunityFragment)
            } else {
                val errorMessage = response?.errorBody()?.string() ?: "Failed to create post"
                Toast.makeText(context, "Error: $errorMessage", Toast.LENGTH_SHORT).show()
            }
        })

        backButton.setOnClickListener {
            // Navigate back to CommunityFragment
            findNavController().popBackStack()
        }

        return binding
    }
}