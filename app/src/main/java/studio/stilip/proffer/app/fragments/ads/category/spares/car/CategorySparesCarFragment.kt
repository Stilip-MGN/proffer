package studio.stilip.proffer.app.fragments.ads.category.spares.car

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import studio.stilip.proffer.R
import studio.stilip.proffer.app.HostViewModel
import studio.stilip.proffer.databinding.FragmentCategorySparesCarBinding
import studio.stilip.proffer.domain.entities.Ad

@AndroidEntryPoint
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

                if (isEditTextEmpty(editName)
                    or isEditTextEmpty(editPrice)
                    or isEditTextEmpty(editDescription)
                    or isEditTextEmpty(editLocation)
                    or isEditTextEmpty(editPhone)
                ) {
                    Toast.makeText(activity, getString(R.string.fill_all_field), Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }

                val name = editName.text.toString()
                val price = editPrice.text.toString().toInt()
                val description = editDescription.text.toString()
                val address = editLocation.text.toString()
                val idSeller = hostViewModel.currentUser.value!!.id
                viewModel.saveAd(Ad(12, "", name, price, description, "", address, idSeller))
                Toast.makeText(activity, getString(R.string.wait), Toast.LENGTH_SHORT).show()
            }

            viewModel.successSaveAd.subscribe { isSave ->
                if (isSave) {
                    Toast.makeText(activity, getString(R.string.ad_saved), Toast.LENGTH_LONG).show()
                    findNavController(view).navigate(
                        R.id.action_navigation_category_spares_car_to_navigation_ads
                    )
                } else {
                    Toast.makeText(activity, getString(R.string.save_ad_error), Toast.LENGTH_LONG)
                        .show()
                }
            }
        }

        viewModel.imagesUri.subscribe { list ->
            adapter.submitList(list)
        }
    }

    private fun isEditTextEmpty(editText: EditText) = editText.text.isEmpty()
}