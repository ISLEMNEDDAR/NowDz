package com.example.nowdz.helper


import android.app.ProgressDialog
import android.content.Context
import com.example.nowdz.R
import com.example.nowdz.ui.Adapter.TitlePagerAdapter

class DialogHelper {

    companion object{
        fun showProgressBar(context : Context,title : String,cancle : Boolean): ProgressDialog{
            var progress = ProgressDialog(context, R.style.ThemeOverlay_MaterialComponents_Dialog)
            progress.setCancelable(cancle)
            progress.setTitle(title)
            progress.show()
            return progress
        }

        fun hideProgress(progress : ProgressDialog){
            progress.dismiss()
        }

    }
}