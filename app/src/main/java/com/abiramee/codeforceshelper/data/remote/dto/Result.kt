package com.abiramee.codeforceshelper.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("author")
    val author: Author,
    @SerializedName("contestId")
    val contestId: Int,
    @SerializedName("creationTimeSeconds")
    val creationTimeSeconds: Long,
    @SerializedName("id")
    val id: Int,
    @SerializedName("memoryConsumedBytes")
    val memoryConsumedBytes: Int,
    @SerializedName("passedTestCount")
    val passedTestCount: Int,
    @SerializedName("problem")
    val problem: Problem,
    @SerializedName("programmingLanguage")
    val programmingLanguage: String,
    @SerializedName("relativeTimeSeconds")
    val relativeTimeSeconds: Int,
    @SerializedName("testset")
    val testset: String,
    @SerializedName("timeConsumedMillis")
    val timeConsumedMillis: Int,
    @SerializedName("verdict")
    val verdict: String
)