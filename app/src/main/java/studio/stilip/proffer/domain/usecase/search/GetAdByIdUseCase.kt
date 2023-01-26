package studio.stilip.proffer.domain.usecase.search

import studio.stilip.proffer.domain.repository_interface.AdRepository
import javax.inject.Inject

class GetAdByIdUseCase @Inject constructor(
    private val repository: AdRepository
) {
    operator fun invoke(idAd: Int, idUser: Int) = repository.getAdById(idAd, idUser)
}