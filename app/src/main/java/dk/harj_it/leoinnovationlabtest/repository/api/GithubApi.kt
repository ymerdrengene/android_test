package dk.harj_it.leoinnovationlabtest.repository.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dk.harj_it.leoinnovationlabtest.models.Repository
import dk.harj_it.leoinnovationlabtest.models.User
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

object Github {
    val api: Api?

    private const val BASE_URL = "https://api.github.com/"

    init {
        api = RetrofitBuilder.builderWithUrl(BASE_URL)?.create(Api::class.java)
    }

    interface Api {
        @GET("users/{username}")
        fun user(@Path("username") username: String): Deferred<User>

        @GET("users/{username}/repos")
        fun repos(@Path("username") username: String): Deferred<List<Repository>>
    }
}

object RetrofitBuilder {
    fun builderWithUrl(url: String): Retrofit? =
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
}