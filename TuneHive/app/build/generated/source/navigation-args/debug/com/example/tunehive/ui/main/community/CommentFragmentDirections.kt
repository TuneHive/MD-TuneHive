package com.example.tunehive.ui.main.community

import android.os.Bundle
import androidx.navigation.NavDirections
import com.example.tunehive.R
import kotlin.Int
import kotlin.String

public class CommentFragmentDirections private constructor() {
  private data class ActionCommentFragmentToCommunityFragment(
    public val userName: String,
    public val likeCount: Int,
    public val postId: Int = 0,
    public val postText: String = "",
    public val userAvatar: String = "ic_person_white"
  ) : NavDirections {
    public override val actionId: Int = R.id.action_commentFragment_to_communityFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putInt("postId", this.postId)
        result.putString("postText", this.postText)
        result.putString("userAvatar", this.userAvatar)
        result.putString("userName", this.userName)
        result.putInt("likeCount", this.likeCount)
        return result
      }
  }

  public companion object {
    public fun actionCommentFragmentToCommunityFragment(
      userName: String,
      likeCount: Int,
      postId: Int = 0,
      postText: String = "",
      userAvatar: String = "ic_person_white"
    ): NavDirections = ActionCommentFragmentToCommunityFragment(userName, likeCount, postId,
        postText, userAvatar)
  }
}
