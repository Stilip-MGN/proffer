package studio.stilip.proffer.app.fragments.favorites.ads

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import studio.stilip.proffer.R
import studio.stilip.proffer.app.fragments.product.ProductFragment
import studio.stilip.proffer.databinding.FragmentFavoritesAdsBinding

@AndroidEntryPoint
class FavoritesAdsFragment : Fragment(R.layout.fragment_favorites_ads) {

    private val viewModel: FavoritesAdsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentFavoritesAdsBinding.bind(view)

        val adAdapter = AdsFavoriteAdapter({ id ->
            val args = Bundle().apply {
                putInt(ProductFragment.ID_AD, id)
            }

            requireActivity().findNavController(R.id.nav_host_fragment_activity_main).navigate(
                R.id.action_navigation_favorites_to_navigation_product,
                args
            )
        }, { id ->
            viewModel.removeFromFavoriteById(id)
        })

        with(binding) {
            recFav.adapter = adAdapter
        }

        with(viewModel) {
            ads.subscribe { list ->
                adAdapter.submitList(list)
            }
        }
    }

    override fun onResume() {
        viewModel.getFavorites()
        super.onResume()
    }
}