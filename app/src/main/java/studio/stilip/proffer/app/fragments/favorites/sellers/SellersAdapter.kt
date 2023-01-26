package studio.stilip.proffer.app.fragments.favorites.sellers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import studio.stilip.proffer.databinding.CardSubBinding
import studio.stilip.proffer.domain.entities.Profile

class SellersAdapter : ListAdapter<Profile, SellersViewHolder>(SellerDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SellersViewHolder(
            CardSubBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: SellersViewHolder, position: Int) =
        holder.bind(getItem(position))
}