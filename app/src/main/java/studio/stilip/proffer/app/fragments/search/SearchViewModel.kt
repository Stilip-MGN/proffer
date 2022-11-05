package studio.stilip.proffer.app.fragments.search

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import studio.stilip.proffer.domain.entities.Ad
import studio.stilip.proffer.domain.usecase.search.AddAdToFavoriteByIdUseCase
import studio.stilip.proffer.domain.usecase.search.GetRecommendedAdsUseCase
import studio.stilip.proffer.domain.usecase.search.RemoveAdFromFavoriteByIdUseCase
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getRecommendedAds: GetRecommendedAdsUseCase,
    private val addAdToFavoriteById: AddAdToFavoriteByIdUseCase,
    private val removeAdFromFavoriteById: RemoveAdFromFavoriteByIdUseCase
) : ViewModel() {

    val ads = BehaviorSubject.create<List<Ad>>().apply { getRecommended() }

    private fun getRecommended() {
        getRecommendedAds()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { bb ->
                ads.onNext(bb)
            }
    }

    fun changeFavoriteStatusById(id_product: Int) {
        addAdToFavoriteById(id_product)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}