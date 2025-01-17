package com.example.msareefapp.Ui.Fragments.DaysFragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.msareefapp.Bases.BaseFragment
import com.example.msareefapp.R
import com.example.msareefapp.Utiltes.Constants
import com.example.msareefapp.databinding.FragmentDaysBinding
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import dagger.hilt.android.AndroidEntryPoint
import java.util.prefs.Preferences


@AndroidEntryPoint
class DaysFragment : BaseFragment<FragmentDaysBinding, DaysViewModel>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        _viewModel.getSpendingDay()
    }

    private fun initViews() {
        initBarChart()
    }

    private fun initBarChart() {
        _viewModel.spendingDaysLiveData.observe(viewLifecycleOwner) { daySpendingList ->
            val barEntries = ArrayList<BarEntry>()
            val dayLabels = arrayListOf(
                "Saturday",
                "Sunday",
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday"
            )

            daySpendingList?.forEach { daySpending ->
                val index = (daySpending?.time?.toInt()?:0)
                if (index in 0..6) {
                    barEntries.add(
                        BarEntry(
                            index.toFloat(),
                            daySpending?.totalSpent?.toFloat() ?: 0F
                        )
                    )
                }

                val barDataSet = BarDataSet(barEntries, "Spending per Day")

                val pref = requireContext().getSharedPreferences(
                    Constants.SHARED_PREF,
                    Context.MODE_PRIVATE
                )
                val isNightModeOn = pref.getBoolean(Constants.IS_NIGH_MODE, false)
                val color = when (isNightModeOn) {
                    true -> Color.parseColor("#9A9EF7")
                    false -> Color.parseColor("#909A9EF7")
                }
                barDataSet.color = color
                barDataSet.valueTextColor = color
                barDataSet.valueTextSize = 12f
                val barData = BarData(barDataSet)
                binding?.barChart?.data = barData

                val xAxis = binding?.barChart?.xAxis
                val yAxis = binding?.barChart?.axisLeft
                yAxis?.textColor = color

                xAxis?.let {
                    it.apply {
                        valueFormatter = IndexAxisValueFormatter(dayLabels)
                       position = XAxis.XAxisPosition.BOTTOM
                       granularity = 1f
                       textColor=color
                        labelCount=dayLabels.size
                       setAvoidFirstLastClipping(true)
                        valueFormatter =
                            object : com.github.mikephil.charting.formatter.ValueFormatter() {
                                override fun getFormattedValue(value: Float): String {
                                    return dayLabels[value.toInt()]
                                }
                            }
                    }
                }

                binding?.let {
                    it.apply {
                        barChart.moveViewToX(0f)
                        barChart.setVisibleXRangeMaximum(dayLabels.size.toFloat())
                        barChart.axisLeft.setDrawGridLines(false)
                        barChart.axisRight.isEnabled = false
                        barChart.description.isEnabled = false
                        barChart.animateY(1000)
                        barChart.invalidate()
                    }
                }


            }


        }
    }


    private val _viewModel: DaysViewModel by viewModels()
    override fun initViewModel(): DaysViewModel {
        return _viewModel
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDaysBinding {
        return FragmentDaysBinding.inflate(inflater, container, false)
    }
}