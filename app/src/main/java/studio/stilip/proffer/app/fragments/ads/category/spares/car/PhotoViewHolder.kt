package studio.stilip.proffer.app.fragments.ads.category.spares.car

import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import studio.stilip.proffer.databinding.CardPhotoBinding

class PhotoViewHolder(
    private val binding: CardPhotoBinding,
    private val onDeleteClicked: (Uri) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var uri: Uri

    fun bind(item: Uri) = with(binding) {
        uri = item
        photo.setImageURI(uri)

        btnDelete.setOnClickListener {
            onDeleteClicked(uri)
        }
    }
}