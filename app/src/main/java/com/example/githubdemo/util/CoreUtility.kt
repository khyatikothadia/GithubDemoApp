package com.example.githubdemo.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

object CoreUtility {

    fun isInternetConnected(context: Context): Boolean {

        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork ?: return false

        val actNetwork =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        val result = when {
            actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
        return result
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

    fun getFormattedDate(inputDate: String): String {
        return if (inputDate.isNotEmpty()) {
            val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH)
            val instant = Instant.parse(inputDate)
            val localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime()
            formatter.format(localDateTime)
        } else ""
    }

}

