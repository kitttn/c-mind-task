package kitttn.cmindtesttask.router

import kitttn.cmindtesttask.R
import kitttn.cmindtesttask.views.MainActivity
import kitttn.cmindtesttask.views.articles.ArticlesFragment

/**
 * @author kitttn
 */

class AppRouter(private val activity: MainActivity) {
    companion object {
        const val ARTICLES = "articles"
    }

    fun openArticlesPage() {
        activity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.frameLayout, ArticlesFragment())
                .addToBackStack(ARTICLES)
                .commit()
    }
}