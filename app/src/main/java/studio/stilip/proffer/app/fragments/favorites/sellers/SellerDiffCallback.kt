package studio.stilip.proffer.app.fragments.favorites.sellers

import androidx.recyclerview.widget.DiffUtil
import studio.stilip.proffer.domain.entities.Seller

object SellerDiffCallback : DiffUtil.ItemCallback<Seller>() {

    override fun areItemsTheSame(oldItem: Seller, newItem: Seller) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Seller, newItem: Seller) =
        oldItem == newItem
}