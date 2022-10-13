package com.ihebchiha.tech_test.data.repository

import com.ihebchiha.tech_test.data.JetbrainsReposApi
import com.ihebchiha.tech_test.data.remote.RepoDto
import com.ihebchiha.tech_test.domain.JetbrainsReposRepository
import javax.inject.Inject

class JetbrainsReposRepositoryImpl @Inject constructor(private val api: JetbrainsReposApi) :
    JetbrainsReposRepository {
    override suspend fun getJetbrainsRepos(): List<RepoDto> {
        return api.retrieveJetbrainsRepos()
    }
}