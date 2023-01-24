package studio.stilip.proffer.app

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import studio.stilip.proffer.data.UserCacheManager
import studio.stilip.proffer.domain.entities.User
import studio.stilip.proffer.domain.usecase.user.ChangeDataUserUseCase
import studio.stilip.proffer.domain.usecase.user.DeleteUserDBUseCase
import studio.stilip.proffer.domain.usecase.user.SaveUserDBUseCase
import javax.inject.Inject

@HiltViewModel
class HostViewModel @Inject constructor(
    private val changeDataUser: ChangeDataUserUseCase,
    private val saveUserDB: SaveUserDBUseCase,
    private val deleteUserDB: DeleteUserDBUseCase,
) : ViewModel() {

    val bottomBarVisible = BehaviorSubject.create<Boolean>()
    var currentUser = BehaviorSubject.create<User>()
    val isDataChanged = PublishSubject.create<Boolean>()

    fun setBottomBarVisible(isVisible: Boolean) {
        bottomBarVisible.onNext(isVisible)
    }

    fun setCurrentUser(user: User) {
        UserCacheManager.setUserCache(user)
        currentUser.onNext(user)
        saveUser(user)
    }

    fun deleteCurrentUser() {
        UserCacheManager.setUserCache(User())
        currentUser = BehaviorSubject.create()
        deleteUser()
    }

    fun changeData(user: User) {
        changeDataUser(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                currentUser.onNext(user)
                isDataChanged.onNext(true)
            }, {
                isDataChanged.onNext(false)
            })
    }

    private fun saveUser(user: User) {
        saveUserDB(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                println("Пользователь сохранён")
            }, {
                println(it.message)
            })
    }

    private fun deleteUser() {
        deleteUserDB()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                println("Пользователь удален")
            }, {
                println(it.message)
            })
    }
}