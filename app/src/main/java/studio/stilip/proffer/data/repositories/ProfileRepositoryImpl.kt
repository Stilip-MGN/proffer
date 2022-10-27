package studio.stilip.proffer.data.repositories

import io.reactivex.rxjava3.core.Single
import studio.stilip.proffer.domain.entities.Profile
import studio.stilip.proffer.domain.repository_interface.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor() : ProfileRepository {

    val man = Profile(
        "Иван Иванов",
        "Екатеринбург",
        1,
        2,
        3
    )

    override fun getProfile(): Single<Profile> = Single.just(man)
}