package studio.stilip.proffer.app.fragments.search.filter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import studio.stilip.proffer.R
import studio.stilip.proffer.app.HostViewModel
import studio.stilip.proffer.databinding.FragmentSearchWithFilterBinding

@AndroidEntryPoint
class SearchWithFilterFragment : Fragment(R.layout.fragment_search_with_filter) {

    private val hostViewModel: HostViewModel by activityViewModels()
    private val viewModel: SearchFilterViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSearchWithFilterBinding.bind(view)

        hostViewModel.setBottomBarVisible(false)

        with(binding) {
            btnFilter.setOnClickListener {
                Navigation.findNavController(view).navigate(
                    R.id.action_navigation_search_with_filter_to_search_filter
                )
            }

            btnBack.setOnClickListener {
                requireActivity().onBackPressed()
            }
        }
    }
}
