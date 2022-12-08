package studio.stilip.proffer.app.fragments.ads.category.spares.car

import android.net.Uri
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.subjects.BehaviorSubject
import javax.inject.Inject

@HiltViewModel
class CategorySparesCarViewModel @Inject constructor() : ViewModel() {

    val imagesUri = BehaviorSubject.create<List<Uri>>()

    fun deleteImage(uri: Uri) {
        imagesUri.onNext(imagesUri.value?.minus(uri))
    }

    fun addImages(list: List<Uri>) {
        imagesUri.onNext(list)
        imagesUri.onNext(
            if (imagesUri.value.isNullOrEmpty()) list
            else imagesUri.value!!.plus(list).distinct()
        )
    }
}