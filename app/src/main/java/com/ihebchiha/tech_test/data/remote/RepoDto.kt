package com.ihebchiha.tech_test.data.remote

import com.google.gson.annotations.SerializedName
import com.ihebchiha.tech_test.domain.model.Owner
import com.ihebchiha.tech_test.domain.model.Permissions
import com.ihebchiha.tech_test.domain.model.Repo

data class RepoDto(

    @SerializedName("full_name")
    val fullName: String?,
    val forks: String?,
    val watchers: Long?,
    @SerializedName("open_issues")
    val openIssues: Long?,
    val description: String?

) {
    fun toRepo(): Repo = Repo(
        fullName ?: "",
        forks ?: "",
        watchers ?: 0,
        openIssues ?: 0,
        description ?: ""
    )
}