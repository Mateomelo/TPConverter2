package com.example.tpconverter.io

import android.content.Context
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

/**
 * Utility methods for file management
 * Created by Hervé Inisan
 */

object IoUtil {
    /**
     * Read text file (file is stored as resource under folder "main/assets"
     *
     * @param context   Context
     * @param path      File path, relative to "main/assets"
     * @return File content
     */
    fun readTextFile(context: Context, path: String): String {
        // StringBuilder to gather file content
        val sb = StringBuilder()
        var str: String?

        try {
            val inputStream = context.assets.open(path)
            val bufferedReader = BufferedReader(InputStreamReader(inputStream, StandardCharsets.UTF_8))

            // Read file and add each line to the StringBuilder
            str = bufferedReader.readLine()
            while (str != null) {
                sb.append(str)
                str = bufferedReader.readLine()
            }

            bufferedReader.close()

        } catch (e: IOException) {
            e.printStackTrace()
        }

        // Final file content
        return sb.toString()
    }
}
