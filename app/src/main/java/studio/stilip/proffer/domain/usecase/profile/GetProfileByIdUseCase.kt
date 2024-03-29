package studio.stilip.proffer.domain.usecase.profile

import studio.stilip.proffer.domain.repository_interface.ProfileRepository
import javax.inject.Inject

class GetProfileByIdUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    operator fun invoke(id: Int) = repository.getProfileById(id)
}