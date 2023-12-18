package com.example.githubdemo.util

import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Class containing companion methods used throughout the application.
 */
class Utils {

    companion object {

        /**
         * Hides the soft keyboard
         *
         * @param context Context
         * @param view View
         */
        fun hideKeyboardFrom(context: Context, view: View) {
            val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }

        /**
         * Animates the view
         *
         * @param view View
         */
        fun animateView(view: View) {
            val floatInAnimator = ObjectAnimator.ofFloat(view, "translationY", 500f, 0f)
            floatInAnimator.duration = 500 // Adjust the duration as needed
            floatInAnimator.start()
        }

        /**
         * Check if string contains alphanumeric characters or not
         *
         * @param input String which needs to be validated
         * @return true of false
         */
        fun isValidString(input: String): Boolean {
            // Define a regular expression to match non-alphanumeric characters (including special characters)
            val regex = Regex("^[a-zA-Z0-9 ]*\$")
            return input.matches(regex)
        }
    }
}