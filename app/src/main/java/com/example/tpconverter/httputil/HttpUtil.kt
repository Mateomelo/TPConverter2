package com.example.tpconverter.httputil

import okhttp3.OkHttpClient
import okhttp3.Request

import java.io.IOException

/**
 * HTTP utility methods
 */
object HttpUtil {

    /**
     * Read URL content
     *
     * @param url The URL
     * @return The content as raw string
     */
    fun readUrl(url: String): String {
        val client = OkHttpClient()
        var content = ""

        try {
            val request = Request.Builder()
                .url(url)
                .build()
            val response = client.newCall(request).execute()
            content = response.body()!!.string()

        } catch (e: IOException) {
            e.printStackTrace()
        }

        return content
    }
}
