// Generated by view binder compiler. Do not edit!
package com.example.tunehive.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.tunehive.R;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentLoginBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView goToSignUpBtn;

  @NonNull
  public final ImageView imgBackground;

  @NonNull
  public final Button loginButton;

  @NonNull
  public final EditText passwordEditText;

  @NonNull
  public final TextInputLayout passwordLayout;

  @NonNull
  public final TextView tvFrontSignup;

  @NonNull
  public final EditText usernameEditText;

  @NonNull
  public final TextInputLayout usernameLayout;

  private FragmentLoginBinding(@NonNull ConstraintLayout rootView, @NonNull TextView goToSignUpBtn,
      @NonNull ImageView imgBackground, @NonNull Button loginButton,
      @NonNull EditText passwordEditText, @NonNull TextInputLayout passwordLayout,
      @NonNull TextView tvFrontSignup, @NonNull EditText usernameEditText,
      @NonNull TextInputLayout usernameLayout) {
    this.rootView = rootView;
    this.goToSignUpBtn = goToSignUpBtn;
    this.imgBackground = imgBackground;
    this.loginButton = loginButton;
    this.passwordEditText = passwordEditText;
    this.passwordLayout = passwordLayout;
    this.tvFrontSignup = tvFrontSignup;
    this.usernameEditText = usernameEditText;
    this.usernameLayout = usernameLayout;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentLoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_login, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentLoginBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.goToSignUpBtn;
      TextView goToSignUpBtn = ViewBindings.findChildViewById(rootView, id);
      if (goToSignUpBtn == null) {
        break missingId;
      }

      id = R.id.img_background;
      ImageView imgBackground = ViewBindings.findChildViewById(rootView, id);
      if (imgBackground == null) {
        break missingId;
      }

      id = R.id.loginButton;
      Button loginButton = ViewBindings.findChildViewById(rootView, id);
      if (loginButton == null) {
        break missingId;
      }

      id = R.id.passwordEditText;
      EditText passwordEditText = ViewBindings.findChildViewById(rootView, id);
      if (passwordEditText == null) {
        break missingId;
      }

      id = R.id.passwordLayout;
      TextInputLayout passwordLayout = ViewBindings.findChildViewById(rootView, id);
      if (passwordLayout == null) {
        break missingId;
      }

      id = R.id.tv_front_signup;
      TextView tvFrontSignup = ViewBindings.findChildViewById(rootView, id);
      if (tvFrontSignup == null) {
        break missingId;
      }

      id = R.id.usernameEditText;
      EditText usernameEditText = ViewBindings.findChildViewById(rootView, id);
      if (usernameEditText == null) {
        break missingId;
      }

      id = R.id.usernameLayout;
      TextInputLayout usernameLayout = ViewBindings.findChildViewById(rootView, id);
      if (usernameLayout == null) {
        break missingId;
      }

      return new FragmentLoginBinding((ConstraintLayout) rootView, goToSignUpBtn, imgBackground,
          loginButton, passwordEditText, passwordLayout, tvFrontSignup, usernameEditText,
          usernameLayout);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}