package studio.stilip.proffer.app.fragments.authorization.registration

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
import studio.stilip.proffer.databinding.FragmentRegistrationBinding
import studio.stilip.proffer.domain.entities.User

@AndroidEntryPoint
class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private val hostViewModel: HostViewModel by activityViewModels()
    private val viewModel: RegistrationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentRegistrationBinding.bind(view)

        hostViewModel.setBottomBarVisible(false)

        with(binding) {
            var user = User(0, "", "", "", "", "", "")

            viewModel.successRegistration.subscribe { isRegistered ->
                if (isRegistered) {
                    hostViewModel.setCurrentUser(user)

                    findNavController(view).navigate(
                        R.id.action_navigation_registration_to_navigation_search
                    )
                }
            }

            viewModel.message.subscribe { message ->
                Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
            }

            btnToSignIn.setOnClickListener {
                findNavController(view).navigate(
                    R.id.action_navigation_registration_to_navigation_sign_in
                )
            }

            btnContinue.setOnClickListener {

                if (isEditTextEmpty(editUserLogin)
                    or isEditTextEmpty(editUserName)
                    or isEditTextEmpty(editUserPassword)
                ) {
                    Toast.makeText(activity, getString(R.string.fill_all_field), Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }

                //TODO получение свободного id
                val id = 1
                val login = editUserLogin.text.toString()
                val name = editUserName.text.toString()
                val password = editUserPassword.text.toString()
                user = User(id, login, name, "", "", "", password)

                viewModel.registration(user)
            }
        }
    }

    private fun isEditTextEmpty(editText: EditText) = editText.text.isEmpty()
}