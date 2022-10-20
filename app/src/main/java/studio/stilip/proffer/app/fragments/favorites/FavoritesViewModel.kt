package studio.stilip.proffer.app.fragments.favorites

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import studio.stilip.proffer.domain.entities.Ad
import studio.stilip.proffer.domain.usecase.search.GetFavoritesAdsUseCase
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesAds: GetFavoritesAdsUseCase
) : ViewModel() {

    val ads = BehaviorSubject.create<List<Ad>>().apply { getFavorites() }

    private fun getFavorites() {
        getFavoritesAds()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { bb ->
                ads.onNext(bb)
            }
    }
}