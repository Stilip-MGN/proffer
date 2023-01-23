package studio.stilip.proffer.app

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import studio.stilip.proffer.domain.entities.User
import studio.stilip.proffer.domain.usecase.user.ChangeDataUserUseCase
import studio.stilip.proffer.domain.usecase.user.SaveUserDBUseCase
import javax.inject.Inject

@HiltViewModel
class HostViewModel @Inject constructor(
    private val changeDataUser: ChangeDataUserUseCase,
    private val saveUserDB: SaveUserDBUseCase
) : ViewModel() {

    val bottomBarVisible = BehaviorSubject.create<Boolean>()
    val currentUser = BehaviorSubject.create<User>()
    val isDataChanged = PublishSubject.create<Boolean>()

    fun setBottomBarVisible(isVisible: Boolean) {
        bottomBarVisible.onNext(isVisible)
    }

    fun setCurrentUser(user: User) {
        currentUser.onNext(user)
        saveUser(user)
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

    private fun saveUser(user: User){
        saveUserDB(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                //TODO удалить позже
                println("Пользователь сохранён")
            }, {
                println(it.message)
            })
    }
}