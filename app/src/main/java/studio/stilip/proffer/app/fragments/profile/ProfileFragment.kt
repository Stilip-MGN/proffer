package studio.stilip.proffer.app.fragments.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import studio.stilip.proffer.R
import studio.stilip.proffer.app.HostViewModel
import studio.stilip.proffer.databinding.FragmentProfileBinding

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val hostViewModel: HostViewModel by activityViewModels()
    private val viewModel: ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentProfileBinding.bind(view)

        hostViewModel.setBottomBarVisible(true)

        with(binding) {
            btnControl.setOnClickListener {
                findNavController(view).navigate(
                    R.id.action_navigation_profile_to_edit_profile
                )
            }
        }

        //TODO Заменить хардкод
        viewModel.profile.subscribe({ profile ->
            with(binding) {
                name.text = profile.name
                place.hint = "Екатеринбург"
                val hardCountOrders = 0
                countOrder.hint = when (hardCountOrders % 10) {
                    0 -> "${getString(R.string.no)} ${getString(R.string.active)}"
                    1 -> "1 ${getString(R.string.active_1)}"
                    else -> "$hardCountOrders ${getString(R.string.active)}"
                }
                val hardCountAds = 3
                countAd.hint = when (hardCountAds % 10) {
                    0 -> "${getString(R.string.no)} ${getString(R.string.active)}"
                    1 -> "1 ${getString(R.string.active_1)}"
                    else -> "${hardCountAds} ${getString(R.string.active)}"
                }

                countReviews.hint = when (profile.countReviews % 10) {
                    0 -> "${getString(R.string.no)} ${getString(R.string.reviews)}"
                    1 -> "1 ${getString(R.string.reviews_1)}"
                    in 2..4 -> "${profile.countReviews} ${getString(R.string.reviews_2_4)}"
                    else -> "${profile.countReviews} ${getString(R.string.reviews)}"
                }

                Glide.with(this@ProfileFragment)
                    .load(profile.photo)
                    .centerCrop()
                    .error(R.drawable.ic_person_24)
                    .into(photo)

                btnAd.setOnClickListener {
                    findNavController(view).navigate(
                        R.id.action_navigation_profile_to_ads
                    )
                }
            }
        }, {
            println(it.message)
        })
    }
}