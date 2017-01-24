package com.overetch.kample.main


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.overetch.kample.R
import com.overetch.kample.data.Movie
import kotlinx.android.synthetic.main.fragment_main.*
import org.jetbrains.anko.support.v4.onRefresh
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment(), MainContract.View {
    lateinit var mPresenter: MainContract.Presenter
    lateinit var mAdapter: MainListAdapter
    lateinit var mRecyclerView: RecyclerView


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_main, container, false) as View
        mRecyclerView = view.findViewById(R.id.mRecyclerView) as RecyclerView
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        mPresenter.loadMovies()
        mSwiperRefreshLayout.onRefresh { mPresenter.loadMovies() }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun showMovies(movies: ArrayList<Movie>) {
        mAdapter = MainListAdapter(movies)
        mRecyclerView.adapter = mAdapter
        mRecyclerView
    }

    override fun onRefreshFinished() {
        mSwiperRefreshLayout.isRefreshing = false
    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        mPresenter = presenter

    }

    override fun onRefreshStarted() {
        mSwiperRefreshLayout.isRefreshing = true
    }

}