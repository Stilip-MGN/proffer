package studio.stilip.proffer.data.repositories

import io.reactivex.Completable
import io.reactivex.Single
import studio.stilip.proffer.data.api.RetrofitServiceAd
import studio.stilip.proffer.data.dto.toData
import studio.stilip.proffer.data.dto.toDomain
import studio.stilip.proffer.data.entities.AdEntityForApi
import studio.stilip.proffer.data.entities.AdFavoriteApi
import studio.stilip.proffer.domain.entities.Ad
import studio.stilip.proffer.domain.repository_interface.AdRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AdRepositoryImpl @Inject constructor(
    private val retrofitService: RetrofitServiceAd,
) : AdRepository {

    //хардкод
    val ads = mutableListOf(
        AdEntityForApi( 1, listOf(
            "https://picsum.photos/300",
            "https://picsum.photos/301",
            "https://picsum.photos/302",
            "https://picsum.photos/303",
        ), "Кружка", 100.0,"88007000628","Большая и с картинкой", "Металлическая", true,"Екатеринбург, Толмачева 9", listOf(),3),
        AdEntityForApi(2, listOf(
            "https://picsum.photos/420",
            "https://picsum.photos/421",
        ), "Шины", 30000.0, "88007000628","Зимние", "Нагрузка до 100 кг", true,"Екатеринбург, Толмачева 9", listOf("Шины, диски и колёса","Для автомобиля"),1),
        AdEntityForApi(3, listOf(
            "https://picsum.photos/520",
            "https://picsum.photos/521",
        ), "Мотор", 90000.0,"88007000628", "Бензиновый мотор четвертого поколения", "15 л.с.",true, "Екатеринбург, Толмачева 9", listOf("Запчасти","Для автомобиля"),2),
        AdEntityForApi(4, emptyList(), "Облако", 3400.0, "88007000628","Белое и воздушное", "100 х 200",false, "Екатеринбург, Толмачева 9",listOf(), 1),
        AdEntityForApi(5, emptyList(), "Укроп", 1100.0,"88007000628", "Свежий и душистый", "10 кг.",true, "Екатеринбург, Толмачева 9",listOf(), 3),
        AdEntityForApi(6, emptyList(), "Тарелка", 190.0,"88007000628", "Использованная и одноразовая", "Пластик", true,"Екатеринбург, Толмачева 9", listOf(),2),
    )

    val complAds = mutableListOf(
        AdEntityForApi( 1, emptyList(), "Свеча", 1000.0,"88007000628","Новая, на запчасти", "Металлическая", true,"Екатеринбург, Толмачева 9", listOf(),1),
        AdEntityForApi(2, emptyList(), "Шины Bridgestone", 20000.0, "88007000628","Летние", "Нагрузка до 100 кг", true,"Екатеринбург, Толмачева 9", listOf(),1),
    )

    val fav = mutableListOf(
        AdFavoriteApi(1, 1, 1),
        AdFavoriteApi(2, 1, 2),
        AdFavoriteApi(3, 1, 3),
    )

    override fun getRecommendedAds(userId: Int): Single<List<Ad>> =
        retrofitService.getRecommendAds(userId.toString())

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

    override fun getSimilarAds(id: Int): Single<List<Ad>> =
        Single.just(ads.map { adApi ->
            adApi.toDomain()
                .also { ad -> ad.isFavorite = fav.any { favTable -> favTable.id_ad == ad.id } }
        }.filter { ad -> ad.id % 2 == id % 2 && ad.id != id })

    override fun addAd(ad: Ad): Completable {
        ads.add(ad.toData())
        return Completable.complete()
    }

    override fun getAdsByUserId(id: Int): Single<List<Ad>> =
        Single.just(ads.filter { ad -> ad.idSeller == id }.map { adApi -> adApi.toDomain() })

    override fun getAdsContainsString(string: String): Single<List<Ad>> {
        return Single.just(ads.map { adApi ->
            adApi.toDomain()
                .also { ad -> ad.isFavorite = fav.any { favTable -> favTable.id_ad == ad.id } }
        }.filter { ad -> ad.name.contains(string, true) })
    }

    override fun getComplitedAdsByUserId(id: Int): Single<List<Ad>> =
        Single.just(complAds.filter { ad -> ad.idSeller == id }.map { adApi -> adApi.toDomain() })

    override fun saveEditAd(ad: Ad): Completable {
        ads.removeIf { adFromList -> ad.id == adFromList.id }
        ads.add(ad.toData())
        return Completable.complete()
    }

    override fun deleteAdById(id: Int): Completable {
        ads.removeIf { ad -> id == ad.id }
        fav.removeIf { ad -> id == ad.id_ad }
        return Completable.complete()
    }
}