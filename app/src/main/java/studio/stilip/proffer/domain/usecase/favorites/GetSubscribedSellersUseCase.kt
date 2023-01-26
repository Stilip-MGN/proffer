package studio.stilip.proffer.domain.usecase.favorites

import studio.stilip.proffer.domain.repository_interface.ProfileRepository
import javax.inject.Inject

class GetSubscribedSellersUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    operator fun invoke() = repository.getSubscribedSellers()
}