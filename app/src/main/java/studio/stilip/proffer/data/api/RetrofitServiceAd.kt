package studio.stilip.proffer.data.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import studio.stilip.proffer.data.entities.AdForApi

interface RetrofitServiceAd {

    @GET("/api/recommended/{id}")
    fun getRecommendAds(@Path("id") id: String): Single<List<AdForApi>>

    @GET("/api/course/user={id_user}/course={id_ad}")
    fun getAdById(
        @Path("id_user") id_user: String,
        @Path("id_ad") id_ad: String
    ): Single<List<AdForApi>>

    @GET("/api/search/q={string}/user={id_user}")
    fun getAdsContainsString(
        @Path("id_user") id_user: String,
        @Path("string") string: String
    ): Single<List<AdForApi>>
}