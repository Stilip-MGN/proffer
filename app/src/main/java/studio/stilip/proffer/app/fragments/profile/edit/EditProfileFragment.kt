package studio.stilip.proffer.app.fragments.profile.edit

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.Disposable
import studio.stilip.proffer.R
import studio.stilip.proffer.app.HostViewModel
import studio.stilip.proffer.app.fragments.profile.ProfileViewModel
import studio.stilip.proffer.databinding.FragmentEditProfileBinding
import studio.stilip.proffer.domain.entities.User

@AndroidEntryPoint
class EditProfileFragment : Fragment(R.layout.fragment_edit_profile) {

    private val hostViewModel: HostViewModel by activityViewModels()
    private val viewModel: ProfileViewModel by viewModels()
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

            //TODO убрать хардкод
            viewModel.profile.subscribe({ user ->
                editUserName.setText(user.name)
                editUserCity.setText("Екатеринбург")
                editUserPhone.setText("895362838362")
                editUserMail.setText(user.mail)
                editUserPassword.setText("12345678Abc")

                Glide.with(this@EditProfileFragment)
                    .load(user.photo)
                    .centerCrop()
                    .error(R.drawable.ic_person_24)
                    .into(photo)
            }, {
                println(it.message)
            })

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
                val user = hostViewModel.currentUser.value!!
                val id = user.id
                val login = user.login
                val name = editUserName.text.toString()
                val city = editUserCity.text.toString()
                val phone = editUserPhone.text.toString()
                val mail = editUserMail.text.toString()
                val password = editUserPassword.text.toString()
                hostViewModel.changeData(User(id, login, name, city, phone, mail, password))
            }

            btnExit.setOnClickListener {
                hostViewModel.deleteCurrentUser()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dataChangedDisposable.dispose()
    }
}