package studio.stilip.proffer.app.fragments.ads.category.spares.car.edit

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import studio.stilip.proffer.app.fragments.ads.active.AdsActiveFragment.Companion.ID_AD
import studio.stilip.proffer.data.UserCacheManager
import studio.stilip.proffer.domain.entities.Ad
import studio.stilip.proffer.domain.usecase.ad.DeleteAdByIdUseCase
import studio.stilip.proffer.domain.usecase.ad.SaveEditAdUseCase
import studio.stilip.proffer.domain.usecase.search.GetAdByIdUseCase
import javax.inject.Inject

@HiltViewModel
class CategorySparesCarEditViewModel @Inject constructor(
    private val saveEditAd: SaveEditAdUseCase,
    private val getAd: GetAdByIdUseCase,
    private val deleteAdById: DeleteAdByIdUseCase,
    stateHandle: SavedStateHandle
) : ViewModel() {
    private val adId: Int = stateHandle[ID_AD]!!
    var userId: Int = UserCacheManager.getUserId()
    val editAd = BehaviorSubject.create<Ad>().apply {
        loadAd()
    }
    val imagesUri = BehaviorSubject.create<List<Uri>>()
    val successSaveAd = BehaviorSubject.create<Boolean>()
    val successDeleteAd = BehaviorSubject.create<Boolean>()

    private fun loadAd() {
        getAd(adId, userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { ad ->
                editAd.onNext(ad)
            }
    }

    fun deleteImage(uri: Uri) {
        imagesUri.onNext(imagesUri.value!!.minus(uri))
    }

    fun addImages(list: List<Uri>) {
        imagesUri.onNext(
            if (imagesUri.value.isNullOrEmpty()) list
            else imagesUri.value!!.plus(list).distinct().take(10)
        )
    }

    fun saveAd(ad: Ad) {
        saveEditAd(ad)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                successSaveAd.onNext(true)
            }, {
                successSaveAd.onNext(false)
            })
    }

    fun deleteAd() {
        deleteAdById(adId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                successDeleteAd.onNext(true)
            }, {
                successDeleteAd.onNext(false)
            })
    }
}