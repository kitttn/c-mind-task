package kitttn.cmindtesttask.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kitttn.cmindtesttask.R
import kitttn.cmindtesttask.app.App
import kitttn.cmindtesttask.di.components.DaggerFragmentComponent
import kitttn.cmindtesttask.di.modules.FragmentModule
import kitttn.cmindtesttask.router.AppRouter
import javax.inject.Inject

/**
 * @author kitttn
 */

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    @Inject lateinit var router: AppRouter

    val component by lazy {
        DaggerFragmentComponent.builder()
                .appComponent(App.graph)
                .fragmentModule(FragmentModule(this))
                .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        component.inject(this)
    }

    override fun onStart() {
        super.onStart()
        router.start()
    }

    override fun onStop() {
        super.onStop()
        router.stop()
    }

    override fun onBackPressed() {
        Log.i(TAG, "onBackPressed: backstack entry count: ${supportFragmentManager.backStackEntryCount}")
        super.onBackPressed()
    }
}