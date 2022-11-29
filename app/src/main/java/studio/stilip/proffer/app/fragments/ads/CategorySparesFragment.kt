package studio.stilip.proffer.app.fragments.ads

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import studio.stilip.proffer.R
import studio.stilip.proffer.app.HostViewModel
import studio.stilip.proffer.databinding.FragmentCategorySparesBinding

class CategorySparesFragment : Fragment(R.layout.fragment_category_spares) {

    private val hostViewModel: HostViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCategorySparesBinding.bind(view)

        hostViewModel.setBottomBarVisible(false)

        with(binding) {
            btnBack.setOnClickListener {
                requireActivity().onBackPressed()
            }
        }
    }
}