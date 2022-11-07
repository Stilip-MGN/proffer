package studio.stilip.proffer.domain.usecase.search

import studio.stilip.proffer.domain.repository_interface.AdRepository
import javax.inject.Inject

class RemoveAdFromFavoriteByIdUseCase @Inject constructor(
    private val repository: AdRepository
) {
    operator fun invoke(id: Int) = repository.removeAdFromFavoriteById(id)
}