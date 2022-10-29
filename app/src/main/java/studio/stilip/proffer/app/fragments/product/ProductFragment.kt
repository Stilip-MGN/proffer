package studio.stilip.proffer.app.fragments.product

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import studio.stilip.proffer.R
import studio.stilip.proffer.databinding.FragmentProductBinding

@AndroidEntryPoint
class ProductFragment : Fragment(R.layout.fragment_product) {
    // stateHandle: SavedStateHandle
    private val viewModel: ProductViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentProductBinding.bind(view)

        viewModel.ad.subscribe { ad ->
            with(binding){
                productName.text = ad.name
                productPrice.text = ad.price.toString()
            }
        }
    }

    companion object {
        const val ID_AD = "id_ad"
    }
}