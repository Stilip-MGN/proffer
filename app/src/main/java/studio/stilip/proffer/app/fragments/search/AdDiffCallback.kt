package studio.stilip.proffer.app.fragments.search

import androidx.recyclerview.widget.DiffUtil
import studio.stilip.proffer.domain.entities.Ad

object AdDiffCallback : DiffUtil.ItemCallback<Ad>() {

    override fun areItemsTheSame(oldItem: Ad, newItem: Ad) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Ad, newItem: Ad) =
        oldItem == newItem
}