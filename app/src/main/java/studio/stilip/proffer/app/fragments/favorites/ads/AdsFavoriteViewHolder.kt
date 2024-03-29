package studio.stilip.proffer.app.fragments.favorites.ads

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import studio.stilip.proffer.R
import studio.stilip.proffer.databinding.CardProductFavBinding
import studio.stilip.proffer.domain.entities.Ad

class AdsFavoriteViewHolder(
    private val binding: CardProductFavBinding,
    private val onItemClicked: (Int) -> Unit,
    private val onRemoveFavoriteClicked: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var ad: Ad

    init {
        itemView.setOnClickListener {
            onItemClicked(ad.id)
        }
    }

    fun bind(item: Ad) = with(binding) {
        ad = item

        Glide.with(this.photo)
            .load(ad.photos.firstOrNull() ?: "5")
            .centerCrop()
            .error(R.drawable.ic_do_not_disturb_24)
            .into(photo)

        price.text = ad.price.toString()
        name.text = ad.name

        close.setOnClickListener {
            onRemoveFavoriteClicked(ad.id)
        }
    }
}