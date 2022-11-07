package studio.stilip.proffer.app.fragments.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import studio.stilip.proffer.app.fragments.search.AdDiffCallback
import studio.stilip.proffer.databinding.CardProductFavBinding
import studio.stilip.proffer.domain.entities.Ad

class AdsFavoriteAdapter(
    private val onRemoveFavoriteClicked: (Int) -> Unit
) : ListAdapter<Ad, AdsFavoriteViewHolder>(AdDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AdsFavoriteViewHolder(
            CardProductFavBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onRemoveFavoriteClicked
        )

    override fun onBindViewHolder(holder: AdsFavoriteViewHolder, position: Int) =
        holder.bind(getItem(position))
}