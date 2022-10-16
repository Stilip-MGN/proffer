package studio.stilip.proffer.app.fragments.search

import androidx.lifecycle.ViewModel
import studio.stilip.proffer.domain.entities.Ad

class SearchViewModel : ViewModel() {

    var ads: List<Ad> = listOf(
        Ad(1,"",100,"Кружка"),
        Ad(2,"",50,"Сок"),
        Ad(3,"",1000,"Мотор"),
        Ad(4,"",3400,"Облако"),
        Ad(5,"",1100,"Укроп"),
        Ad(6,"",190,"Тарелка"),
    )

}