package studio.stilip.proffer.app.fragments.ads

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import studio.stilip.proffer.R
import studio.stilip.proffer.app.fragments.HostViewModel
import studio.stilip.proffer.databinding.FragmentCategorySelectionBinding

class CategorySelectionFragment : Fragment(R.layout.fragment_category_selection) {

    private val hostViewModel: HostViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCategorySelectionBinding.bind(view)

        hostViewModel.setBottomBarVisible(false)

        with(binding){
            btnBack.setOnClickListener {
                requireActivity().onBackPressed()
            }
        }
    }
}