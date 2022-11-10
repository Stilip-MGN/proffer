package studio.stilip.proffer.app.fragments

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.subjects.BehaviorSubject
import javax.inject.Inject

@HiltViewModel
class HostViewModel @Inject constructor() : ViewModel() {

    val bottomBarVisible = BehaviorSubject.create<Boolean>()

    fun setBottomBarVisible(isVisible: Boolean) {
        bottomBarVisible.onNext(isVisible)
    }
}