package kitttn.cmindtesttask.views.articles

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kitttn.cmindtesttask.R
import kitttn.cmindtesttask.model.ArticleEntity

/**
 * @author kitttn
 */

class ArticlesRVAdapter(private val data: MutableList<ArticleEntity>) : RecyclerView.Adapter<ArticleViewHolder>() {
    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ArticleViewHolder?, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.view_article, parent, false)
        return ArticleViewHolder(view)
    }

}

class ArticleViewHolder(val view: View) : RecyclerView.ViewHolder(view)