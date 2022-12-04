package studio.stilip.proffer.app.fragments.ads

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation.findNavController
import studio.stilip.proffer.R
import studio.stilip.proffer.app.HostViewModel
import studio.stilip.proffer.databinding.FragmentCategorySparesCarBinding

class CategorySparesCarFragment : Fragment(R.layout.fragment_category_spares_car) {

    private val hostViewModel: HostViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCategorySparesCarBinding.bind(view)

        hostViewModel.setBottomBarVisible(false)

        with(binding) {
            btnBack.setOnClickListener {
                requireActivity().onBackPressed()
            }

            btnPlaceAd.setOnClickListener {
                findNavController(view).navigate(
                    R.id.action_navigation_category_spares_car_to_navigation_ads
                )
            }
        }
    }
}