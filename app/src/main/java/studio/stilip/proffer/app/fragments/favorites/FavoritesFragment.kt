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
            var isFav = true
            recFav.adapter = adAdapter
            recSubs.adapter = sellersAdapter

            fun changeRecycle() {
                if (isFav) {
                    recFav.visibility = View.VISIBLE
                    recSubs.visibility = View.GONE
                    btnSub.hint = getString(R.string.btn_subs)
                    btnSub.text = ""
                    btnAds.text = getString(R.string.btn_ads)
                    btnAds.hint = ""
                } else {
                    recSubs.visibility = View.VISIBLE
                    recFav.visibility = View.GONE
                    btnAds.hint = getString(R.string.btn_ads)
                    btnAds.text = ""
                    btnSub.text = getString(R.string.btn_subs)
                    btnSub.hint = ""
                }
            }

            viewModel.isFav.subscribe {
                isFav = it
                changeRecycle()
            }

            btnAds.setOnClickListener {
                if (!isFav) viewModel.isFav.onNext(true)
            }

            btnSub.setOnClickListener {
                if (isFav) viewModel.isFav.onNext(false)
            }
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