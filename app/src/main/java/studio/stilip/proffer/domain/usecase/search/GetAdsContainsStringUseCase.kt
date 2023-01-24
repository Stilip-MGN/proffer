package studio.stilip.proffer.domain.usecase.search

import studio.stilip.proffer.domain.repository_interface.AdRepository
import javax.inject.Inject

class GetAdsContainsStringUseCase @Inject constructor(
    private val repository: AdRepository
) {
    operator fun invoke(string: String, userId: Int) =
        repository.getAdsContainsString(string, userId)
}