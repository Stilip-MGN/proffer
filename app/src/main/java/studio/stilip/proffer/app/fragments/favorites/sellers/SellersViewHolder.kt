package studio.stilip.proffer.app.fragments.favorites.sellers

import androidx.recyclerview.widget.RecyclerView
import studio.stilip.proffer.databinding.CardSubBinding
import studio.stilip.proffer.domain.entities.Profile

class SellersViewHolder(
    private val binding: CardSubBinding,
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var seller: Profile

    fun bind(item: Profile) = with(binding) {
        seller = item

        name.text = seller.name
        rating.text = seller.rating.toString()
        reviewsCount.text = seller.countReviews.toString()
    }
}