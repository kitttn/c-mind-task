package kitttn.cmindtesttask.views.articles

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kitttn.cmindtesttask.R
import kitttn.cmindtesttask.model.ArticleEntity
import kitttn.cmindtesttask.presenter.ArticlesPresenter
import kitttn.cmindtesttask.states.ArticleState
import kitttn.cmindtesttask.states.ArticleStateData
import kitttn.cmindtesttask.states.ArticleStateError
import kitttn.cmindtesttask.states.ArticleStateLoading
import kitttn.cmindtesttask.views.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_data_page.*
import javax.inject.Inject

/**
 * @author kitttn
 */

interface ArticleView {
    fun render(state: ArticleState)
}

class ArticlesFragment : BaseFragment(), ArticleView {
    private val TAG = "ArticlesFragment"
    private lateinit var sourceId: String
    private var articles = mutableListOf<ArticleEntity>()
    private val adapter by lazy { ArticlesRVAdapter(articles, act) }
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
        if (savedInstanceState != null)
            sourceId = savedInstanceState.getString("sourceId")

        presenter.view = this
        presenter.sourceId = sourceId

        dataRV.layoutManager = LinearLayoutManager(act)
        dataRV.adapter = adapter

        refresher.setOnRefreshListener { presenter.loadArticles() }
    }

    override fun onStart() {
        super.onStart()
        presenter.start()
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString("sourceId", sourceId)
        Log.i(TAG, "onSaveInstanceState: Saved sourceId to bundle!")
    }

    override fun render(state: ArticleState) {
        refresher.isRefreshing = false

        when (state) {
            is ArticleStateLoading -> refresher.isRefreshing = true
            is ArticleStateError -> state.error.printStackTrace()
            is ArticleStateData -> {
                articles.clear()
                articles.addAll(state.articles)
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun createTestAdapter(): ArticlesRVAdapter {
        val list = (1..20).map { ArticleEntity("", "", "", "", "", "") }.toMutableList()
        return ArticlesRVAdapter(list, act)
    }
}