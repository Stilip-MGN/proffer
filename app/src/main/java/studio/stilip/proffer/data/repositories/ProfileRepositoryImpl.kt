package studio.stilip.proffer.data.repositories

import io.reactivex.Single
import studio.stilip.proffer.data.api.RetrofitServiceAd
import studio.stilip.proffer.data.dto.toDomain
import studio.stilip.proffer.domain.entities.Profile
import studio.stilip.proffer.domain.repository_interface.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val retrofitService: RetrofitServiceAd,
) : ProfileRepository {

    val sellers = listOf(
        Profile(1, "Иван Иванов", "", "", 4.3, 123),
        Profile(2, "Петр Вайко", "", "", 2.3, 25),
        Profile(3, "Олег Воробьев", "", "", 4.7, 540),
        Profile(4, "Арарат Мунслинов", "", "", 4.1, 12),
    )

    override fun getSubscribedSellers(): Single<List<Profile>> = Single.just(sellers)

    override fun getProfileById(id: Int): Single<Profile> =
        retrofitService.getUserInfoById(id.toString()).map { it.first().toDomain() }
}