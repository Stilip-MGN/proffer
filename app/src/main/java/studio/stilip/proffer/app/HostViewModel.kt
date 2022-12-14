package studio.stilip.proffer.app

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import studio.stilip.proffer.domain.entities.User
import studio.stilip.proffer.domain.usecase.user.ChangeDataUserUseCase
import javax.inject.Inject

@HiltViewModel
class HostViewModel @Inject constructor(
    private val changeDataUser: ChangeDataUserUseCase
) : ViewModel() {

    val bottomBarVisible = BehaviorSubject.create<Boolean>()
    val currentUser = BehaviorSubject.create<User>()
    val isDataChanged = PublishSubject.create<Boolean>()

    fun setBottomBarVisible(isVisible: Boolean) {
        bottomBarVisible.onNext(isVisible)
    }

    fun setCurrentUser(user: User) {
        currentUser.onNext(user)
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
}