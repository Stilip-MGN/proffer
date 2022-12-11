package studio.stilip.proffer.app.fragments.ads

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import studio.stilip.proffer.app.fragments.search.AdDiffCallback
import studio.stilip.proffer.databinding.CardMyAdBinding
import studio.stilip.proffer.domain.entities.Ad

class MyAdsAdapter(
) : ListAdapter<Ad, MyAdViewHolder>(AdDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MyAdViewHolder(
            CardMyAdBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        )

    override fun onBindViewHolder(holder: MyAdViewHolder, position: Int) =
        holder.bind(getItem(position))
}