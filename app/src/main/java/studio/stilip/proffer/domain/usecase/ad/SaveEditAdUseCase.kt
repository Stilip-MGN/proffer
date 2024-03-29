package studio.stilip.proffer.domain.usecase.ad

import studio.stilip.proffer.domain.entities.Ad
import studio.stilip.proffer.domain.repository_interface.AdRepository
import javax.inject.Inject

class SaveEditAdUseCase @Inject constructor(
    private val repository: AdRepository
) {
    operator fun invoke(ad: Ad) = repository.saveEditAd(ad)
}