package com.example.msareefapp.Bases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.viewbinding.ViewBinding
import com.example.msareefapp.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheet<Vb:ViewBinding , Vm:BaseViewModel> : BottomSheetDialogFragment() {
    private var _binding :Vb ?= null
    val binding  get() = _binding
    
   private lateinit var _viewModel:Vm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewModel = initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeUiMessage()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflateBinding(inflater,container)
        return _binding?.root
    }
  private  var isDialogShowIn = false

    fun observeUiMessage(){
        _viewModel.uiMessageLiveData.observe(viewLifecycleOwner){ uiMessage ->
            uiMessage?.let {
                if (isDialogShowIn){
                    showDialog(it)
                }
                }
        }
    }

    var alertDialog : AlertDialog?=null
    fun showDialog(uiMessage: UiMessage) {
        val builder = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialogTheme)
         builder.setCancelable(uiMessage.isCancelable?:true)
        val messageToShow = uiMessage.message ?: uiMessage.messageId?.let { requireContext().getString(it) }
        messageToShow?.let { builder.setMessage(it) }

        uiMessage.posText?.let { posText ->
            builder.setPositiveButton(posText) { _, _ ->
                uiMessage.posActionButton?.onCLickDialogListener()
            }
        }
        uiMessage.posTextId?.let { textId ->
            builder.setPositiveButton(textId) { _, _ ->
                uiMessage.posActionButton?.onCLickDialogListener()
            }
        }
        uiMessage.negText?.let { negText ->
            builder.setNegativeButton(negText) { _, _ ->

                uiMessage.negActionButton?.onCLickDialogListener()
                hideDialog()

            }
        }
        uiMessage.negTextId?.let { negTextId ->
            builder.setNegativeButton(negTextId) { _, _ ->
                uiMessage.negActionButton?.onCLickDialogListener()
                hideDialog()

            }
        }
        isDialogShowIn =true
        alertDialog = builder.create()
        builder.show()
    }
fun hideDialog(){
    alertDialog?.dismiss()
    alertDialog = null
    isDialogShowIn = false
}

    override fun onDestroyView() {
        super.onDestroyView()
      hideDialog()
    }
    abstract fun initViewModel(): Vm
    abstract fun inflateBinding(inflater : LayoutInflater,container:ViewGroup?):Vb
}