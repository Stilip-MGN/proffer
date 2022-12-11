package studio.stilip.proffer.app.fragments.ads

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import studio.stilip.proffer.domain.entities.Ad
import studio.stilip.proffer.domain.usecase.ad.GetAdsByUserIdUseCase
import javax.inject.Inject

@HiltViewModel
class AdsViewModel @Inject constructor(
    private val getAdsByUserId: GetAdsByUserIdUseCase,
) : ViewModel() {

    val ads = BehaviorSubject.create<List<Ad>>()

    fun getCurrentUserAds(id: Int) {
        getAdsByUserId(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { list ->
                ads.onNext(list)
            }
    }

}