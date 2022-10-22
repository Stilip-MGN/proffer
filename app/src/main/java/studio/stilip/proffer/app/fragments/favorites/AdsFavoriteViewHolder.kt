package studio.stilip.proffer.app.fragments.favorites

import androidx.recyclerview.widget.RecyclerView
import studio.stilip.proffer.databinding.CardProductFavBinding
import studio.stilip.proffer.domain.entities.Ad

class AdsFavoriteViewHolder(
    private val binding: CardProductFavBinding,
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var ad: Ad

    fun bind(item: Ad) = with(binding) {
        ad = item

        price.text = ad.price.toString()
        name.text = ad.name
    }
}