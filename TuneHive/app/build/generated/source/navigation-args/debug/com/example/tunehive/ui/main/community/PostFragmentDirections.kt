package com.example.tunehive.ui.main.community

import android.os.Bundle
import androidx.navigation.NavDirections
import com.example.tunehive.R
import kotlin.Int
import kotlin.String

public class PostFragmentDirections private constructor() {
  private data class ActionPostFragmentToCommunityFragment(
    public val postText: String,
    public val userName: String,
    public val likeCount: Int,
    public val postId: Int = 0,
    public val userAvatar: String = "ic_person_white"
  ) : NavDirections {
    public override val actionId: Int = R.id.action_postFragment_to_communityFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("postText", this.postText)
        result.putInt("postId", this.postId)
        result.putString("userAvatar", this.userAvatar)
        result.putString("userName", this.userName)
        result.putInt("likeCount", this.likeCount)
        return result
      }
  }

  public companion object {
    public fun actionPostFragmentToCommunityFragment(
      postText: String,
      userName: String,
      likeCount: Int,
      postId: Int = 0,
      userAvatar: String = "ic_person_white"
    ): NavDirections = ActionPostFragmentToCommunityFragment(postText, userName, likeCount, postId,
        userAvatar)
  }
}
