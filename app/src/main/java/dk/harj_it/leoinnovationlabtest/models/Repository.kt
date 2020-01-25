package dk.harj_it.leoinnovationlabtest.models

import com.google.gson.annotations.SerializedName

data class Repository(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("full_name")
    val fullName: String? = null,
    @SerializedName("owner")
    val owner: User,
    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("clone_url")
    val cloneUrl: String

)