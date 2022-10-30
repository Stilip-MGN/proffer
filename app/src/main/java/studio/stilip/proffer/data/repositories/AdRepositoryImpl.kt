package studio.stilip.proffer.data.repositories

import io.reactivex.rxjava3.core.Single
import studio.stilip.proffer.domain.entities.Ad
import studio.stilip.proffer.domain.repository_interface.AdRepository
import javax.inject.Inject

class AdRepositoryImpl @Inject constructor() : AdRepository {

    //хардкод
    val ads = listOf(
        Ad( 1, "", "Кружка", 100,"Большая и с картинкой", "Металлическая", "Екатеринбург, Толмачева 9", 1),
        Ad(2, "", "Сок", 50, "Апельсиновый", "1 л.", "Екатеринбург, Толмачева 9", 1),
        Ad(3, "", "Мотор", 1000, "Для надувной лодки", "15 л.с.", "Екатеринбург, Толмачева 9", 1),
        Ad(4, "", "Облако", 3400, "Белое и воздушное", "100 х 200", "Екатеринбург, Толмачева 9", 1),
        Ad(5, "", "Укроп", 1100, "Свежий и душистый", "10 кг.", "Екатеринбург, Толмачева 9", 1),
        Ad(6, "", "Тарелка", 190, "Использованная и одноразовая", "Пластик", "Екатеринбург, Толмачева 9", 1),
    )

    val fav = listOf(
        Ad( 1, "", "Кружка", 100,"Большая и с картинкой", "Металлическая", "Екатеринбург, Толмачева 9", 1),
        Ad(2, "", "Сок", 50, "Апельсиновый", "1 л.", "Екатеринбург, Толмачева 9", 1),
        Ad(3, "", "Мотор", 1000, "Для надувной лодки", "15 л.с.", "Екатеринбург, Толмачева 9", 1),
    )

    override fun getRecommendedAds(): Single<List<Ad>> = Single.just(ads)

    override fun getFavoritesAds(): Single<List<Ad>> = Single.just(fav)

    override fun getAdById(id: Int): Single<Ad> = Single.just(ads.first { ad -> ad.id == id })
}