package com.example.tunehive.ui.main.community

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

public data class CommunityFragmentArgs(
  public val postText: String = ""
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("postText", this.postText)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("postText", this.postText)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): CommunityFragmentArgs {
      bundle.setClassLoader(CommunityFragmentArgs::class.java.classLoader)
      val __postText : String?
      if (bundle.containsKey("postText")) {
        __postText = bundle.getString("postText")
        if (__postText == null) {
          throw IllegalArgumentException("Argument \"postText\" is marked as non-null but was passed a null value.")
        }
      } else {
        __postText = ""
      }
      return CommunityFragmentArgs(__postText)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): CommunityFragmentArgs {
      val __postText : String?
      if (savedStateHandle.contains("postText")) {
        __postText = savedStateHandle["postText"]
        if (__postText == null) {
          throw IllegalArgumentException("Argument \"postText\" is marked as non-null but was passed a null value")
        }
      } else {
        __postText = ""
      }
      return CommunityFragmentArgs(__postText)
    }
  }
}
