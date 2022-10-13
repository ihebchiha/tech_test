package com.ihebchiha.tech_test.domain.model

import com.google.gson.annotations.SerializedName

data class Repo(

    val fullName: String,
    val forks: String,
    val watchers: Long,
    val openIssues: Long,
    val description: String
)
