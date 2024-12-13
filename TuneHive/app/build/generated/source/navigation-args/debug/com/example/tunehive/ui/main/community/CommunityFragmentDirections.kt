package com.example.tunehive.ui.main.community

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.tunehive.R

public class CommunityFragmentDirections private constructor() {
  public companion object {
    public fun actionNavigationCommunityToPostFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_navigation_community_to_postFragment)

    public fun actionNavigationCommunityToUploadMusicFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_navigation_community_to_uploadMusicFragment)
  }
}
