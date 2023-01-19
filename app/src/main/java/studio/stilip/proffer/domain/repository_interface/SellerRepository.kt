package studio.stilip.proffer.domain.repository_interface

import io.reactivex.Single
import studio.stilip.proffer.domain.entities.Seller

interface SellerRepository {

    fun getSubscribedSellers(): Single<List<Seller>>

    fun getSellerById(id: Int): Single<Seller>
}