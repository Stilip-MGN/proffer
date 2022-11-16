package studio.stilip.proffer.domain.usecase.product

import studio.stilip.proffer.domain.repository_interface.AdRepository
import javax.inject.Inject

class GetSimilarAdsUseCase @Inject constructor(
    private val repository: AdRepository
) {
    operator fun invoke(id: Int) = repository.getSimilarAds(id)
}