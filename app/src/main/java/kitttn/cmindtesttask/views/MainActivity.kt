package kitttn.cmindtesttask.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kitttn.cmindtesttask.R
import kitttn.cmindtesttask.app.App
import kitttn.cmindtesttask.di.components.DaggerFragmentComponent
import kitttn.cmindtesttask.di.modules.FragmentModule
import kitttn.cmindtesttask.views.sources.SourcesFragment

/**
 * @author kitttn
 */

class MainActivity : AppCompatActivity() {
    val component by lazy {
        DaggerFragmentComponent.builder()
                .appComponent(App.graph)
                .fragmentModule(FragmentModule(this))
                .build()
    }

    val fragment = SourcesFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .commit()
    }
}