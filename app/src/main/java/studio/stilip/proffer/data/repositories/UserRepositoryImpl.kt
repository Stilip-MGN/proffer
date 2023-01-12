package studio.stilip.proffer.data.repositories

import io.reactivex.rxjava3.core.Completable
import studio.stilip.proffer.R
import studio.stilip.proffer.app.ResourcesProvider
import studio.stilip.proffer.domain.entities.User
import studio.stilip.proffer.domain.repository_interface.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val resourcesProvider: ResourcesProvider,
) : UserRepository {

    val users = mutableListOf(User(2, "", "", "", "", "", ""))

    override fun authentication(login: String, password: String): Completable {
        TODO("Not yet implemented")
    }

    override fun registerUser(user: User): Completable {
        if (users.any { u -> u.login == user.login })
            return Completable.error(Throwable(resourcesProvider.getString(R.string.user_with_this_login_already_exists)))
        users.add(user)
        return Completable.complete()
    }

    override fun changeDataUser(user: User): Completable {
        if (users.firstOrNull { u -> u.id == user.id } == null)
            return Completable.error(Throwable("Такого пользователя нет"))
        users.removeIf { u -> u.id == user.id }
        users.add(user)
        return Completable.complete()
    }

}