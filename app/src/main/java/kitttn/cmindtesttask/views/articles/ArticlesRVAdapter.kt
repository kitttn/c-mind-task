package kitttn.cmindtesttask.views.articles

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso
import kitttn.cmindtesttask.R
import kitttn.cmindtesttask.model.ArticleEntity

/**
 * @author kitttn
 */

class ArticlesRVAdapter(private val data: MutableList<ArticleEntity>, private val context: Context)
    : RecyclerView.Adapter<ArticleViewHolder>() {

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ArticleViewHolder?, position: Int) {
        val article = data[position]
        holder?.header?.text = article.title
        holder?.author?.text = article.author
        holder?.time?.text = article.publishedAt
        Picasso.with(context).load(article.urlToImage).into(holder?.image)

        fun openUrl() {
            try {
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(article.url)))
            } catch (e: Exception) {
                Toast.makeText(context, "Can't open browser!", Toast.LENGTH_LONG).show()
            }
        }

        fun showDialog() = AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.open_in_browser))
                .setMessage(context.getString(R.string.dou_you_want_to_open))
                .setNegativeButton("Cancel", { dialog, _ -> dialog.dismiss() })
                .setPositiveButton("OK", { _, _ -> openUrl() })
                .show()

        holder?.view?.setOnClickListener { showDialog() }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.view_article, parent, false)
        return ArticleViewHolder(view)
    }

}

class ArticleViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val image by lazy { view.findViewById<ImageView?>(R.id.articleImage) }
    val header by lazy { view.findViewById<TextView?>(R.id.articleHeader) }
    val author by lazy { view.findViewById<TextView?>(R.id.articleAuthor) }
    val time by lazy { view.findViewById<TextView?>(R.id.articleTime) }
}