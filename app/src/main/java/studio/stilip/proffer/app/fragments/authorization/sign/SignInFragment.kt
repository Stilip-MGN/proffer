package studio.stilip.proffer.app.fragments.authorization.sign

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import studio.stilip.proffer.R
import studio.stilip.proffer.app.HostViewModel
import studio.stilip.proffer.databinding.FragmentSignInBinding
import studio.stilip.proffer.domain.entities.User

@AndroidEntryPoint
class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private val hostViewModel: HostViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSignInBinding.bind(view)

        hostViewModel.setBottomBarVisible(false)

        with(binding) {
            btnToRegistration.setOnClickListener {
                findNavController(view).navigate(
                    R.id.action_navigation_sign_in_to_navigation_registration
                )
            }

            btnContinue.setOnClickListener {
                //хардкод
                hostViewModel.setCurrentUser(User(1, "", "", "", "", "", ""))

                findNavController(view).navigate(
                    R.id.action_navigation_sign_in_to_navigation_search
                )
            }
        }
    }
}