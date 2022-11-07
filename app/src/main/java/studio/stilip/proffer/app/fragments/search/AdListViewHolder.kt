package studio.stilip.proffer.app.fragments.search

import android.util.Log
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import studio.stilip.proffer.R
import studio.stilip.proffer.databinding.CardProductBinding
import studio.stilip.proffer.domain.entities.Ad

class AdListViewHolder(
    private val binding: CardProductBinding,
    private val onItemClicked: (Int) -> Unit,
    private val onFavoriteClicked: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var ad: Ad

    init {
        itemView.setOnClickListener {
            onItemClicked(ad.id)
        }
    }

    fun bind(item: Ad) = with(binding) {
        ad = item

        price.text = ad.price.toString()
        name.text = ad.name

        fun changeBtnFavorite() {
            if (ad.isFavorite) {
                btnFavorite.setImageResource(R.drawable.ic_favorite_24)
                btnFavorite.setColorFilter(ContextCompat.getColor(btnFavorite.context, R.color.red))
            } else {
                btnFavorite.setImageResource(R.drawable.ic_favorite_empty_24)
                btnFavorite.setColorFilter(ContextCompat.getColor(btnFavorite.context, R.color.black))
            }
        }
        changeBtnFavorite()

        btnFavorite.setOnClickListener {
            onFavoriteClicked(ad.id)
            changeBtnFavorite()
        }
    }
}