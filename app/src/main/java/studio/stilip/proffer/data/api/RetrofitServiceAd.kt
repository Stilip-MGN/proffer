package studio.stilip.proffer.data.api

import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import studio.stilip.proffer.data.entities.AdEntityForApi
import studio.stilip.proffer.domain.entities.Ad

interface RetrofitServiceAd {
    @GET("/api/posts/")
    fun getPosts(): Single<List<AdEntityForApi>>

    @GET("/api/userimages/")
    fun getUserImages(): Single<List<String>>
}