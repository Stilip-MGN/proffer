package studio.stilip.proffer.app.fragments.ads

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import studio.stilip.proffer.R
import studio.stilip.proffer.databinding.FragmentAdsBinding
import androidx.navigation.Navigation.findNavController
import studio.stilip.proffer.app.HostViewModel

@AndroidEntryPoint
class AdsFragment : Fragment(R.layout.fragment_ads) {

    private val hostViewModel: HostViewModel by activityViewModels()
    private val viewModel: AdsViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        viewModel.getCurrentUserAds(hostViewModel.currentUser.value!!.id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentAdsBinding.bind(view)

        val adapter = MyAdsAdapter()

        hostViewModel.setBottomBarVisible(true)

        fun changeSelectedTextView(newFocusView: TextView, oldFocusView: TextView) {
            newFocusView.setTextColor(ContextCompat.getColor(newFocusView.context, R.color.black))
            oldFocusView.setTextColor(ContextCompat.getColor(oldFocusView.context, R.color.grey))
        }

        fun isTextColorTextViewTheSame(textView: TextView, idColor: Int): Boolean {
            return textView.currentTextColor == ContextCompat.getColor(textView.context, idColor)
        }

        with(binding) {
            btnAddNewAd.setOnClickListener {
                findNavController(view).navigate(
                    R.id.action_navigation_ads_to_navigation_category_selection
                )
            }

            recMyAds.adapter = adapter

            btnActive.setOnClickListener {
                if (isTextColorTextViewTheSame(btnActive, R.color.grey)) {
                    changeSelectedTextView(btnActive, btnCompleted)
                    viewModel.changeSelectAds(true)
                }
            }

            btnCompleted.setOnClickListener {
                if (isTextColorTextViewTheSame(btnCompleted, R.color.grey)) {
                    changeSelectedTextView(btnCompleted, btnActive)
                    viewModel.changeSelectAds(false)
                }
            }
        }

        viewModel.ads.subscribe { list ->
            adapter.submitList(list)
        }
    }
}