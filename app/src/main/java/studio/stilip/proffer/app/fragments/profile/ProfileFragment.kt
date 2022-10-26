package studio.stilip.proffer.app.fragments.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import studio.stilip.proffer.R
import studio.stilip.proffer.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentProfileBinding.bind(view)
    }
}