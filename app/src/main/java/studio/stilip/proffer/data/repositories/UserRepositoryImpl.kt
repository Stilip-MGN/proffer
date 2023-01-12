package studio.stilip.proffer.data.repositories

import io.reactivex.rxjava3.core.Completable
import studio.stilip.proffer.domain.entities.User
import studio.stilip.proffer.domain.repository_interface.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor() : UserRepository {

    val users = mutableListOf(User(2, "", "", "", "", "", ""))

    override fun authentication(login: String, password: String): Completable {
        TODO("Not yet implemented")
    }

    override fun registerUser(user: User): Completable {
        if (users.any { u -> u.login == user.login })
            return Completable.error(Throwable("Пользователь с таким логином уже существует"))
        users.add(user)
        return Completable.complete()
    }

    override fun changeDataUser(user: User): Completable {
        if (users.firstOrNull { u -> u.id == user.id } == null)
            return Completable.error(Exception("Такого пользователя нет"))
        users.removeIf { u -> u.id == user.id }
        users.add(user)
        return Completable.complete()
    }

}