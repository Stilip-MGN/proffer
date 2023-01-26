package studio.stilip.proffer.domain.repository_interface

import io.reactivex.Single
import studio.stilip.proffer.domain.entities.Profile

interface ProfileRepository {

    fun getSubscribedSellers(): Single<List<Profile>>

    fun getProfileById(id: Int): Single<Profile>
}