package studio.stilip.proffer.app.fragments.search.filter

import android.os.Bundle
import android.view.View
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import studio.stilip.proffer.R
import studio.stilip.proffer.app.HostViewModel
import studio.stilip.proffer.app.fragments.product.ProductFragment
import studio.stilip.proffer.app.fragments.search.AdListAdapter
import studio.stilip.proffer.databinding.FragmentSearchWithFilterBinding

@AndroidEntryPoint
class SearchWithFilterFragment : Fragment(R.layout.fragment_search_with_filter) {

    private val hostViewModel: HostViewModel by activityViewModels()
    private val viewModel: SearchFilterViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSearchWithFilterBinding.bind(view)

        hostViewModel.setBottomBarVisible(false)

        val adapter = AdListAdapter({ id ->
            val args = Bundle().apply {
                putInt(ProductFragment.ID_AD, id)
            }
            findNavController(view).navigate(
                R.id.action_navigation_search_with_filter_to_product,
                args
            )
        }, { id ->
            viewModel.changeFavoriteStatusById(id)
        })

        viewModel.ads.subscribe { list ->
            adapter.submitList(list)
        }

        with(binding) {
            recAds.adapter = adapter
            search.setQuery(viewModel.searchQuery, false)

            btnFilter.setOnClickListener {
                findNavController(view).navigate(
                    R.id.action_navigation_search_with_filter_to_search_filter
                )
            }

            btnBack.setOnClickListener {
                requireActivity().onBackPressed()
            }

            search.setOnQueryTextListener(object : OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query.isNullOrEmpty())
                        return false
                    viewModel.findAdsContainsString(query)
                    search.clearFocus()
                    return true
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    viewModel.searchQuery = query ?: ""
                    return false
                }
            })
        }
    }
}
