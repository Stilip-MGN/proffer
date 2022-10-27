package studio.stilip.proffer.app.fragments.profile

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import studio.stilip.proffer.domain.entities.Profile
import studio.stilip.proffer.domain.usecase.profile.GetProfileUseCase
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfile: GetProfileUseCase
) : ViewModel() {

    var profile = BehaviorSubject.create<Profile>().apply{ loadProfile() }

    private fun loadProfile() {
        getProfile()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { profile ->
                this.profile.onNext(profile)
            }
    }
}