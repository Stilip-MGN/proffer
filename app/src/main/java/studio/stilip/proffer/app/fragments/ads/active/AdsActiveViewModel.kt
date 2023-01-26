package studio.stilip.proffer.app.fragments.ads.active

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import studio.stilip.proffer.domain.entities.Ad
import studio.stilip.proffer.domain.usecase.ad.GetAdsByUserIdUseCase
import javax.inject.Inject

@HiltViewModel
class AdsActiveViewModel @Inject constructor(
    private val getAdsByUserId: GetAdsByUserIdUseCase,
) : ViewModel() {

    val ads = BehaviorSubject.create<List<Ad>>()

    private fun getActiveAds(id: Int) {
        getAdsByUserId(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { list ->
                ads.onNext(list)
            }
    }

    fun getCurrentUserAds(id: Int) {
        getActiveAds(id)
    }

}