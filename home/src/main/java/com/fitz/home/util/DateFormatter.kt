package com.fitz.home.util

import java.text.DateFormat
import java.util.*

object DateFormatter {

    fun Date.format(): String {
        val formatter = DateFormat.getDateInstance(DateFormat.SHORT)
        return formatter.format(this)
    }
}