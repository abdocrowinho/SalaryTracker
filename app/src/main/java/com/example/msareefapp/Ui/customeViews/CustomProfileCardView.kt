package com.example.msareefapp.Ui.customeViews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.msareefapp.R
import com.google.android.material.materialswitch.MaterialSwitch

class CustomProfileCardView @JvmOverloads constructor (
    context: Context ,
    atrrs : AttributeSet ?=null,
    defStyleArray:Int = 0
):ConstraintLayout(context,atrrs,defStyleArray) {
    private val tittle : TextView
    private var icon : ImageView?=null
    private var switch : MaterialSwitch?=null
    private var isIcon : Boolean = false
    private var isSwitch : Boolean=false

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_card_view,this,true)
         tittle = findViewById(R.id.tittle_CustomCard)
        icon = findViewById(R.id.icon_customCard)
        switch = findViewById(R.id.customSwitch)
        atrrs?.let {
            val typedArray = context.obtainStyledAttributes(it,R.styleable.CustomProfileCardView)
            val tittle = typedArray.getString(R.styleable.CustomProfileCardView_cardTittle)
            val isIcon =typedArray.getBoolean(R.styleable.CustomProfileCardView_isIcon,false)
            val isSwitch=typedArray.getBoolean(R.styleable.CustomProfileCardView_isSwitch,false)
            toggleVisibility(isIcon,isSwitch)


            tittle?.let {
                setTittle(it)
            }

             if (isIcon){
                 val iconImage = typedArray.getResourceId(R.styleable.CustomProfileCardView_cardIcon,-1)
                 if (iconImage!= -1) setImage(iconImage)
             }else{
                 icon = null
             }

if (isSwitch){

    var switch = typedArray.getBoolean(R.styleable.CustomProfileCardView_switch1,false)
    setSwitch(switch)
}else{switch = null}
        }

    }
     fun setIsNightMode(isNightMode:Boolean){
         this.switch?.isChecked = isNightMode
     }

    private fun setSwitch(switch: Boolean) {
        this.switch?.isChecked = switch

    }

    private fun setImage(it: Int) {
icon?.setImageResource(it)
    }

    private fun setTittle(it: String) {
        tittle.text = it

    }
    fun setOnSwitchCheckedChangeListener(listener: (Boolean) -> Unit) {
        switch?.setOnCheckedChangeListener { _, isChecked ->
            listener(isChecked)
        }
    }

    private fun toggleVisibility(showIcon: Boolean, showSwitch: Boolean) {
        icon?.visibility = if (showIcon) VISIBLE else GONE
        switch?.visibility = if (showSwitch) VISIBLE else GONE
    }
}