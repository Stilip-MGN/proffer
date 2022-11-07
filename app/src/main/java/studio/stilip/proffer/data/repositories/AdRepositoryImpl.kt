package studio.stilip.proffer.data.repositories

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import studio.stilip.proffer.data.dto.toDomain
import studio.stilip.proffer.data.entities.AdEntityForApi
import studio.stilip.proffer.data.entities.AdFavoriteApi
import studio.stilip.proffer.domain.entities.Ad
import studio.stilip.proffer.domain.repository_interface.AdRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AdRepositoryImpl @Inject constructor() : AdRepository {

    //хардкод
    val ads = listOf(
        AdEntityForApi( 1, "", "Кружка", 100,"Большая и с картинкой", "Металлическая", "Екатеринбург, Толмачева 9", 1),
        AdEntityForApi(2, "", "Сок", 50, "Апельсиновый", "1 л.", "Екатеринбург, Толмачева 9", 1),
        AdEntityForApi(3, "", "Мотор", 1000, "Для надувной лодки", "15 л.с.", "Екатеринбург, Толмачева 9", 2),
        AdEntityForApi(4, "", "Облако", 3400, "Белое и воздушное", "100 х 200", "Екатеринбург, Толмачева 9", 1),
        AdEntityForApi(5, "", "Укроп", 1100, "Свежий и душистый", "10 кг.", "Екатеринбург, Толмачева 9", 1),
        AdEntityForApi(6, "", "Тарелка", 190, "Использованная и одноразовая", "Пластик", "Екатеринбург, Толмачева 9", 2),
    )

    val fav = mutableListOf(
        AdFavoriteApi(1, 1, 1),
        AdFavoriteApi(2, 1, 2),
        AdFavoriteApi(3, 1, 3),
    )

    override fun getRecommendedAds(): Single<List<Ad>> =
        Single.just(ads.map { adApi ->
            adApi.toDomain()
                .also { ad -> ad.isFavorite = fav.any { favTable -> favTable.id_ad == ad.id } }
        })

    override fun getFavoritesAds(): Single<List<Ad>> {
        return Single.just(ads.map { adApi ->
            adApi.toDomain()
                .also { ad -> ad.isFavorite = fav.any { favTable -> favTable.id_ad == ad.id } }
        }.filter { ad -> ad.isFavorite })
    }

    override fun getAdById(id: Int): Single<Ad> =
        Single.just(ads.first { ad -> ad.id == id }.toDomain()
            .also { ad -> ad.isFavorite = fav.any { favTable -> favTable.id_ad == ad.id } })

    override fun addAdToFavoriteById(id: Int): Completable {
        fav.add(AdFavoriteApi(fav.count(), 1, id))
        return Completable.complete()
    }

    override fun removeAdFromFavoriteById(id: Int): Completable {
        fav.removeIf { ad -> ad.id_ad == id }
        return Completable.complete()
    }
}