package studio.stilip.proffer.app.fragments.ads

import androidx.recyclerview.widget.RecyclerView
import studio.stilip.proffer.databinding.CardMyAdBinding
import studio.stilip.proffer.domain.entities.Ad

class MyAdViewHolder(
    private val binding: CardMyAdBinding,
    private val onItemClicked: (Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var ad: Ad

    init {
        itemView.setOnClickListener {
            onItemClicked(ad.id)
        }
    }

    fun bind(item: Ad) = with(binding) {
        ad = item

        name.text = ad.name
        price.text = ad.price.toString()
        viewed.text = "34"
        favorite.text = "4"
    }
}