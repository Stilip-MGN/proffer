package studio.stilip.proffer.app.fragments.search

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import studio.stilip.proffer.R
import studio.stilip.proffer.app.fragments.product.ProductFragment.Companion.ID_AD
import studio.stilip.proffer.databinding.FragmentSearchBinding

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private val viewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSearchBinding.bind(view)

        val adAdapter = AdListAdapter({ id ->
            val args = Bundle().apply {
                putInt(ID_AD, id)
            }
            findNavController(view).navigate(
                R.id.action_navigation_search_to_navigation_product,
                args
            )
        }, { id ->
            viewModel.changeFavoriteStatusById(id)
        })

        with(binding) {
            recRecommendations.adapter = adAdapter
        }

        viewModel.ads.subscribe { list ->
            adAdapter.submitList(list)
        }

    }

    //TODO исправить
    override fun onResume() {
        viewModel.getRecommended()
        super.onResume()
    }
}