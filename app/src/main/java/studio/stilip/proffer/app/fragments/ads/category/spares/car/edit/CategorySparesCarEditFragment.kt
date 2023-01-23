package studio.stilip.proffer.app.fragments.ads.category.spares.car.edit

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
import studio.stilip.proffer.app.fragments.ads.category.spares.car.PhotosAdapter
import studio.stilip.proffer.databinding.FragmentCategorySparesCarEditBinding
import studio.stilip.proffer.domain.entities.Ad

@AndroidEntryPoint
class CategorySparesCarEditFragment : Fragment(R.layout.fragment_category_spares_car_edit) {

    private val hostViewModel: HostViewModel by activityViewModels()
    private val viewModel: CategorySparesCarEditViewModel by viewModels()

    private val adapter = PhotosAdapter { uri ->
        viewModel.deleteImage(uri)
    }

    val getContent =
        registerForActivityResult(ActivityResultContracts.GetMultipleContents()) { uriList ->
            viewModel.addImages(uriList)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCategorySparesCarEditBinding.bind(view)

        hostViewModel.setBottomBarVisible(false)

        with(binding) {

            recPhotos.adapter = adapter
            var id = 0
            var categories = emptyList<String>()

            viewModel.editAd.subscribe({ ad ->
                editName.setText(ad.name)
                editPrice.setText(ad.price.toString())
                editDescription.setText(ad.description)
                editLocation.setText(ad.address)
                editPhone.setText(ad.phone)
                categories = ad.categories
                id = ad.id
                rSell.isChecked = ad.isSell
                rBuy.isChecked = ad.isSell.not()
            }, {})

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
                val price = editPrice.text.toString().toDouble()
                val phone = editPhone.text.toString()
                val description = editDescription.text.toString()
                val address = editLocation.text.toString()
                val idSeller = hostViewModel.currentUser.value!!.id
                val isSell = rSell.isChecked
                viewModel.saveAd(
                    Ad(
                        id,
                        emptyList(),
                        name,
                        price,
                        phone,
                        description,
                        "",
                        isSell,
                        address,
                        idSeller,
                        categories
                    )
                )
                Toast.makeText(activity, getString(R.string.wait), Toast.LENGTH_SHORT).show()
            }

            btnDelete.setOnClickListener {
                viewModel.deleteAd()
            }

            viewModel.successSaveAd.subscribe { isSave ->
                if (isSave) {
                    Toast.makeText(activity, getString(R.string.ad_saved), Toast.LENGTH_LONG).show()
                    findNavController(view).navigate(
                        R.id.action_navigation_edit_ad_to_ads
                    )
                } else {
                    Toast.makeText(activity, getString(R.string.save_ad_error), Toast.LENGTH_LONG)
                        .show()
                }
            }

            viewModel.successDeleteAd.subscribe { isDelete ->
                if (isDelete) {
                    Toast.makeText(activity, getString(R.string.ad_deleted), Toast.LENGTH_LONG)
                        .show()
                    findNavController(view).navigate(
                        R.id.action_navigation_edit_ad_to_ads
                    )
                } else {
                    Toast.makeText(activity, getString(R.string.delete_ad_error), Toast.LENGTH_LONG)
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