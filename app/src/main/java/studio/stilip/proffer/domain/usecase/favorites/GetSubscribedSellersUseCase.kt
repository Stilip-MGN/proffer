package studio.stilip.proffer.domain.usecase.favorites

import studio.stilip.proffer.domain.repository_interface.SellerRepository
import javax.inject.Inject

class GetSubscribedSellersUseCase @Inject constructor(
    private val repository: SellerRepository
) {
    operator fun invoke() = repository.getSubscribedSellers()
}