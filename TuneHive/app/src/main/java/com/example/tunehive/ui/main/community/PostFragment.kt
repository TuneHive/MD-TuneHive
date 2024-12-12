package com.example.tunehive.ui.main.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tunehive.R

class PostFragment : Fragment() {

    private lateinit var editText: EditText
    private lateinit var postButton: Button
    private lateinit var backButton: AppCompatImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = inflater.inflate(R.layout.fragment_post, container, false)

        // Initialize the EditText and Button
        editText = binding.findViewById(R.id.editTextPost)
        postButton = binding.findViewById(R.id.btnPost)
        backButton = binding.findViewById(R.id.backButton)

        // Handle the "Post" button click
        postButton.setOnClickListener {
            // Perform the posting action
            val postText = editText.text.toString()

            // Here you can send the post data to your server or database
            // Then, navigate back to the CommunityFragment
            if (postText.isNotEmpty()) {
                val action = PostFragmentDirections.actionPostFragmentToCommunityFragment(postText)
                findNavController().navigate(action)
            }
        }
        backButton.setOnClickListener {
            // Navigate back to CommunityFragment
            findNavController().popBackStack()
        }

        return binding
    }
}
