package studio.stilip.proffer.data

import studio.stilip.proffer.domain.entities.User

object UserCacheManager {

    private var user: User = User()

    fun setUserCache(user: User) {
        this.user = user
    }

    fun getUser(): User = user

    fun getUserId(): Int = user.id
}