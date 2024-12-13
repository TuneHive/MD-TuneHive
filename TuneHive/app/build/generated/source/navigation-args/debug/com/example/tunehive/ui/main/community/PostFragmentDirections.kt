package com.example.tunehive.ui.main.community

import android.os.Bundle
import androidx.navigation.NavDirections
import com.example.tunehive.R
import kotlin.Int
import kotlin.String

public class PostFragmentDirections private constructor() {
  private data class ActionPostFragmentToCommunityFragment(
    public val postText: String
  ) : NavDirections {
    public override val actionId: Int = R.id.action_postFragment_to_communityFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("postText", this.postText)
        return result
      }
  }

  public companion object {
    public fun actionPostFragmentToCommunityFragment(postText: String): NavDirections =
        ActionPostFragmentToCommunityFragment(postText)
  }
}
