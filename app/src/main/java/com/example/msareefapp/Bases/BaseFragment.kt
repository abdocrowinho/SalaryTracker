package com.example.msareefapp.Bases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<vb : ViewBinding,
        VM : BaseViewModel> : Fragment() {

    private var _binding: vb? = null
    val binding get() = _binding
    lateinit var viewModel: VM
    abstract fun initViewModel(): VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = initViewModel()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = inflateBinding(inflater, container)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeUiMessage()
    }

    private fun observeUiMessage() {
        viewModel.uiMessageLiveData.observe(viewLifecycleOwner) { uiMessage ->
            uiMessage?.let {
                if (alertDialog==null) {
                    showDialog(it)
                }
            }
        }

    }



    abstract fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): vb
    var alertDialog: AlertDialog? = null
    fun showDialog(uiMessage: UiMessage) {
        val builder = AlertDialog.Builder(requireContext())
        uiMessage.isCancelable.let { builder.setCancelable(it ?: true) }
        uiMessage.message?.let { builder.setMessage(it) }
        uiMessage.messageId?.let { builder.setMessage(it) }
        uiMessage.posText?.let { posText ->
            builder.setPositiveButton(posText) { _, _ ->
                uiMessage.posActionButton?.onCLickDialogListener()
                hideDialog()
            }
        }
        uiMessage.posTextId?.let { textId ->
            builder.setPositiveButton(textId) { _, _ ->
                uiMessage.posActionButton?.onCLickDialogListener()
                hideDialog()
            }
        }
        uiMessage.negText?.let { negText ->
            builder.setNegativeButton(negText) { _, _ ->
                uiMessage.negActionButton?.onCLickDialogListener()

            }
        }
        uiMessage.negTextId?.let { negTextId ->
            builder.setNegativeButton(negTextId) { _, _ ->
                uiMessage.negActionButton?.onCLickDialogListener()

            }
        }
        alertDialog = builder.create()
        alertDialog!!.show()

    }

    fun hideDialog() {
        alertDialog?.let { if (it.isShowing) it.dismiss()  }
        alertDialog = null

    }

    override fun onDestroyView() {
        super.onDestroyView()
        hideDialog()
        _binding = null

    }
}
