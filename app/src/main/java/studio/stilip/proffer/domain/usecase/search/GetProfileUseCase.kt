package studio.stilip.proffer.domain.usecase.search

import studio.stilip.proffer.domain.repository_interface.ProfileRepository
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    operator fun invoke() = repository.getProfile()
}