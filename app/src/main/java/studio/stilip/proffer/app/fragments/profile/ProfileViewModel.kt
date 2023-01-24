package studio.stilip.proffer.app.fragments.profile

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import studio.stilip.proffer.data.UserCacheManager
import studio.stilip.proffer.domain.entities.Profile
import studio.stilip.proffer.domain.usecase.profile.GetProfileByIdUseCase
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfile: GetProfileByIdUseCase
) : ViewModel() {

    var profile = BehaviorSubject.create<Profile>().apply { loadProfile() }

    private fun loadProfile() {
        getProfile(UserCacheManager.getUserId())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ profile ->
                this.profile.onNext(profile)
            }, {
                println(it.message)
            })
    }
}