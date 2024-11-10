package com.example.msareefapp.Ui.Activites.regestertion

import android.view.View
import android.widget.TextView
import com.example.msareefapp.Bases.BaseViewModel

class RegisterViewModel : BaseViewModel() {
    fun animateText(textView: TextView, fullText: String, delay: Long = 100L) {
        textView.text = ""
        for (i in fullText.indices) {
            textView.postDelayed({
                textView.text = fullText.substring(0, i + 1)
            }, i * delay)
        }
    }

    fun animateTextFields(view: View,delay: Long = 50L) {
        view.translationX = view.rootView.width.toFloat()
        view.alpha = 0f

        view.animate().translationX(0f).alpha(1f).setDuration(1000).setStartDelay(delay).start()

    }
}