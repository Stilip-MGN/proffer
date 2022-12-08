package studio.stilip.proffer.app.fragments.ads.category.spares.car

import android.net.Uri
import androidx.recyclerview.widget.DiffUtil

object StringDiffCallback : DiffUtil.ItemCallback<Uri>() {

    override fun areItemsTheSame(oldItem: Uri, newItem: Uri) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Uri, newItem: Uri) =
        oldItem == newItem
}