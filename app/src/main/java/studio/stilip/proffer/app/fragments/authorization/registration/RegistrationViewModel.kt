package studio.stilip.proffer.app.fragments.authorization.registration

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import studio.stilip.proffer.domain.entities.User
import studio.stilip.proffer.domain.usecase.authorization.RegistrationUserUseCase
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registrationUser: RegistrationUserUseCase,
) : ViewModel() {

    val message = PublishSubject.create<String>()
    val successRegistration = PublishSubject.create<Boolean>()

    fun registration(user: User) {
        registrationUser(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                message.onNext("Пользователь зарегистрирован")
                successRegistration.onNext(true)
            }, {
                message.onNext(it.message)
                successRegistration.onNext(false)
            })
    }

}