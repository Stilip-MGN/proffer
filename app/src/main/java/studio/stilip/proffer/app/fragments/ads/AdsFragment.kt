package studio.stilip.proffer.app.fragments.ads

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import studio.stilip.proffer.R
import studio.stilip.proffer.databinding.FragmentAdsBinding
import androidx.navigation.Navigation.findNavController
import studio.stilip.proffer.app.HostViewModel

@AndroidEntryPoint
class AdsFragment : Fragment(R.layout.fragment_ads) {

    private val hostViewModel: HostViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentAdsBinding.bind(view)

        val adapter = MyAdsAdapter()

        hostViewModel.setBottomBarVisible(true)

        with(binding) {
            btnAddNewAd.setOnClickListener {
                findNavController(view).navigate(
                    R.id.action_navigation_ads_to_navigation_category_selection
                )
            }

            recMyAds.adapter = adapter
        }
    }
}