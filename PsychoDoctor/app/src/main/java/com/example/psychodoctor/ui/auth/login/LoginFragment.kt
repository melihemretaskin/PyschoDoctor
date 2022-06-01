package com.example.psychodoctor.ui.auth.login

import android.text.Editable
import android.text.TextWatcher
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.psychodoctor.R
import com.example.psychodoctor.data.local.ClientPreferences
import com.example.psychodoctor.databinding.FragmentLoginBinding
import com.example.psychodoctor.util.base.BaseFragment
import com.example.psychodoctor.util.extension.snack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding,LoginViewModel>(
    FragmentLoginBinding::inflate
) {
    override val viewModel by viewModels<LoginViewModel>()
    private var docId: String = ""
    private var password: String = ""

    override fun onCreateFinished() {

   }

    override fun initListeners() {
        binding.btnLogin.setOnClickListener {
            login()
        }

        binding.etLoginDocId.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                checkFields()
            }

        })

        binding.etLoginPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                checkFields()
            }
        })
    }

    override fun observeEvents() {
        with(viewModel){
            loginResponse?.observe(viewLifecycleOwner, Observer {
                if(it.status == "true"){
                    snack(requireView(),"Login Successful")
                    ClientPreferences(requireContext()).setDocId(docId)
                    ClientPreferences(requireContext()).setRememberMe(true)
                    val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment(it.data!!.get(0))
                    findNavController().navigate(action)

                } else if(it.status == "false"){
                    snack(requireView(),it.message.toString())
                }
            })
            isLoading.observe(viewLifecycleOwner, Observer {
                handleViewAction(it)
            })

            onError.observe(viewLifecycleOwner, Observer {
                snack(requireView(),it.toString())
            })
        }
    }

    private fun handleViewAction(isLoading: Boolean = false) {
        binding.progressBar.isVisible = isLoading
    }

    private fun login() {
        docId = binding.etLoginDocId.text.toString()
        password = binding.etLoginPassword.text.toString()

        viewModel.login(docId, password)
    }

    private fun checkFields() {
        if (!binding.etLoginDocId.text.isNullOrEmpty() && !binding.etLoginPassword.text.isNullOrEmpty()) {
            binding.btnLogin.isEnabled = true
            binding.btnLogin.alpha = 1F
        } else {
            binding.btnLogin.isEnabled = false
            binding.btnLogin.alpha = 0.2F
        }
    }
}