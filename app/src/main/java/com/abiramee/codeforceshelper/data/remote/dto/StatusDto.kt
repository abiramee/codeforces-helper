package com.abiramee.codeforceshelper.data.remote.dto


import android.util.Log
import com.abiramee.codeforceshelper.domain.model.AcceptedItem
import com.abiramee.codeforceshelper.domain.model.Status
import com.google.gson.annotations.SerializedName
import java.security.Timestamp
import java.text.SimpleDateFormat
import java.util.*

data class StatusDto(
    @SerializedName("result")
    val result: List<Result>,
    @SerializedName("status")
    val status: String
) {
    fun toStatus(): Status {
        return Status(
            monthlyCount = result.count {
                val created = Calendar.getInstance();
                created.timeInMillis = it.creationTimeSeconds * 1000;
                val today = Calendar.getInstance();
                created.get(Calendar.MONTH) == today.get(Calendar.MONTH) && created.get(Calendar.YEAR) == today.get(Calendar.YEAR) && it.verdict == "OK"
            },
            dailyCount = result.count {
                val created = Calendar.getInstance();
                created.timeInMillis = it.creationTimeSeconds * 1000;
                val today = Calendar.getInstance();
                created.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR) && created.get(Calendar.YEAR) == today.get(Calendar.YEAR) && it.verdict == "OK"
            },
            acceptedList = result.filter {
                val created = Calendar.getInstance();
                created.timeInMillis = it.creationTimeSeconds * 1000;
                val today = Calendar.getInstance();
                created.get(Calendar.MONTH) == today.get(Calendar.MONTH) && created.get(Calendar.YEAR) == today.get(Calendar.YEAR) && it.verdict == "OK"
            }.map {
                AcceptedItem(
                    it.problem.name,
                    "tags: " + it.problem.tags.joinToString(","),
                    "*" + it.problem.rating
                )
            }
        )
    }

    private fun getDateTime(s: Long): String? {
        try {
            val sdf = SimpleDateFormat("MM/dd/yyyy")
            val netDate = Date(s * 1000)
            return sdf.format(netDate)
        } catch (e: Exception) {
            return e.toString()
        }
    }

    fun getDate(milliSeconds: Long, dateFormat: String?): String? {
        // Create a DateFormatter object for displaying date in specified format.
        val formatter = SimpleDateFormat(dateFormat)

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = java.sql.Timestamp(milliSeconds).time
        return formatter.format(calendar.time)
    }
}