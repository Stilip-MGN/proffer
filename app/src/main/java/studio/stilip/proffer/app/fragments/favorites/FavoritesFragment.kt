package studio.stilip.proffer.app.fragments.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import studio.stilip.proffer.R
import studio.stilip.proffer.databinding.FragmentFavoritesBinding

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentFavoritesBinding.bind(view)

    }

}