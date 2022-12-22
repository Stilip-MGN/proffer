package studio.stilip.proffer.data.repositories

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import studio.stilip.proffer.domain.entities.Profile
import studio.stilip.proffer.domain.entities.User
import studio.stilip.proffer.domain.repository_interface.ProfileRepository
import studio.stilip.proffer.domain.repository_interface.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor() : UserRepository {

    val users = mutableListOf(User(1, "", "", "", "", ""))

    override fun authentication(login: String, password: String): Completable {
        TODO("Not yet implemented")
    }

    override fun registerUser(user: User): Completable {
        TODO("Not yet implemented")
    }

    override fun changeDataUser(user: User): Completable {
        if (users.firstOrNull { u -> u.id == user.id } == null)
            return Completable.error(Exception("Такого пользователя нет"))
        users.removeIf { u -> u.id == user.id }
        users.add(user)
        return Completable.complete()
    }

}