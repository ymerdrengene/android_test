package dk.harj_it.leoinnovationlabtest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dk.harj_it.leoinnovationlabtest.models.Repository
import dk.harj_it.leoinnovationlabtest.models.User
import dk.harj_it.leoinnovationlabtest.repository.GithubRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel : ViewModel() {

    val username = MutableLiveData<String>()
    val user = MutableLiveData<User>()
    val repositories = MutableLiveData<List<Repository>>()
    val loading = MutableLiveData<Boolean>()
    val showError = MutableLiveData<Boolean>()

    suspend fun fetchFromGithub(username: String) {
        showError.postValue(false)
        loading.postValue(true)

        viewModelScope.launch {
            try {
                user.postValue(GithubRepository.fetchUser(username))
                repositories.postValue(GithubRepository.fetchRepositories(username))

            } catch (e: Exception) {
                Timber.e(e)
                showError.postValue(true)

            } finally {
                loading.postValue(false)
            }
        }
    }
}