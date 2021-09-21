package com.fitz.core.viewutils

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateFormatter {

    fun Date.format(): String {
        val formatter = DateFormat.getDateInstance(DateFormat.SHORT)
        return formatter.format(this)
    }

    @Throws(ParseException::class)
    fun String.formatToDate(): Date {
        val formatter = SimpleDateFormat.getDateInstance(DateFormat.SHORT)
        return formatter.parse(this) ?: throw ParseException("failed to parse $this", -1)
    }
}