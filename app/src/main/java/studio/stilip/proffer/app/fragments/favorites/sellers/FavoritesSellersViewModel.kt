package studio.stilip.proffer.app.fragments.favorites.sellers

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import studio.stilip.proffer.domain.entities.Seller
import studio.stilip.proffer.domain.usecase.favorites.GetSubscribedSellersUseCase
import javax.inject.Inject

@HiltViewModel
class FavoritesSellersViewModel @Inject constructor(
    private val getSubscribedSellers: GetSubscribedSellersUseCase,
) : ViewModel() {

    val sellers = BehaviorSubject.create<List<Seller>>().apply { getSubscribed() }

    private fun getSubscribed() {
        getSubscribedSellers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { seller ->
                sellers.onNext(seller)
            }
    }
}