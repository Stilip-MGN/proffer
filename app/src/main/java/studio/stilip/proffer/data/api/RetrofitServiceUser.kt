package studio.stilip.proffer.data.api

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import studio.stilip.proffer.data.entities.ProfileForApi
import studio.stilip.proffer.data.entities.UserApiForLogin
import studio.stilip.proffer.data.entities.UserApiForRegister
import studio.stilip.proffer.data.entities.UserFromApi

interface RetrofitServiceUser {

    @POST("/api/login/")
    fun getLogin(@Body review: UserApiForLogin): Single<UserFromApi>

    @POST("/api/createuser/")
    fun registerUser(@Body review: UserApiForRegister): Single<UserFromApi>

    @GET("/api/users/{id}")
    fun getUserInfoById(@Path("id") id: String): Single<List<ProfileForApi>>
}