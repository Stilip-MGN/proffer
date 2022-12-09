package studio.stilip.proffer.domain.usecase.ad

import studio.stilip.proffer.domain.repository_interface.AdRepository
import javax.inject.Inject

class GetAdsByUserIdUseCase @Inject constructor(
    private val repository: AdRepository
) {
    operator fun invoke(id: Int) = repository.getAdsByUserId(id)
}