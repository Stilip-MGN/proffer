package studio.stilip.proffer.app.fragments.favorites.sellers

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import studio.stilip.proffer.domain.entities.Profile
import studio.stilip.proffer.domain.usecase.favorites.GetSubscribedSellersUseCase
import javax.inject.Inject

@HiltViewModel
class FavoritesSellersViewModel @Inject constructor(
    private val getSubscribedSellers: GetSubscribedSellersUseCase,
) : ViewModel() {

    val sellers = BehaviorSubject.create<List<Profile>>().apply { getSubscribed() }

    private fun getSubscribed() {
        getSubscribedSellers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { seller ->
                sellers.onNext(seller)
            }
    }
}