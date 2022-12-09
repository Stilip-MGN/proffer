package studio.stilip.proffer.app.fragments.ads.category.spares.car

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import studio.stilip.proffer.databinding.CardPhotoBinding

class PhotosAdapter(
    private val onDeleteClicked: (Uri) -> Unit
) : ListAdapter<Uri, PhotoViewHolder>(StringDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PhotoViewHolder(
            CardPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onDeleteClicked
        )

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) =
        holder.bind(getItem(position))
}