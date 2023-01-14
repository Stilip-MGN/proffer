package studio.stilip.proffer.domain.repository_interface

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import studio.stilip.proffer.domain.entities.User

interface UserRepository {

    fun authentication(login: String, password: String): Single<User>

    fun registerUser(user: User): Completable

    fun changeDataUser(user: User): Completable
}