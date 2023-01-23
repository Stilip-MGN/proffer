package studio.stilip.proffer.domain.usecase.user

import studio.stilip.proffer.domain.entities.User
import studio.stilip.proffer.domain.repository_interface.UserRepository
import javax.inject.Inject

class SaveUserDBUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(user: User) = repository.saveUserInDB(user)
}