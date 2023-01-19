package studio.stilip.proffer.app.fragments.profile.edit

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation.findNavController
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.Disposable
import studio.stilip.proffer.R
import studio.stilip.proffer.app.HostViewModel
import studio.stilip.proffer.databinding.FragmentEditProfileBinding
import studio.stilip.proffer.domain.entities.User

@AndroidEntryPoint
class EditProfileFragment : Fragment(R.layout.fragment_edit_profile) {

    private val hostViewModel: HostViewModel by activityViewModels()
    private lateinit var binding: FragmentEditProfileBinding
    private lateinit var dataChangedDisposable: Disposable

    val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            Glide.with(binding.photo).load(uri).into(binding.photo)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEditProfileBinding.bind(view)

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
                    findNavController(view).navigate(
                        R.id.action_navigation_edit_profile_to_profile
                    )
                } else {
                    Toast.makeText(activity, getString(R.string.could_not_save), Toast.LENGTH_SHORT)
                        .show()
                }
            }

            photo.setOnClickListener {
                getContent.launch("image/*")
            }

            btnSave.setOnClickListener {
                val id = user.id
                val login = hostViewModel.currentUser.value!!.login
                val name = editUserName.text.toString()
                val city = editUserCity.text.toString()
                val phone = editUserPhone.text.toString()
                val mail = editUserMail.text.toString()
                val password = editUserPassword.text.toString()
                hostViewModel.changeData(User(id, login, name, city, phone, mail, password))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dataChangedDisposable.dispose()
    }
}