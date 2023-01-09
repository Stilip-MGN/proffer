package studio.stilip.proffer.app.fragments.ads.active

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import studio.stilip.proffer.R
import studio.stilip.proffer.app.HostViewModel
import studio.stilip.proffer.app.fragments.ads.MyAdsAdapter
import studio.stilip.proffer.databinding.FragmentAdsActiveBinding

@AndroidEntryPoint
class AdsActiveFragment : Fragment(R.layout.fragment_ads_active) {

    private val viewModel: AdsActiveViewModel by viewModels()
    private val hostViewModel: HostViewModel by activityViewModels()

    override fun onResume() {
        super.onResume()
        viewModel.getCurrentUserAds(hostViewModel.currentUser.value!!.id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentAdsActiveBinding.bind(view)

        val adapter = MyAdsAdapter()

        with(binding) {
            recMyAds.adapter = adapter
        }

        with(viewModel) {
            ads.subscribe { list ->
                adapter.submitList(list)
            }
        }
    }


}