package studio.stilip.proffer.app.fragments.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import studio.stilip.proffer.databinding.CardProductBinding
import studio.stilip.proffer.domain.entities.Ad

class AdListAdapter(
    private val onItemClicked: (Int) -> Unit,
    private val onFavoriteClicked: (Int) -> Unit
) : ListAdapter<Ad, AdListViewHolder>(AdDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AdListViewHolder(
            CardProductBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onItemClicked,
            onFavoriteClicked
        )

    override fun onBindViewHolder(holder: AdListViewHolder, position: Int) =
        holder.bind(getItem(position))
}