package kitttn.cmindtesttask.views.articles

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kitttn.cmindtesttask.R
import kitttn.cmindtesttask.model.ArticleEntity
import kitttn.cmindtesttask.presenter.ArticlesPresenter
import kitttn.cmindtesttask.views.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_data_page.*
import javax.inject.Inject

/**
 * @author kitttn
 */

class ArticlesFragment : BaseFragment() {
    private lateinit var sourceId: String
    private var articles = mutableListOf<ArticleEntity>()
    private var adapter = ArticlesRVAdapter(articles)
    @Inject lateinit var presenter: ArticlesPresenter

    companion object {
        fun newInstance(sourceId: String): ArticlesFragment {
            val fragment = ArticlesFragment()
            fragment.sourceId = sourceId
            return fragment
        }
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_data_page, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        act.component.inject(this)

        dataRV.layoutManager = LinearLayoutManager(act)
        dataRV.adapter = createTestAdapter()

        refresher.setOnRefreshListener { loadArticles() }
    }

    override fun onStart() {
        super.onStart()
        loadArticles()
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }

    fun loadArticles() {
        refresher.isRefreshing = true

        presenter.loadArticles(sourceId, {
            refresher.isRefreshing = false
            articles.clear()
            articles.addAll(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun createTestAdapter(): ArticlesRVAdapter {
        val list = (1..20).map { ArticleEntity("", "", "", "", "", "") }.toMutableList()
        return ArticlesRVAdapter(list)
    }
}