package com.unknownadii.injecteat.Utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar


class UtilService {

    fun hideKeyboard(view: View, activity: Activity) {
        try {
            val inputMethodManager: InputMethodManager =
                activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun showSnackBar(view: View?, msg: String?) {
        if (view != null) {
            Snackbar.make(view, msg!!, Snackbar.LENGTH_LONG).show()
        }
    }
}
