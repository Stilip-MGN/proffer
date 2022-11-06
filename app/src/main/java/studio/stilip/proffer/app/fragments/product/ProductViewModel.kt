package studio.stilip.proffer.app.fragments.product

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import studio.stilip.proffer.app.fragments.product.ProductFragment.Companion.ID_AD
import studio.stilip.proffer.domain.entities.Ad
import studio.stilip.proffer.domain.entities.Seller
import studio.stilip.proffer.domain.usecase.search.GetAdByIdUseCase
import studio.stilip.proffer.domain.usecase.search.GetSellerByIdUseCase
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getAd: GetAdByIdUseCase,
    private val getSellerById: GetSellerByIdUseCase,
    stateHandle: SavedStateHandle
) : ViewModel() {
    private val adId: Int = stateHandle[ID_AD]!!
    var ad = BehaviorSubject.create<Ad>().apply { loadAd() }
    var seller = BehaviorSubject.create<Seller>()

    private fun loadAd() {
        getAd(adId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { ad ->
                this.ad.onNext(ad)
                loadProfile(ad.idSeller)
            }
    }

    private fun loadProfile(id: Int) {
        getSellerById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { seller ->
                this.seller.onNext(seller)
            }
    }
}