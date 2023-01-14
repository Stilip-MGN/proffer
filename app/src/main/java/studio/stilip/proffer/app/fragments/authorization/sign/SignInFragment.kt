package studio.stilip.proffer.app.fragments.authorization.sign

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import studio.stilip.proffer.R
import studio.stilip.proffer.app.HostViewModel
import studio.stilip.proffer.app.fragments.authorization.registration.RegistrationViewModel
import studio.stilip.proffer.databinding.FragmentSignInBinding
import studio.stilip.proffer.domain.entities.User

@AndroidEntryPoint
class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private val hostViewModel: HostViewModel by activityViewModels()
    private val viewModel: SignInViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSignInBinding.bind(view)

        hostViewModel.setBottomBarVisible(false)

        with(binding) {

            viewModel.user.subscribe { user ->
                hostViewModel.setCurrentUser(user)

                findNavController(view).navigate(
                    R.id.action_navigation_sign_in_to_navigation_search
                )
            }


            viewModel.message.subscribe { message ->
                Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
            }

            btnToRegistration.setOnClickListener {
                findNavController(view).navigate(
                    R.id.action_navigation_sign_in_to_navigation_registration
                )
            }

            btnContinue.setOnClickListener {

                if (isEditTextEmpty(editUserLogin)
                    or isEditTextEmpty(editUserPassword)
                ) {
                    Toast.makeText(activity, getString(R.string.fill_all_field), Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }

                val login = editUserLogin.text.toString()
                val password = editUserPassword.text.toString()

                viewModel.authentication(login, password)
            }
        }
    }

    private fun isEditTextEmpty(editText: EditText) = editText.text.isEmpty()
}