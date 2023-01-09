package studio.stilip.proffer.app.fragments.ads

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import studio.stilip.proffer.R
import studio.stilip.proffer.databinding.FragmentAdsBinding
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import studio.stilip.proffer.app.HostViewModel
import studio.stilip.proffer.app.fragments.ads.completed.AdsCompletedFragment

@AndroidEntryPoint
class AdsFragment : Fragment(R.layout.fragment_ads) {

    private val hostViewModel: HostViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentAdsBinding.bind(view)

        hostViewModel.setBottomBarVisible(true)

        val navHostFragment = this@AdsFragment.childFragmentManager
            .findFragmentById(R.id.nav_host_fragment_ads) as NavHostFragment

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

            btnActive.setOnClickListener {
                if (isTextColorTextViewTheSame(btnActive, R.color.grey)) {
                    changeSelectedTextView(btnActive, btnCompleted)
                    navHostFragment.navController.navigate(R.id.navigation_ads_active)
                }
            }

            btnCompleted.setOnClickListener {
                if (isTextColorTextViewTheSame(btnCompleted, R.color.grey)) {
                    changeSelectedTextView(btnCompleted, btnActive)
                    navHostFragment.navController.navigate(R.id.navigation_ads_completed)
                }
            }

            when (navHostFragment.childFragmentManager.fragments[0]) {
                is AdsCompletedFragment -> changeSelectedTextView(btnCompleted, btnActive)
                else -> changeSelectedTextView(btnActive, btnCompleted)
            }
        }

    }
}