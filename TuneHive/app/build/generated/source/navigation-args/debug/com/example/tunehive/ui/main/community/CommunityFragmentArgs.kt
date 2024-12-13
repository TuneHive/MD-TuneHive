package com.example.tunehive.ui.main.community

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Int
import kotlin.String
import kotlin.jvm.JvmStatic

public data class CommunityFragmentArgs(
  public val userName: String,
  public val likeCount: Int,
  public val postId: Int = 0,
  public val postText: String = "",
  public val userAvatar: String = "ic_person_white"
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putInt("postId", this.postId)
    result.putString("postText", this.postText)
    result.putString("userAvatar", this.userAvatar)
    result.putString("userName", this.userName)
    result.putInt("likeCount", this.likeCount)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("postId", this.postId)
    result.set("postText", this.postText)
    result.set("userAvatar", this.userAvatar)
    result.set("userName", this.userName)
    result.set("likeCount", this.likeCount)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): CommunityFragmentArgs {
      bundle.setClassLoader(CommunityFragmentArgs::class.java.classLoader)
      val __postId : Int
      if (bundle.containsKey("postId")) {
        __postId = bundle.getInt("postId")
      } else {
        __postId = 0
      }
      val __postText : String?
      if (bundle.containsKey("postText")) {
        __postText = bundle.getString("postText")
        if (__postText == null) {
          throw IllegalArgumentException("Argument \"postText\" is marked as non-null but was passed a null value.")
        }
      } else {
        __postText = ""
      }
      val __userAvatar : String?
      if (bundle.containsKey("userAvatar")) {
        __userAvatar = bundle.getString("userAvatar")
        if (__userAvatar == null) {
          throw IllegalArgumentException("Argument \"userAvatar\" is marked as non-null but was passed a null value.")
        }
      } else {
        __userAvatar = "ic_person_white"
      }
      val __userName : String?
      if (bundle.containsKey("userName")) {
        __userName = bundle.getString("userName")
        if (__userName == null) {
          throw IllegalArgumentException("Argument \"userName\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"userName\" is missing and does not have an android:defaultValue")
      }
      val __likeCount : Int
      if (bundle.containsKey("likeCount")) {
        __likeCount = bundle.getInt("likeCount")
      } else {
        throw IllegalArgumentException("Required argument \"likeCount\" is missing and does not have an android:defaultValue")
      }
      return CommunityFragmentArgs(__userName, __likeCount, __postId, __postText, __userAvatar)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): CommunityFragmentArgs {
      val __postId : Int?
      if (savedStateHandle.contains("postId")) {
        __postId = savedStateHandle["postId"]
        if (__postId == null) {
          throw IllegalArgumentException("Argument \"postId\" of type integer does not support null values")
        }
      } else {
        __postId = 0
      }
      val __postText : String?
      if (savedStateHandle.contains("postText")) {
        __postText = savedStateHandle["postText"]
        if (__postText == null) {
          throw IllegalArgumentException("Argument \"postText\" is marked as non-null but was passed a null value")
        }
      } else {
        __postText = ""
      }
      val __userAvatar : String?
      if (savedStateHandle.contains("userAvatar")) {
        __userAvatar = savedStateHandle["userAvatar"]
        if (__userAvatar == null) {
          throw IllegalArgumentException("Argument \"userAvatar\" is marked as non-null but was passed a null value")
        }
      } else {
        __userAvatar = "ic_person_white"
      }
      val __userName : String?
      if (savedStateHandle.contains("userName")) {
        __userName = savedStateHandle["userName"]
        if (__userName == null) {
          throw IllegalArgumentException("Argument \"userName\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"userName\" is missing and does not have an android:defaultValue")
      }
      val __likeCount : Int?
      if (savedStateHandle.contains("likeCount")) {
        __likeCount = savedStateHandle["likeCount"]
        if (__likeCount == null) {
          throw IllegalArgumentException("Argument \"likeCount\" of type integer does not support null values")
        }
      } else {
        throw IllegalArgumentException("Required argument \"likeCount\" is missing and does not have an android:defaultValue")
      }
      return CommunityFragmentArgs(__userName, __likeCount, __postId, __postText, __userAvatar)
    }
  }
}
