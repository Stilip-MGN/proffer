package studio.stilip.proffer.app.fragments.search.filter.filter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import studio.stilip.proffer.R
import studio.stilip.proffer.app.HostViewModel
import studio.stilip.proffer.databinding.FragmentSearchFilterBinding

@AndroidEntryPoint
class SearchFilterFragment : Fragment(R.layout.fragment_search_filter) {

    private val hostViewModel: HostViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSearchFilterBinding.bind(view)

        hostViewModel.setBottomBarVisible(false)

        with(binding) {

            btnBack.setOnClickListener {
                requireActivity().onBackPressed()
            }
        }
    }
}
