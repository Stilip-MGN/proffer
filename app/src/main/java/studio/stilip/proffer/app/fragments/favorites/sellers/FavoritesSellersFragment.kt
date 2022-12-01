package studio.stilip.proffer.app.fragments.favorites.sellers

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import studio.stilip.proffer.R
import studio.stilip.proffer.databinding.FragmentFavoritesSellersBinding

@AndroidEntryPoint
class FavoritesSellersFragment : Fragment(R.layout.fragment_favorites_sellers) {

    private val viewModel: FavoritesSellersViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentFavoritesSellersBinding.bind(view)

        val adapter = SellersAdapter()

        with(binding) {
            recSellers.adapter = adapter
        }

        with(viewModel) {
            sellers.subscribe { list ->
                adapter.submitList(list)
            }
        }
    }
}