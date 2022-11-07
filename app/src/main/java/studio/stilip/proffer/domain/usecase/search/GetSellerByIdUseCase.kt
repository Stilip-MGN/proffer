package studio.stilip.proffer.domain.usecase.search

import studio.stilip.proffer.domain.repository_interface.SellerRepository
import javax.inject.Inject

class GetSellerByIdUseCase @Inject constructor(
    private val repository: SellerRepository
) {
    operator fun invoke(id: Int) = repository.getSellerById(id)
}