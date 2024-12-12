package com.example.tunehive.ui.main.community

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tunehive.R
import android.provider.OpenableColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class UploadMusicFragment : Fragment() {

    private lateinit var titleInput: EditText
    private lateinit var genreSpinner: Spinner
    private lateinit var uploadSongCoverButton: Button
    private lateinit var uploadAudioButton: Button
    private lateinit var submitButton: Button
    private lateinit var backButton: ImageButton  // Back button to navigate back to the CommunityFragment

    private var songCoverUri: Uri? = null
    private var audioFileUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = inflater.inflate(R.layout.fragment_upload_music, container, false)

        titleInput = binding.findViewById(R.id.titleInput)
        genreSpinner = binding.findViewById(R.id.genreSpinner)
        uploadSongCoverButton = binding.findViewById(R.id.uploadSongCoverButton)
        uploadAudioButton = binding.findViewById(R.id.uploadAudioButton)
        submitButton = binding.findViewById(R.id.submitButton)
        backButton = binding.findViewById(R.id.backButton)  // Initialize the back button

        // Set up the genre spinner with options
        val genres = arrayOf("Pop", "Rock", "House", "Jazz", "R&B")
        val adapter = ArrayAdapter(requireContext(), R.layout.item_spinner, genres)
        adapter.setDropDownViewResource(R.layout.item_spinner_dropdown)
        genreSpinner.adapter = adapter

        // Handle song cover button click
        uploadSongCoverButton.setOnClickListener {
            // Open the device's image picker
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, IMAGE_PICK_REQUEST_CODE)
        }

        // Handle audio file button click
        uploadAudioButton.setOnClickListener {
            // Open the device's file picker for audio
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "audio/*"
            startActivityForResult(intent, AUDIO_PICK_REQUEST_CODE)
        }

        // Handle submit button click
        submitButton.setOnClickListener {
            // Collect the data from inputs
            val title = titleInput.text.toString()
            val genre = genreSpinner.selectedItem.toString()

            if (title.isEmpty() || songCoverUri == null || audioFileUri == null) {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                // Submit data (e.g., upload to server or database)
                // For now, just navigate back to the community fragment
                findNavController().popBackStack()
            }
        }

        // Handle the back button click (navigate back to CommunityFragment)
        backButton.setOnClickListener {
            // Pop the current fragment (UploadMusicFragment) off the back stack
            findNavController().popBackStack()
        }

        return binding
    }

    // Handle the result of the file picker for song cover and audio
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && data != null) {
            when (requestCode) {
                IMAGE_PICK_REQUEST_CODE -> {
                    songCoverUri = data.data
                    uploadSongCoverButton.text = "Image selected"
                }
                AUDIO_PICK_REQUEST_CODE -> {
                    audioFileUri = data.data
                    uploadAudioButton.text = "Audio selected"
                }
            }
        }
    }

    companion object {
        private const val IMAGE_PICK_REQUEST_CODE = 1001
        private const val AUDIO_PICK_REQUEST_CODE = 1002
    }
}
