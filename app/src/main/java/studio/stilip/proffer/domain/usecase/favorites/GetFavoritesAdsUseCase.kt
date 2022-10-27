package studio.stilip.proffer.domain.usecase.favorites

import studio.stilip.proffer.domain.repository_interface.AdRepository
import javax.inject.Inject

class GetFavoritesAdsUseCase @Inject constructor(
    private val repository: AdRepository
) {
    operator fun invoke() = repository.getFavoritesAds()
}