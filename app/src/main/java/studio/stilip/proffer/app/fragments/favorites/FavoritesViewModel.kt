package studio.stilip.proffer.app.fragments.favorites

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import studio.stilip.proffer.domain.entities.Ad
import studio.stilip.proffer.domain.entities.Seller
import studio.stilip.proffer.domain.usecase.favorites.GetFavoritesAdsUseCase
import studio.stilip.proffer.domain.usecase.favorites.GetSubscribedSellersUseCase
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesAds: GetFavoritesAdsUseCase,
    private val getSubscribedSellers: GetSubscribedSellersUseCase
) : ViewModel() {

    val ads = BehaviorSubject.create<List<Ad>>().apply { getFavorites() }
    val sellers = BehaviorSubject.create<List<Seller>>().apply { getSubscribed() }
    val isFav = BehaviorSubject.create<Boolean>().also { it.onNext(true) }

    private fun getFavorites() {
        getFavoritesAds()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { ad ->
                ads.onNext(ad)
            }
    }

    private fun getSubscribed() {
        getSubscribedSellers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { seller ->
                sellers.onNext(seller)
            }
    }
}