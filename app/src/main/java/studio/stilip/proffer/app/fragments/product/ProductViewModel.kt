package studio.stilip.proffer.app.fragments.product

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import studio.stilip.proffer.app.fragments.product.ProductFragment.Companion.ID_AD
import studio.stilip.proffer.data.UserCacheManager
import studio.stilip.proffer.domain.entities.Ad
import studio.stilip.proffer.domain.entities.Seller
import studio.stilip.proffer.domain.usecase.product.GetSimilarAdsUseCase
import studio.stilip.proffer.domain.usecase.search.AddAdToFavoriteByIdUseCase
import studio.stilip.proffer.domain.usecase.search.GetAdByIdUseCase
import studio.stilip.proffer.domain.usecase.search.GetSellerByIdUseCase
import studio.stilip.proffer.domain.usecase.search.RemoveAdFromFavoriteByIdUseCase
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getAd: GetAdByIdUseCase,
    private val getSellerById: GetSellerByIdUseCase,
    private val addAdToFavoriteById: AddAdToFavoriteByIdUseCase,
    private val removeAdFromFavoriteById: RemoveAdFromFavoriteByIdUseCase,
    private val getSimilarAds: GetSimilarAdsUseCase,
    stateHandle: SavedStateHandle
) : ViewModel() {
    private val adId: Int = stateHandle[ID_AD]!!
    var userId: Int = UserCacheManager.getUserId()
    var ad = BehaviorSubject.create<Ad>().apply { loadAd() }
    var seller = BehaviorSubject.create<Seller>()
    val similarAds = BehaviorSubject.create<List<Ad>>()

    fun loadSimilar() {
        getSimilarAds(adId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                this.similarAds.onNext(list)
            }, {
                println("loadSimilar")
            })
    }

    private fun loadAd() {
        getAd(adId, userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ ad ->
                this.ad.onNext(ad)
                loadProfile(ad.idSeller)
                loadSimilar()
            }, {
                println(it.message)
            })
    }

    private fun loadProfile(id: Int) {
        getSellerById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ seller ->
                this.seller.onNext(seller)
            }, {
                println("loadProfile")
            })
    }

    fun onFavoriteClick() {
        if (ad.value!!.isFavorite)
            removeAdFromFavoriteById(adId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    ad.onNext(ad.value!!.copy(isFavorite = false))
                }
        else
            addAdToFavoriteById(adId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    ad.onNext(ad.value!!.copy(isFavorite = true))
                }
    }

    fun changeFavoriteStatusById(id_product: Int) {
        val ad = similarAds.value!!.first { ad -> ad.id == id_product }
        if (ad.isFavorite)
            removeAdFromFavoriteById(id_product)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    similarAds.onNext((similarAds.value!!.map { el ->
                        if (el.id == id_product) ad.copy(
                            isFavorite = false
                        ) else el
                    }))
                }
        else
            addAdToFavoriteById(id_product)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    similarAds.onNext((similarAds.value!!.map { el ->
                        if (el.id == id_product) ad.copy(
                            isFavorite = true
                        ) else el
                    }))
                }
    }
}