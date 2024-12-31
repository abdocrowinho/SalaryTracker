package com.example.msareefapp.Bases

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<vb : ViewBinding, Vm : BaseViewModel> : AppCompatActivity() {
    private var _binding: vb? = null
    val binding: vb get() = _binding!!

    lateinit var viewModel: Vm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = inflateBinding()
        setContentView(binding.root)
        viewModel = initViewModel()
        observeUiMessage()

    }
    private fun observeUiMessage(){
        viewModel.uiMessageLiveData.observe(this){ uiMessage ->
            uiMessage?.let { showDialog(it) }
        }
    }

    fun showDialog(uiMessage: UiMessage) {
        val builder = AlertDialog.Builder(this)
        uiMessage.isCancelable.let { builder.setCancelable(it?:true) }
        uiMessage.message?.let { builder.setMessage(it) }
        uiMessage.messageId?.let { builder.setMessage(it) }
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

            }
        }
        uiMessage.negTextId?.let { negTextId ->
            builder.setNegativeButton(negTextId) { _, _ ->
                uiMessage.negActionButton?.onCLickDialogListener()

            }
        }
        builder.show()

    }

    abstract fun initViewModel():Vm

    abstract fun inflateBinding(): vb

}