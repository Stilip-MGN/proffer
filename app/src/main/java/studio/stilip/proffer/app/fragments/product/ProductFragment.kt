package studio.stilip.proffer.app.fragments.product

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import studio.stilip.proffer.R
import studio.stilip.proffer.app.HostViewModel
import studio.stilip.proffer.app.fragments.search.AdListAdapter
import studio.stilip.proffer.databinding.FragmentProductBinding

@AndroidEntryPoint
class ProductFragment : Fragment(R.layout.fragment_product) {

    private val hostViewModel: HostViewModel by activityViewModels()
    private val viewModel: ProductViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentProductBinding.bind(view)

        hostViewModel.setBottomBarVisible(false)

        val adAdapter = AdListAdapter({ id ->
            val args = Bundle().apply {
                putInt(ID_AD, id)
            }
            Navigation.findNavController(view).navigate(
                R.id.action_navigation_product_self,
                args
            )
        }, { id ->
            viewModel.changeFavoriteStatusById(id)
        })

        viewModel.ad.subscribe { ad ->
            with(binding) {
                productName.text = ad.name
                productPrice.text = ad.price.toString()
                textDescription.text = ad.description
                textCharacteristics.text = ad.characteristics
                textAddress.text = ad.address

                if (ad.isFavorite) {
                    btnFavorite.setImageResource(R.drawable.ic_favorite_24)
                    btnFavorite.setColorFilter(
                        ContextCompat.getColor(
                            btnFavorite.context,
                            R.color.red
                        )
                    )
                } else {
                    btnFavorite.setImageResource(R.drawable.ic_favorite_empty_24)
                    btnFavorite.setColorFilter(
                        ContextCompat.getColor(
                            btnFavorite.context,
                            R.color.black
                        )
                    )
                }

                var iterator = 0

                fun updateImageSw() {
                    Glide.with(this@ProductFragment)
                        .load(ad.photos[iterator])
                        .centerCrop()
                        .error(R.drawable.ic_do_not_disturb_24)
                        .into(imgSw)
                }

                btnImageLeft.setOnClickListener {
                    if (iterator > 0) {
                        iterator--
                        updateImageSw()
                    }
                }

                btnImageRight.setOnClickListener {
                    if (iterator < ad.photos.size - 1) {
                        iterator++
                        updateImageSw()
                    }
                }
                if (ad.photos.isNotEmpty())
                    updateImageSw()
            }
        }

        viewModel.seller.subscribe { seller ->
            with(binding) {
                sellerName.text = seller.name
                sellerRating.text = seller.rating.toString()
                sellerCountReviews.text = when (seller.countReviews % 10) {
                    0 -> "0 ${getString(R.string.reviews)}"
                    1 -> "1 ${getString(R.string.reviews_1)}"
                    in 2..4 -> "${seller.countReviews} ${getString(R.string.reviews_2_4)}"
                    else -> "${seller.countReviews} ${getString(R.string.reviews)}"
                }

                Glide.with(this@ProductFragment)
                    .load(seller.photo)
                    .centerCrop()
                    .error(R.drawable.ic_person_24)
                    .into(sellerPhoto)
            }
        }

        viewModel.similarAds.subscribe { list ->
            adAdapter.submitList(list)
        }

        with(binding) {
            btnBack.setOnClickListener {
                requireActivity().onBackPressed()
            }

            btnFavorite.setOnClickListener {
                viewModel.onFavoriteClick()
            }

            recSimilar.adapter = adAdapter
        }
    }

    override fun onResume() {
        viewModel.loadSimilar()
        super.onResume()
    }

    companion object {
        const val ID_AD = "id_ad"
    }
}