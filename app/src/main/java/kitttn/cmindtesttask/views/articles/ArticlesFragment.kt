package kitttn.cmindtesttask.views.articles

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kitttn.cmindtesttask.R
import kitttn.cmindtesttask.model.ArticleEntity
import kitttn.cmindtesttask.views.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_data_page.*

/**
 * @author kitttn
 */

class ArticlesFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_data_page, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataRV.layoutManager = LinearLayoutManager(act)
        dataRV.adapter = createTestAdapter()
    }

    private fun createTestAdapter(): ArticlesRVAdapter {
        val list = (1..20).map { ArticleEntity("", "", "", "", "", "") }.toMutableList()
        return ArticlesRVAdapter(list)
    }
}