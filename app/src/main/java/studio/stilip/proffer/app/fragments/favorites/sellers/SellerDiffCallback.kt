package studio.stilip.proffer.app.fragments.favorites.sellers

import androidx.recyclerview.widget.DiffUtil
import studio.stilip.proffer.domain.entities.Profile

object SellerDiffCallback : DiffUtil.ItemCallback<Profile>() {

    override fun areItemsTheSame(oldItem: Profile, newItem: Profile) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Profile, newItem: Profile) =
        oldItem == newItem
}