package studio.stilip.proffer.app.fragments.favorites

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import studio.stilip.proffer.R
import studio.stilip.proffer.app.HostViewModel
import studio.stilip.proffer.app.fragments.favorites.sellers.FavoritesSellersFragment
import studio.stilip.proffer.databinding.FragmentFavoritesBinding

@AndroidEntryPoint
class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private val hostViewModel: HostViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentFavoritesBinding.bind(view)

        hostViewModel.setBottomBarVisible(true)

        val navHostFragment = this@FavoritesFragment.childFragmentManager
            .findFragmentById(R.id.nav_host_fragment_fav) as NavHostFragment

        fun changeSelectedTextView(newFocusView: TextView, oldFocusView: TextView) {
            newFocusView.setTextColor(getColor(newFocusView.context, R.color.black))
            oldFocusView.setTextColor(getColor(oldFocusView.context, R.color.grey))
        }

        with(binding) {

            btnAds.setOnClickListener {
                if (btnAds.currentTextColor == getColor(btnAds.context, R.color.grey)) {
                    changeSelectedTextView(btnAds, btnSub)

                    navHostFragment.navController.navigate(R.id.navigation_favorites_ads)
                }
            }

            btnSub.setOnClickListener {
                if (btnSub.currentTextColor == getColor(btnSub.context, R.color.grey)) {
                    changeSelectedTextView(btnSub, btnAds)

                    navHostFragment.navController.navigate(R.id.navigation_favorites_subs)
                }
            }

            when (navHostFragment.childFragmentManager.fragments[0]) {
                is FavoritesSellersFragment -> changeSelectedTextView(btnSub, btnAds)
                else -> changeSelectedTextView(btnAds, btnSub)
            }
        }
    }
}