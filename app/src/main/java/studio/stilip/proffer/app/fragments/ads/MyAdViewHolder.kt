package studio.stilip.proffer.app.fragments.ads

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import studio.stilip.proffer.R
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

        Glide.with(this.photo)
            .load(ad.photos.firstOrNull() ?: "")
            .centerCrop()
            .error(R.drawable.ic_do_not_disturb_24)
            .into(photo)

        name.text = ad.name
        price.text = ad.price.toString()
        viewed.text = "34"
        favorite.text = "4"
    }
}