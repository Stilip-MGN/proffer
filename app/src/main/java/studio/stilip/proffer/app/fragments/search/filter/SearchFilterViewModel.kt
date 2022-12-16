package studio.stilip.proffer.app.fragments.search.filter

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import studio.stilip.proffer.domain.entities.Ad
import studio.stilip.proffer.domain.usecase.search.AddAdToFavoriteByIdUseCase
import studio.stilip.proffer.domain.usecase.search.GetAdsContainsStringUseCase
import studio.stilip.proffer.domain.usecase.search.RemoveAdFromFavoriteByIdUseCase
import javax.inject.Inject

@HiltViewModel
class SearchFilterViewModel @Inject constructor(
    private val getAdsContainsString: GetAdsContainsStringUseCase,
    private val addAdToFavoriteById: AddAdToFavoriteByIdUseCase,
    private val removeAdFromFavoriteById: RemoveAdFromFavoriteByIdUseCase,
) : ViewModel() {

    val ads = BehaviorSubject.create<List<Ad>>().apply { emptyList<Ad>() }
    private val allAds = BehaviorSubject.create<List<Ad>>().apply { emptyList<Ad>() }
    val categories = mutableListOf<String>()
    var location: String = ""
    var priceFrom: Int = 0
    var priceTo: Int = 0
    var isBuy: Boolean = true
    var isSell: Boolean = false
    var searchQuery: String = ""

    fun changeFavoriteStatusById(id_product: Int) {
        val ad = ads.value!!.first { ad -> ad.id == id_product }
        if (ad.isFavorite)
            removeAdFromFavoriteById(id_product)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    ads.onNext((ads.value!!.map { el ->
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
                    ads.onNext((ads.value!!.map { el ->
                        if (el.id == id_product) ad.copy(
                            isFavorite = true
                        ) else el
                    }))
                }
    }

    fun findAdsContainsString(string: String) {
        getAdsContainsString(string)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { list ->
                allAds.onNext(list)
                filterAds()
            }
    }

    fun filterAds() {
        var newAds = allAds.value!!
        if (priceFrom != 0) newAds = newAds.filter { ad -> ad.price >= priceFrom }
        if (priceTo != 0) newAds = newAds.filter { ad -> ad.price <= priceTo }
        ads.onNext(newAds)
    }
}