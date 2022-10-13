package com.ihebchiha.tech_test.data

import com.ihebchiha.tech_test.data.remote.RepoDto
import retrofit2.http.GET

interface JetbrainsReposApi{

    @GET("orgs/jetbrains/repos?page=1")
    suspend fun retrieveJetbrainsRepos(): List<RepoDto>
}