package com.hdk24.basemvvm.ui.onboard

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.hdk24.basemvvm.R
import com.hdk24.basemvvm.databinding.ActivitySplashBinding
import com.hdk24.basemvvm.presentation.base.BaseActivity
import com.hdk24.basemvvm.ui.MainActivity
import com.hdk24.basemvvm.utils.DisplayUtils

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    private val mHandler = Handler()

    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun onViewReady(savedInstance: Bundle?) {
        DisplayUtils.setTransparentStatusBar(window)
        mHandler.postDelayed(runnable,3000)
    }

    private val runnable = Runnable {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onBackPressed() {
        // prevent back press
    }

    override fun onDestroy() {
        super.onDestroy()
        mHandler.removeCallbacksAndMessages(runnable)
    }
}
