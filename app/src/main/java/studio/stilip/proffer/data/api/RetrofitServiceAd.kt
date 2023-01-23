package studio.stilip.proffer.data.api

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import studio.stilip.proffer.data.entities.UserApi
import studio.stilip.proffer.data.entities.UserApiForRegister
import studio.stilip.proffer.domain.entities.Ad
import studio.stilip.proffer.domain.entities.User

interface RetrofitServiceAd {

    @POST("/api/login/")
    fun getLogin(@Body review: UserApi): Single<User>

    @POST("/api/createuser/")
    fun registerUser(@Body review: UserApiForRegister): Single<User>

    @GET("/api/recommended/{id}")
    fun getRecommendAds(@Path("id") id: String): Single<List<Ad>>
}