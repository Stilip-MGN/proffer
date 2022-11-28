package studio.stilip.proffer.app

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.subjects.BehaviorSubject
import studio.stilip.proffer.domain.entities.User
import javax.inject.Inject

@HiltViewModel
class HostViewModel @Inject constructor() : ViewModel() {

    val bottomBarVisible = BehaviorSubject.create<Boolean>()
    val currentUser = BehaviorSubject.create<User>()

    fun setBottomBarVisible(isVisible: Boolean) {
        bottomBarVisible.onNext(isVisible)
    }

    fun setCurrentUser(user: User) {
        currentUser.onNext(user)
    }
}