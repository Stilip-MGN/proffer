package studio.stilip.proffer.app.fragments.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import studio.stilip.proffer.R
import studio.stilip.proffer.databinding.FragmentSearchBinding

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {
    
    private val viewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSearchBinding.bind(view)

        val adAdapter = AdListAdapter()

        with(binding){
            recRecommendations.adapter = adAdapter
            textRecommendations.setOnClickListener {
                viewModel.getRecommended()
            }
        }

       viewModel.ads
            .subscribe { list ->
            adAdapter.submitList(list)
        }

    }
}