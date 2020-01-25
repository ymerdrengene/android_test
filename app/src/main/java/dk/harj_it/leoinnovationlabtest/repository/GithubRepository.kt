package dk.harj_it.leoinnovationlabtest.repository

import dk.harj_it.leoinnovationlabtest.models.Repository
import dk.harj_it.leoinnovationlabtest.models.User
import dk.harj_it.leoinnovationlabtest.repository.api.Github
import timber.log.Timber

object GithubRepository {

    suspend fun fetchUser(username: String): User? {
        val user = Github.api?.user(username)?.await()
        Timber.d("user: %s", user)

        return user
    }

    suspend fun fetchRepositories(username: String): List<Repository>? {
        val repositories = Github.api?.repos(username)?.await()
        Timber.d("Repos: %s", repositories)
        return repositories
    }
}