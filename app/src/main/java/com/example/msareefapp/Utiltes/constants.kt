package com.example.msareefapp.Utiltes

import com.example.msareefapp.R
import com.example.msareefapp.Ui.Activites.onBorading.ItemBoarding

object Constants {
   val boardingList : List<ItemBoarding> =
       listOf(
           ItemBoarding(R.raw.first_animation,"أهلاً وسهلاً في تطبيق مراقب المرتب! هنا هنساعدك تحكم في مصروفاتك وتوفر من راتبك بسهولة")
       ,
           ItemBoarding(R.raw.secound_animation,"اكتب راتبك الشهري في التطبيق، وخلينا نساعدك تتابع مصروفاتك بدقة! تقدر تعدل الراتب في أي وقت، لو حصل أي تغيير.")
       ,
           ItemBoarding(R.raw.animation,"استلم تنبيهات ذكية لو مصروفاتك بدأت تتجاوز 50% من راتبك. كمان هنوفر لك إشعارات تحفيزية لمساعدتك على الادخار وتجنب الإنفاق المفرط!")
       )
    const val APP_DATA_BASE_NAME = "SalaryTracker-Database"

}