package com.junaid.todoapp.utils.app


import android.content.Context
import android.widget.Toast

import androidx.fragment.app.Fragment

import com.google.android.material.snackbar.Snackbar





fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}


fun Fragment.showToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun Fragment.showSnackBar(message: String) {
    this?.view?.let {
        Snackbar.make(this.requireView(), message, Snackbar.LENGTH_SHORT).show()
    } ?: showToast(message)

}



