package studio.stilip.proffer.app.fragments.ads

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import studio.stilip.proffer.domain.entities.Ad
import studio.stilip.proffer.domain.usecase.ad.GetAdsByUserIdUseCase
import studio.stilip.proffer.domain.usecase.ad.GetComplitedAdsByUserIdUseCase
import javax.inject.Inject

@HiltViewModel
class AdsViewModel @Inject constructor(
    private val getAdsByUserId: GetAdsByUserIdUseCase,
    private val getComplitedAdsByUserId: GetComplitedAdsByUserIdUseCase,
) : ViewModel() {

    private val activeAds =
        BehaviorSubject.create<List<Ad>>().also { it.subscribe { list -> ads.onNext(list) } }
    private val complitedAds = BehaviorSubject.create<List<Ad>>()
    val ads = BehaviorSubject.create<List<Ad>>()

    private fun getActiveAds(id: Int) {
        getAdsByUserId(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { list ->
                activeAds.onNext(list)
            }
    }

    private fun getComplitedAds(id: Int) {
        getComplitedAdsByUserId(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { list ->
                complitedAds.onNext(list)
            }
    }

    fun getCurrentUserAds(id: Int) {
        getActiveAds(id)
        getComplitedAds(id)
    }

    fun changeSelectAds(isActiveAds: Boolean) {
        if (isActiveAds) activeAds.subscribe { list -> ads.onNext(list) }
        else complitedAds.subscribe { list -> ads.onNext(list) }
    }

}