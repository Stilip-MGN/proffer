package studio.stilip.proffer.app.fragments.ads.completed

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import studio.stilip.proffer.R
import studio.stilip.proffer.app.HostViewModel
import studio.stilip.proffer.app.fragments.ads.MyAdsAdapter
import studio.stilip.proffer.databinding.FragmentAdsCompletedBinding

@AndroidEntryPoint
class AdsCompletedFragment : Fragment(R.layout.fragment_ads_completed) {

    private val viewModel: AdsCompletedViewModel by viewModels()
    private val hostViewModel: HostViewModel by activityViewModels()

    override fun onResume() {
        super.onResume()
        viewModel.getCurrentUserAds(hostViewModel.currentUser.value!!.id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentAdsCompletedBinding.bind(view)

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