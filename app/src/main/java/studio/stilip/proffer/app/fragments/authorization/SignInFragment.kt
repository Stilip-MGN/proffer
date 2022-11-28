package studio.stilip.proffer.app.fragments.authorization

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import studio.stilip.proffer.R
import studio.stilip.proffer.app.HostViewModel
import studio.stilip.proffer.databinding.FragmentSingInBinding

@AndroidEntryPoint
class SignInFragment : Fragment(R.layout.fragment_sing_in) {

    private val hostViewModel: HostViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSingInBinding.bind(view)

        hostViewModel.setBottomBarVisible(false)

        with(binding) {
            btnToRegistration.setOnClickListener {
                findNavController(view).navigate(
                    R.id.action_navigation_sing_in_to_navigation_registration
                )
            }
        }
    }
}