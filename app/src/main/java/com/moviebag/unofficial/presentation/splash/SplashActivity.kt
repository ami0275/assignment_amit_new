package com.moviebag.unofficial.presentation.splash

import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.moviebag.unofficial.R
import com.moviebag.unofficial.presentation.home.HomeActivity


class SplashActivity : AppCompatActivity() {
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)
        handler.postDelayed({
            HomeActivity.start(this)
            supportFinishAfterTransition()
        }, 100)
    }
}