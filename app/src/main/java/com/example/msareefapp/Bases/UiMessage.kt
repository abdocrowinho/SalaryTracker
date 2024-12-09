package com.example.msareefapp.Bases

fun interface OnClickDialogButtons {
    fun onCLickDialogListener()
}

class UiMessage(
    builder: Builder
) {
    val message: String? = builder.message
    val messageId: Int? = builder.messageId

    val posText: String? = builder.posText
    val posTextId: Int? = builder.posTextId
    val posActionButton: OnClickDialogButtons? = builder.posActionButton

    val negText: String? = builder.negText
    val negTextId: Int? = builder.negTextId
    val negActionButton: OnClickDialogButtons? = builder.negActionButton

    val isCancelable: Boolean? = builder.isCancelable


    class Builder {


        var message: String? = null
        var messageId: Int? = null

        var posText: String? = null
        var posTextId: Int? = null
        var posActionButton: OnClickDialogButtons? = null

        var negText: String? = null
        var negTextId: Int? = null
        var negActionButton: OnClickDialogButtons? = null

        var isCancelable: Boolean? = true
        fun setMessage(message: String) = apply { this.message = message }
        fun setMessageId(messageId: Int) = apply { this.messageId = messageId }
        fun setPosTextId(posTextId: Int) = apply { this.posTextId = posTextId }
        fun setPosText(posText: String) = apply { this.posText = posText }
        fun setNegTextId(negTextId: Int) = apply { this.negTextId = negTextId }
        fun setNegText(negText: String) = apply { this.negText = negText }
        fun setIsCancelable(isCancelable: Boolean) = apply { this.isCancelable = isCancelable }
        fun setPosClickListener(onClick: OnClickDialogButtons) = apply { this.posActionButton = onClick }
        fun setNegClickListener(onClick: OnClickDialogButtons) = apply { this.negActionButton = onClick }

        fun build(): UiMessage {
            return UiMessage(this)
        }
    }
}
