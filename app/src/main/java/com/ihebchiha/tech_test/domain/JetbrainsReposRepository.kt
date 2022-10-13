package com.ihebchiha.tech_test.domain

import com.ihebchiha.tech_test.data.remote.RepoDto

interface JetbrainsReposRepository {

    suspend fun getJetbrainsRepos(): List<RepoDto>
}