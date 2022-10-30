package studio.stilip.proffer.app.fragments.search

import androidx.recyclerview.widget.RecyclerView
import studio.stilip.proffer.databinding.CardProductBinding
import studio.stilip.proffer.domain.entities.Ad

class AdListViewHolder(
    private val binding: CardProductBinding,
    private val onItemClicked: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root){

    private lateinit var ad: Ad

    init {
        itemView.setOnClickListener {
            onItemClicked(ad.id)
        }
    }

    fun bind(item: Ad) = with(binding){
        ad = item

        price.text = ad.price.toString()
        name.text = ad.name
    }
}