package studio.stilip.proffer.app.fragments.ads.completed

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import studio.stilip.proffer.domain.entities.Ad
import studio.stilip.proffer.domain.usecase.ad.GetComplitedAdsByUserIdUseCase
import javax.inject.Inject

@HiltViewModel
class AdsCompletedViewModel @Inject constructor(
    private val getComplitedAdsByUserId: GetComplitedAdsByUserIdUseCase,
) : ViewModel() {

    val ads = BehaviorSubject.create<List<Ad>>()

    private fun getComplitedAds(id: Int) {
        getComplitedAdsByUserId(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { list ->
                ads.onNext(list)
            }
    }

    fun getCurrentUserAds(id: Int) {
        getComplitedAds(id)
    }

}