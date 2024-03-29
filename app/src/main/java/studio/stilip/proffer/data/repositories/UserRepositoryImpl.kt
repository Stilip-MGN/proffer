package studio.stilip.proffer.data.repositories

import io.reactivex.Completable
import io.reactivex.Single
import studio.stilip.proffer.app.ResourcesProvider
import studio.stilip.proffer.data.api.RetrofitServiceUser
import studio.stilip.proffer.data.dao.UserDBDao
import studio.stilip.proffer.data.dto.toDB
import studio.stilip.proffer.data.dto.toDomain
import studio.stilip.proffer.data.entities.UserApiForLogin
import studio.stilip.proffer.data.entities.UserApiForRegister
import studio.stilip.proffer.domain.entities.User
import studio.stilip.proffer.domain.repository_interface.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val resourcesProvider: ResourcesProvider,
    private val retrofitService: RetrofitServiceUser,
    private val userDBDao: UserDBDao
) : UserRepository {

    val users = mutableListOf(User(1, "admin", "Иван Иванович", "", "", "", "admin"))

    override fun authentication(login: String, password: String): Single<User> =
        retrofitService.getLogin(UserApiForLogin(login, password)).map { it.toDomain() }

    override fun registerUser(login: String, password: String, email: String): Single<User> =
        retrofitService.registerUser(UserApiForRegister(login, password, email))
            .map { it.toDomain() }


    override fun changeDataUser(user: User): Completable {
        if (users.firstOrNull { u -> u.id == user.id } == null)
            return Completable.error(Throwable("Такого пользователя нет"))
        users.removeIf { u -> u.id == user.id }
        users.add(user)
        return Completable.complete()
    }

    override fun saveUserInDB(user: User): Completable {
        return userDBDao.insertUser(user.toDB())
    }

    override fun deleteUserInBD(): Completable {
        return userDBDao.deleteUser()
    }

    //TODO убрать лишнее
    override fun getUserFromDB(): Single<User> {
        return userDBDao.getUsers().map { it.first().toDomain() }
    }

}