package studio.stilip.proffer.app.fragments.authorization.sign

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import studio.stilip.proffer.R
import studio.stilip.proffer.app.ResourcesProvider
import studio.stilip.proffer.domain.entities.User
import studio.stilip.proffer.domain.usecase.authorization.SignInUserUseCase
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUser: SignInUserUseCase,
    private val resourcesProvider: ResourcesProvider,
) : ViewModel() {

    val message = PublishSubject.create<String>()
    val user = PublishSubject.create<User>()

    fun authentication(login: String, password: String) {
        signInUser(login, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ u ->
                user.onNext(u)
                message.onNext(resourcesProvider.getString(R.string.you_sign_in))
            }, {
                message.onNext(resourcesProvider.getString(R.string.invalid_login_or_password))
            })
    }

}