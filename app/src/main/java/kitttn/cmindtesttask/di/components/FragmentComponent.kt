package kitttn.cmindtesttask.di.components

import dagger.Component
import kitttn.cmindtesttask.di.annotations.PerActivity
import kitttn.cmindtesttask.di.modules.FragmentModule
import kitttn.cmindtesttask.views.sources.SourcesFragment

/**
 * @author kitttn
 */

@PerActivity @Component(modules = arrayOf(FragmentModule::class), dependencies = arrayOf(AppComponent::class))
interface FragmentComponent {
    fun inject(fragment: SourcesFragment)
}