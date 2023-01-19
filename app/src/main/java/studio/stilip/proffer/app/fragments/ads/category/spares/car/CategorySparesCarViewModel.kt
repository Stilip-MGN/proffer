package studio.stilip.proffer.app.fragments.ads.category.spares.car

import android.net.Uri
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import studio.stilip.proffer.domain.entities.Ad
import studio.stilip.proffer.domain.usecase.ad.AddAdUseCase
import javax.inject.Inject

@HiltViewModel
class CategorySparesCarViewModel @Inject constructor(
    private val addAd: AddAdUseCase,
) : ViewModel() {

    val imagesUri = BehaviorSubject.create<List<Uri>>()
    val successSaveAd = BehaviorSubject.create<Boolean>()

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
        addAd(ad)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                successSaveAd.onNext(true)
            }, {
                successSaveAd.onNext(false)
            })
    }
}