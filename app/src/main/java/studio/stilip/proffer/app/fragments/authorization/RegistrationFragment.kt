package studio.stilip.proffer.app.fragments.authorization

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import studio.stilip.proffer.R
import studio.stilip.proffer.app.HostViewModel
import studio.stilip.proffer.databinding.FragmentRegistrationBinding
import studio.stilip.proffer.domain.entities.User

@AndroidEntryPoint
class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private val hostViewModel: HostViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentRegistrationBinding.bind(view)

        hostViewModel.setBottomBarVisible(false)

        with(binding) {
            btnToSignIn.setOnClickListener {
                Navigation.findNavController(view).navigate(
                    R.id.action_navigation_registration_to_navigation_sign_in
                )
            }

            btnContinue.setOnClickListener {
                //хардкод
                hostViewModel.setCurrentUser(User(1))

                Navigation.findNavController(view).navigate(
                    R.id.action_navigation_registration_to_navigation_search
                )
            }
        }
    }
}