package studio.stilip.proffer.app.fragments.profile.edit

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.Disposable
import studio.stilip.proffer.R
import studio.stilip.proffer.app.HostViewModel
import studio.stilip.proffer.databinding.FragmentEditProfileBinding
import studio.stilip.proffer.domain.entities.User

@AndroidEntryPoint
class EditProfileFragment : Fragment(R.layout.fragment_edit_profile) {

    private val hostViewModel: HostViewModel by activityViewModels()
    private lateinit var dataChangedDisposable: Disposable

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentEditProfileBinding.bind(view)

        hostViewModel.setBottomBarVisible(false)

        with(binding) {

            val user = hostViewModel.currentUser.value!!
            editUserName.setText(user.name)
            editUserCity.setText(user.city)
            editUserPhone.setText(user.phone)
            editUserMail.setText(user.mail)
            editUserPassword.setText(user.password)

            btnBack.setOnClickListener {
                requireActivity().onBackPressed()
            }

            dataChangedDisposable = hostViewModel.isDataChanged.subscribe { isChanged ->
                if (isChanged) {
                    Toast.makeText(activity, getString(R.string.saved), Toast.LENGTH_SHORT)
                        .show()
                    Navigation.findNavController(view).navigate(
                        R.id.action_navigation_edit_profile_to_profile
                    )
                } else {
                    Toast.makeText(activity, getString(R.string.could_not_save), Toast.LENGTH_SHORT)
                        .show()
                }
            }

            btnSave.setOnClickListener {
                val id = user.id
                val name = editUserName.text.toString()
                val city = editUserCity.text.toString()
                val phone = editUserPhone.text.toString()
                val mail = editUserMail.text.toString()
                val password = editUserPassword.text.toString()
                hostViewModel.changeData(User(id, name, city, phone, mail, password))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dataChangedDisposable.dispose()
    }
}