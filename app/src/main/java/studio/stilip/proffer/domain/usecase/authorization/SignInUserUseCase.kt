package studio.stilip.proffer.domain.usecase.authorization

import studio.stilip.proffer.domain.repository_interface.UserRepository
import javax.inject.Inject

class SignInUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(login: String, password: String) =
        repository.authentication(login, password)
}