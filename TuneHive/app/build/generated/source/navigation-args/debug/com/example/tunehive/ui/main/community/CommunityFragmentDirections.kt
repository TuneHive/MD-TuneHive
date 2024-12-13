package com.example.tunehive.ui.main.community

import android.os.Bundle
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.tunehive.R
import kotlin.Int
import kotlin.String

public class CommunityFragmentDirections private constructor() {
  private data class ActionNavigationCommunityToPostFragment(
    public val userName: String = "",
    public val likeCount: Int = 0,
    public val userId: Int = 0
  ) : NavDirections {
    public override val actionId: Int = R.id.action_navigation_community_to_postFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("userName", this.userName)
        result.putInt("likeCount", this.likeCount)
        result.putInt("userId", this.userId)
        return result
      }
  }

  private data class ActionNavigationCommunityToCommentFragment(
    public val userName: String,
    public val likeCount: Int,
    public val postId: Int = 0,
    public val postText: String = "",
    public val userAvatar: String = "ic_person_white"
  ) : NavDirections {
    public override val actionId: Int = R.id.action_navigation_community_to_commentFragment

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
    public fun actionNavigationCommunityToPostFragment(
      userName: String = "",
      likeCount: Int = 0,
      userId: Int = 0
    ): NavDirections = ActionNavigationCommunityToPostFragment(userName, likeCount, userId)

    public fun actionNavigationCommunityToUploadMusicFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_navigation_community_to_uploadMusicFragment)

    public fun actionNavigationCommunityToCommentFragment(
      userName: String,
      likeCount: Int,
      postId: Int = 0,
      postText: String = "",
      userAvatar: String = "ic_person_white"
    ): NavDirections = ActionNavigationCommunityToCommentFragment(userName, likeCount, postId,
        postText, userAvatar)
  }
}
