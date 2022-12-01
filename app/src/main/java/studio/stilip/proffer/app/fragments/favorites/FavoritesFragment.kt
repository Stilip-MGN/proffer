package studio.stilip.proffer.app.fragments.favorites

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import studio.stilip.proffer.R
import studio.stilip.proffer.app.HostViewModel
import studio.stilip.proffer.app.fragments.favorites.ads.FavoritesAdsFragment
import studio.stilip.proffer.app.fragments.favorites.sellers.FavoritesSellersFragment
import studio.stilip.proffer.databinding.FragmentFavoritesBinding

@AndroidEntryPoint
class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private val hostViewModel: HostViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentFavoritesBinding.bind(view)

        hostViewModel.setBottomBarVisible(true)

        fun changeFocusTextView(newFocusView: TextView, oldFocusView: TextView) {
            newFocusView.hint = newFocusView.text.also { newFocusView.text = newFocusView.hint }
            oldFocusView.hint = oldFocusView.text.also { oldFocusView.text = oldFocusView.hint }
        }

        with(binding) {

            btnAds.setOnClickListener {
                if (btnAds.text.isEmpty()) {
                    changeFocusTextView(btnSub, btnAds)

                    this@FavoritesFragment.childFragmentManager
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment_fav, FavoritesAdsFragment())
                        .commit()
                }
            }

            btnSub.setOnClickListener {
                if (btnSub.text.isEmpty()) {
                    changeFocusTextView(btnAds, btnSub)

                    this@FavoritesFragment.childFragmentManager
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment_fav, FavoritesSellersFragment())
                        .commit()
                }
            }

            when (this@FavoritesFragment.childFragmentManager.findFragmentById(R.id.nav_host_fragment_fav)) {
                is FavoritesSellersFragment -> {
                    btnAds.hint = getString(R.string.btn_ads)
                    btnAds.text = ""
                }
                else -> {
                    btnSub.hint = getString(R.string.btn_subs)
                    btnSub.text = ""
                }
            }
        }
    }
}