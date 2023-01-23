package studio.stilip.proffer.domain.usecase.search

import studio.stilip.proffer.domain.repository_interface.AdRepository
import javax.inject.Inject

class GetRecommendedAdsUseCase @Inject constructor(
    private val repository: AdRepository
) {
    operator fun invoke(userId: Int) = repository.getRecommendedAds(userId)
}