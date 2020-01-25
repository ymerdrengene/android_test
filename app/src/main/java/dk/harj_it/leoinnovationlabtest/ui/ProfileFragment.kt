package dk.harj_it.leoinnovationlabtest.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dk.harj_it.leoinnovationlabtest.MainViewModel
import dk.harj_it.leoinnovationlabtest.R
import dk.harj_it.leoinnovationlabtest.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

        binding.apply {
            lifecycleOwner = this@ProfileFragment
            vm = viewModel

            profileGithubProfileButton.setOnClickListener {
                viewModel.user.value?.htmlUrl?.let { url ->
                    startActivity(Intent(Intent.ACTION_VIEW).setData(Uri.parse(url)))
                }
            }

            profileEmailButton.setOnClickListener {
                viewModel.user.value?.email?.let {
                    Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$it")).let {
                        it.putExtra(Intent.EXTRA_SUBJECT, "")
                        it.putExtra(Intent.EXTRA_TEXT, "")
                        startActivity(
                            Intent.createChooser(
                                it,
                                getString(R.string.profile_choose_email)
                            )
                        )
                    }
                }
            }

            return root
        }
    }
}