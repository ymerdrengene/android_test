package dk.harj_it.leoinnovationlabtest.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login")
    val login: String? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,
    @SerializedName("html_url")
    val htmlUrl: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("company")
    val company: String? = null,
    @SerializedName("location")
    val location: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("followers")
    val followers: String? = null,
    @SerializedName("following")
    val following: String? = null
)