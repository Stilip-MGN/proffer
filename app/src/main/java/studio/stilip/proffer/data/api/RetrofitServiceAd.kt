package studio.stilip.proffer.data.api

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import studio.stilip.proffer.data.entities.AdForApi
import studio.stilip.proffer.data.entities.UserApiForLogin
import studio.stilip.proffer.data.entities.UserApiForRegister
import studio.stilip.proffer.domain.entities.Ad
import studio.stilip.proffer.domain.entities.User

interface RetrofitServiceAd {

    @POST("/api/login/")
    fun getLogin(@Body review: UserApiForLogin): Single<User>

    @POST("/api/createuser/")
    fun registerUser(@Body review: UserApiForRegister): Single<User>

    @GET("/api/recommended/{id}")
    fun getRecommendAds(@Path("id") id: String): Single<List<AdForApi>>

    @GET("/api/course/user={id_user}/course={id_ad}")
    fun getAdById(@Path("id_user") id_user: String, @Path("id_ad") id_ad: String): Single<List<AdForApi>>

    //TODO путь переделать
    @GET("/api/search/{id_user}{string}")
    fun getAdsContainsString(
        @Path("id_user") id_user: String,
        @Path("string") string: String
    ): Single<List<Ad>>
}