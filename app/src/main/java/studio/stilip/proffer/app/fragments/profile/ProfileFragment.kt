package studio.stilip.proffer.app.fragments.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import studio.stilip.proffer.R
import studio.stilip.proffer.app.fragments.HostViewModel
import studio.stilip.proffer.databinding.FragmentProfileBinding

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val hostViewModel: HostViewModel by activityViewModels()
    private val viewModel: ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentProfileBinding.bind(view)

        viewModel.profile.subscribe { profile ->
            with(binding) {
                name.text = profile.name
                place.hint = profile.place
                countOrder.hint = when (profile.countOrders % 10) {
                    0 -> "${getString(R.string.no)} ${getString(R.string.active)}"
                    1 -> "1 ${getString(R.string.active_1)}"
                    else -> "${profile.countOrders} ${getString(R.string.active)}"
                }

                countAd.hint = when (profile.countAds % 10) {
                    0 -> "${getString(R.string.no)} ${getString(R.string.active)}"
                    1 -> "1 ${getString(R.string.active_1)}"
                    else -> "${profile.countAds} ${getString(R.string.active)}"
                }

                countReviews.hint = when (profile.countReviews % 10) {
                    0 -> "${getString(R.string.no)} ${getString(R.string.reviews)}"
                    1 -> "1 ${getString(R.string.reviews_1)}"
                    in 2..4 -> "${profile.countReviews} ${getString(R.string.reviews_2_4)}"
                    else -> "${profile.countReviews} ${getString(R.string.reviews)}"
                }

            }
        }
    }

    override fun onStart() {
        super.onStart()
        hostViewModel.setBottomBarVisible(true)
    }
}