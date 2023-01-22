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

@AndroidEntryPoint
class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private val hostViewModel: HostViewModel by activityViewModels()
    private val viewModel: RegistrationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentRegistrationBinding.bind(view)

        hostViewModel.setBottomBarVisible(false)

        with(binding) {

            viewModel.user.subscribe { user ->
                hostViewModel.setCurrentUser(user)

                findNavController(view).navigate(
                    R.id.action_navigation_registration_to_navigation_search
                )
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
                    or isEditTextEmpty(editUserMail)
                    or isEditTextEmpty(editUserPassword)
                ) {
                    Toast.makeText(activity, getString(R.string.fill_all_field), Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }

                val login = editUserLogin.text.toString()
                val email = editUserMail.text.toString()
                val password = editUserPassword.text.toString()

                if (!isPasswordValid(password)) {
                    Toast.makeText(
                        activity,
                        getString(R.string.error_password_is_not_valid),
                        Toast.LENGTH_LONG
                    )
                        .show()
                    editUserPassword.setText("")
                    return@setOnClickListener
                }

                viewModel.registration(login, password, email)
            }
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        if (password.length < 8) return false
        var upperFlag = false
        var lowerFlag = false
        var numberFlag = false
        password.forEach { ch ->
            ch.isUpperCase()
            if (ch.isDigit())
                numberFlag = true
            else if (ch.isUpperCase())
                upperFlag = true
            else if (ch.isLowerCase())
                lowerFlag = true

            if (numberFlag && upperFlag && lowerFlag) return true
        }

        return false
    }

    private fun isEditTextEmpty(editText: EditText) = editText.text.isEmpty()
}