package studio.stilip.proffer.app.fragments.ads

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import studio.stilip.proffer.R
import studio.stilip.proffer.databinding.FragmentAdsBinding

@AndroidEntryPoint
class AdsFragment : Fragment(R.layout.fragment_ads) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentAdsBinding.bind(view)
    }
}