package studio.stilip.proffer.domain.usecase.user

import studio.stilip.proffer.domain.repository_interface.UserRepository
import javax.inject.Inject

class DeleteUserDBUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke() = repository.deleteUserInBD()
}