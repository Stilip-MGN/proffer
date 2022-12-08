package studio.stilip.proffer.app.fragments.ads.category.spares.car

import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import studio.stilip.proffer.databinding.CardPhotoBinding

class PhotoViewHolder(
    private val binding: CardPhotoBinding,
    private val onDeleteClicked: (Uri) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var uri: Uri

    fun bind(item: Uri) = with(binding) {
        uri = item
        Glide.with(photo).load(uri).into(photo)

        btnDelete.setOnClickListener {
            onDeleteClicked(uri)
        }
    }
}