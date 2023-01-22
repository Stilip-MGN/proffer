package studio.stilip.proffer.domain.repository_interface

import io.reactivex.Completable
import io.reactivex.Single
import studio.stilip.proffer.domain.entities.User

interface UserRepository {

    fun authentication(login: String, password: String): Single<User>

    fun registerUser(login: String, password: String, email: String): Single<User>

    fun changeDataUser(user: User): Completable
}