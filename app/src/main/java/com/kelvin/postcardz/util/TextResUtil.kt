package com.kelvin.postcardz.util

import android.content.Context
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.Toast
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TextResUtil @Inject constructor(
    @ApplicationContext val context: Context
) {
    fun getStringFromRes(stringRes: Int): String? = context.getString(stringRes)

    fun showToast(message: String, duration: Int = Toast.LENGTH_LONG) =
        Toast.makeText(context, message, duration).show()

    fun showToast(messageRes: Int, duration: Int = Toast.LENGTH_LONG) =
        Toast.makeText(context, messageRes, duration).show()

    fun createUnderlineText(
        text: String,
        fromIndex: Int = 0,
        toIndex: Int = text.length
    ): SpannableString {
        val spannableString = SpannableString(text)
        spannableString.setSpan(UnderlineSpan(), fromIndex, toIndex, 0)
        return spannableString
    }
}