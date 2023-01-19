package studio.stilip.proffer.app.fragments.authorization.registration

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import studio.stilip.proffer.R
import studio.stilip.proffer.app.ResourcesProvider
import studio.stilip.proffer.domain.entities.User
import studio.stilip.proffer.domain.usecase.authorization.RegistrationUserUseCase
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registrationUser: RegistrationUserUseCase,
    private val resourcesProvider: ResourcesProvider,
) : ViewModel() {

    val message = PublishSubject.create<String>()
    val successRegistration = PublishSubject.create<Boolean>()

    fun registration(user: User) {
        registrationUser(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                message.onNext(resourcesProvider.getString(R.string.user_registered))
                successRegistration.onNext(true)
            }, {
                message.onNext(it.message ?: "Ошибка")
                successRegistration.onNext(false)
            })
    }

}