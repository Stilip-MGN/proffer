package studio.stilip.proffer.app.fragments.search

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import studio.stilip.proffer.domain.entities.Ad
import studio.stilip.proffer.domain.usecase.search.GetRecommendedAdsUseCase
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getRecommendedAds: GetRecommendedAdsUseCase
) : ViewModel() {

    val ads = PublishSubject.create<List<Ad>>()!!

    fun getRecommended() {

        getRecommendedAds()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { bb ->
                ads.onNext(bb)
            }



    }
}