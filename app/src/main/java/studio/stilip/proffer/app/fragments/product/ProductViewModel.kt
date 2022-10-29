package studio.stilip.proffer.app.fragments.product

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import studio.stilip.proffer.app.fragments.product.ProductFragment.Companion.ID_AD
import studio.stilip.proffer.domain.entities.Ad
import studio.stilip.proffer.domain.usecase.search.GetAdByIdUseCase
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getAd: GetAdByIdUseCase,
    stateHandle: SavedStateHandle
) : ViewModel() {
    val adId: Int = stateHandle[ID_AD]!!
    var ad = BehaviorSubject.create<Ad>().apply { loadAd() }

    private fun loadAd() {
        getAd(adId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { ad ->
                this.ad.onNext(ad)
            }
    }
}