package studio.stilip.proffer.app.fragments.search.filter.filter

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation.findNavController
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import studio.stilip.proffer.R
import studio.stilip.proffer.app.HostViewModel
import studio.stilip.proffer.app.fragments.search.filter.SearchFilterViewModel
import studio.stilip.proffer.databinding.FragmentSearchFilterBinding

@AndroidEntryPoint
class SearchFilterFragment : Fragment(R.layout.fragment_search_filter) {

    private val hostViewModel: HostViewModel by activityViewModels()
    private val viewModel: SearchFilterViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSearchFilterBinding.bind(view)

        hostViewModel.setBottomBarVisible(false)

        with(binding) {

            btnBack.setOnClickListener {
                requireActivity().onBackPressed()
            }

            btnApply.setOnClickListener {
                if (!(btnBuy.isChecked or btnSell.isChecked)) {
                    Toast.makeText(
                        activity,
                        getString(R.string.one_ad_type_must_be_selected),
                        Toast.LENGTH_LONG
                    ).show()
                    return@setOnClickListener
                }
                viewModel.isBuy = btnBuy.isChecked
                viewModel.isSell = btnSell.isChecked

                viewModel.priceFrom =
                    if (editPriceFrom.text.isEmpty()) 0 else editPriceFrom.text.toString().toInt()

                viewModel.priceTo =
                    if (editPriceTo.text.isEmpty()) 0 else editPriceTo.text.toString().toInt()

                viewModel.location = editCity.text.toString()

                viewModel.categories.clear()
                val res = category.children.toList()
                    .map { c -> c as Chip }
                    .filter { c -> c.isChecked }
                    .map { c -> c.text.toString() }

                viewModel.categories.addAll(res)
                viewModel.filterAds()
                findNavController(view).navigate(
                    R.id.action_navigation_search_filter_to_search_with_filter
                )
            }

            //Заполнение из vm
            category.children.toList()
                .map { c -> c as Chip }
                .forEach { c ->
                    if (viewModel.categories.contains(c.text.toString()))
                        c.isChecked = true
                }

            editCity.setText(viewModel.location)

            if (viewModel.priceFrom == 0) editPriceFrom.setText("")
            else editPriceFrom.setText(viewModel.priceFrom.toString())

            if (viewModel.priceTo == 0) editPriceTo.setText("")
            else editPriceTo.setText(viewModel.priceTo.toString())

            btnBuy.isChecked = viewModel.isBuy
            btnSell.isChecked = viewModel.isSell
        }
    }
}
