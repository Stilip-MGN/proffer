package studio.stilip.proffer.data.repositories

import io.reactivex.rxjava3.core.Single
import studio.stilip.proffer.domain.entities.Seller
import studio.stilip.proffer.domain.repository_interface.SellerRepository
import javax.inject.Inject

class SellerRepositoryImpl @Inject constructor() : SellerRepository {

    //хардкод
    val sellers = listOf(
        Seller(1, "Иван Иванов", "", 4.3, 123),
        Seller(2, "Петр Вайко", "", 2.3, 25),
        Seller(3, "Олег Воробьев", "", 4.7, 540),
        Seller(4, "Арарат Мунслинов", "", 4.1, 12),
    )

    override fun getSubscribedSellers(): Single<List<Seller>> = Single.just(sellers)

    override fun getSellerById(id: Int): Single<Seller> =
        Single.just(sellers.first { seller -> seller.id == id })

}