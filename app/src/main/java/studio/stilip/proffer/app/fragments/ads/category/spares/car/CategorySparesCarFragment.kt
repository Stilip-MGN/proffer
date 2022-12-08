package studio.stilip.proffer.app.fragments.ads.category.spares.car

import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import studio.stilip.proffer.R
import studio.stilip.proffer.app.HostViewModel
import studio.stilip.proffer.databinding.FragmentCategorySparesCarBinding


class CategorySparesCarFragment : Fragment(R.layout.fragment_category_spares_car) {

    private val hostViewModel: HostViewModel by activityViewModels()
    private val viewModel: CategorySparesCarViewModel by viewModels()

    private val adapter = PhotosAdapter { uri ->
        viewModel.deleteImage(uri)
    }

    val getContent =
        registerForActivityResult(ActivityResultContracts.GetMultipleContents()) { uriList ->
            viewModel.addImages(uriList)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCategorySparesCarBinding.bind(view)

        hostViewModel.setBottomBarVisible(false)

        with(binding) {

            recPhotos.adapter = adapter

            btnAddPhoto.setOnClickListener {
                getContent.launch("image/*")
            }

            btnBack.setOnClickListener {
                requireActivity().onBackPressed()
            }

            btnPlaceAd.setOnClickListener {
                findNavController(view).navigate(
                    R.id.action_navigation_category_spares_car_to_navigation_ads
                )
            }
        }

        viewModel.imagesUri.subscribe { list ->
            adapter.submitList(list)
        }
    }
}