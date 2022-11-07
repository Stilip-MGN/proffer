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
import studio.stilip.proffer.domain.usecase.search.RemoveAdFromFavoriteByIdUseCase
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesAds: GetFavoritesAdsUseCase,
    private val getSubscribedSellers: GetSubscribedSellersUseCase,
    private val removeAdFromFavoriteById: RemoveAdFromFavoriteByIdUseCase,
) : ViewModel() {

    val ads = BehaviorSubject.create<List<Ad>>()
    val sellers = BehaviorSubject.create<List<Seller>>().apply { getSubscribed() }
    val isFav = BehaviorSubject.create<Boolean>().also { it.onNext(true) }

    fun getFavorites() {
        getFavoritesAds()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { list ->
                ads.onNext(list)
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

    fun removeFromFavoriteById(id_product: Int) {
        removeAdFromFavoriteById(id_product)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                val updateList = ads.value!!.toMutableList()
                updateList.removeIf { ad -> ad.id == id_product }
                ads.onNext(updateList)
            }
    }
}