package com.ihebchiha.tech_test.domain.usecase

import com.ihebchiha.tech_test.domain.JetbrainsReposRepository
import com.ihebchiha.tech_test.domain.model.Repo
import com.ihebchiha.tech_test.utils.Resource
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import java.util.concurrent.Flow
import javax.inject.Inject

class GetReposUseCase @Inject constructor (private val reposRepository: JetbrainsReposRepository) {

    operator fun invoke() = flow{
        try {
            emit(Resource.Loading<List<Repo>>())
            val repos = reposRepository.getJetbrainsRepos().map { repo -> repo.toRepo() }
            emit(Resource.Success(repos))
            Timber.d("response => $repos" )
        } catch(e: HttpException) {
            Timber.d("error message1 => $e")
            emit(Resource.Error<Repo>(e.localizedMessage ?: "An unexpected error has occurred"))
        } catch(e: IOException) {
            Timber.d("error message2 => $e")
            emit(Resource.Error<String>("Couldn't reach server. Retry later or check your connectivity."))
        }catch (e: Exception){
            Timber.d("error message3 => ${e}")
            emit(Resource.Error<String>("Unexpected error occurred."))
        }
    }
}