package studio.stilip.proffer.data.repositories

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import studio.stilip.proffer.domain.entities.Ad
import studio.stilip.proffer.domain.repository_interface.AdRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AdRepositoryImpl @Inject constructor() : AdRepository {

    //хардкод
    val ads = listOf(
        Ad( 1, "", "Кружка", 100,"Большая и с картинкой", "Металлическая", "Екатеринбург, Толмачева 9", 1),
        Ad(2, "", "Сок", 50, "Апельсиновый", "1 л.", "Екатеринбург, Толмачева 9", 1),
        Ad(3, "", "Мотор", 1000, "Для надувной лодки", "15 л.с.", "Екатеринбург, Толмачева 9", 2),
        Ad(4, "", "Облако", 3400, "Белое и воздушное", "100 х 200", "Екатеринбург, Толмачева 9", 1),
        Ad(5, "", "Укроп", 1100, "Свежий и душистый", "10 кг.", "Екатеринбург, Толмачева 9", 1),
        Ad(6, "", "Тарелка", 190, "Использованная и одноразовая", "Пластик", "Екатеринбург, Толмачева 9", 2),
    )

    val fav = mutableListOf(
        Ad( 1, "", "Кружка", 100,"Большая и с картинкой", "Металлическая", "Екатеринбург, Толмачева 9", 1),
        Ad(2, "", "Сок", 50, "Апельсиновый", "1 л.", "Екатеринбург, Толмачева 9", 1),
        Ad(3, "", "Мотор", 1000, "Для надувной лодки", "15 л.с.", "Екатеринбург, Толмачева 9", 2),
    )

    override fun getRecommendedAds(): Single<List<Ad>> = Single.just(ads)

    override fun getFavoritesAds(): Single<List<Ad>> {
        return Single.just(fav)
    }

    override fun getAdById(id: Int): Single<Ad> = Single.just(ads.first { ad -> ad.id == id })

    override fun addAdToFavoriteById(id: Int): Completable {
        fav.add(ads.first { ad -> ad.id == id })
        return Completable.complete()
    }

    override fun removeAdFromFavoriteById(id: Int): Completable {
        fav.remove(ads.first { ad -> ad.id == id })
        return Completable.complete()
    }
}