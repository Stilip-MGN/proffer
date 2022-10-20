package studio.stilip.proffer.app.fragments.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import studio.stilip.proffer.R
import studio.stilip.proffer.databinding.FragmentFavoritesBinding

@AndroidEntryPoint
class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private val viewModel: FavoritesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentFavoritesBinding.bind(view)

        val adAdapter = AdsFavoriteAdapter()
        val sellersAdapter = SellersAdapter()

        with(binding) {
            recFav.adapter = adAdapter
            recSubs.adapter = sellersAdapter
        }

        with(viewModel) {
            ads.subscribe { list ->
                adAdapter.submitList(list)
            }
            sellers.subscribe { list ->
                sellersAdapter.submitList(list)
            }
        }
    }
}