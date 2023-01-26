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
    val user = PublishSubject.create<User>()

    fun registration(login: String, password: String, email: String) {
        registrationUser(login, password, email)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ u ->
                user.onNext(u)
                message.onNext(resourcesProvider.getString(R.string.user_registered))
            }, {
                message.onNext(resourcesProvider.getString(R.string.user_with_this_login_already_exists))
            })
    }

}