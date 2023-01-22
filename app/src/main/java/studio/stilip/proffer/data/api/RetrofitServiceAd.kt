package studio.stilip.proffer.data.api

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import studio.stilip.proffer.data.entities.AdEntityForApi
import studio.stilip.proffer.data.entities.UserApi
import studio.stilip.proffer.data.entities.UserApiForRegister
import studio.stilip.proffer.domain.entities.User

interface RetrofitServiceAd {
    @GET("/api/posts/")
    fun getPosts(): Single<List<AdEntityForApi>>

    @GET("/api/userimages/")
    fun getUserImages(): Single<List<String>>

    @POST("/api/login/")
    fun getLogin(@Body review: UserApi): Single<User>

    @POST("/api/createuser/")
    fun registerUser(@Body review: UserApiForRegister): Single<User>
}