package kitttn.cmindtesttask.di.modules

import dagger.Module
import dagger.Provides
import kitttn.cmindtesttask.di.annotations.PerActivity
import kitttn.cmindtesttask.router.AppRouter
import kitttn.cmindtesttask.views.MainActivity

/**
 * @author kitttn
 */

@PerActivity @Module
class RouterModule {
    @Provides @PerActivity
    fun provideRouter(activity: MainActivity) = AppRouter(activity)
}