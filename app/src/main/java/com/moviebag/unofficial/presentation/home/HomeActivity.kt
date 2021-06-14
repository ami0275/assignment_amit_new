package com.moviebag.unofficial.presentation.home

import android.app.Activity
import android.content.Intent
import com.moviebag.unofficial.base.view.BaseActivity
import com.moviebag.unofficial.R
import com.moviebag.unofficial.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>() {
    override val layoutResourceId: Int = R.layout.activity_home
    override val classTypeOfViewModel: Class<HomeViewModel> = HomeViewModel::class.java

    companion object {
        fun start(activity: Activity) {
            activity.startActivity(Intent(activity, HomeActivity::class.java))
        }
    }
}