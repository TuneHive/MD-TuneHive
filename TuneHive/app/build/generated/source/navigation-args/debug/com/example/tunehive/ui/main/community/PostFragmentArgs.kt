package com.example.tunehive.ui.main.community

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Int
import kotlin.String
import kotlin.jvm.JvmStatic

public data class PostFragmentArgs(
  public val userName: String = "",
  public val likeCount: Int = 0,
  public val userId: Int = 0
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("userName", this.userName)
    result.putInt("likeCount", this.likeCount)
    result.putInt("userId", this.userId)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("userName", this.userName)
    result.set("likeCount", this.likeCount)
    result.set("userId", this.userId)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): PostFragmentArgs {
      bundle.setClassLoader(PostFragmentArgs::class.java.classLoader)
      val __userName : String?
      if (bundle.containsKey("userName")) {
        __userName = bundle.getString("userName")
        if (__userName == null) {
          throw IllegalArgumentException("Argument \"userName\" is marked as non-null but was passed a null value.")
        }
      } else {
        __userName = ""
      }
      val __likeCount : Int
      if (bundle.containsKey("likeCount")) {
        __likeCount = bundle.getInt("likeCount")
      } else {
        __likeCount = 0
      }
      val __userId : Int
      if (bundle.containsKey("userId")) {
        __userId = bundle.getInt("userId")
      } else {
        __userId = 0
      }
      return PostFragmentArgs(__userName, __likeCount, __userId)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): PostFragmentArgs {
      val __userName : String?
      if (savedStateHandle.contains("userName")) {
        __userName = savedStateHandle["userName"]
        if (__userName == null) {
          throw IllegalArgumentException("Argument \"userName\" is marked as non-null but was passed a null value")
        }
      } else {
        __userName = ""
      }
      val __likeCount : Int?
      if (savedStateHandle.contains("likeCount")) {
        __likeCount = savedStateHandle["likeCount"]
        if (__likeCount == null) {
          throw IllegalArgumentException("Argument \"likeCount\" of type integer does not support null values")
        }
      } else {
        __likeCount = 0
      }
      val __userId : Int?
      if (savedStateHandle.contains("userId")) {
        __userId = savedStateHandle["userId"]
        if (__userId == null) {
          throw IllegalArgumentException("Argument \"userId\" of type integer does not support null values")
        }
      } else {
        __userId = 0
      }
      return PostFragmentArgs(__userName, __likeCount, __userId)
    }
  }
}
