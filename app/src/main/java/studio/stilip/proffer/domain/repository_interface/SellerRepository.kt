package studio.stilip.proffer.domain.repository_interface

import io.reactivex.rxjava3.core.Single
import studio.stilip.proffer.domain.entities.Seller

interface SellerRepository {

    fun getSubscribedSellers(): Single<List<Seller>>

}