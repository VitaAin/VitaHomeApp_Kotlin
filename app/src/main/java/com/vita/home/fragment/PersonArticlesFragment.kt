package com.vita.home.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.vita.home.R
import com.vita.home.activity.ArticleShowActivity
import com.vita.home.adapter.commonrvadapter.RvCommonAdapter
import com.vita.home.adapter.commonrvadapter.ViewHolder
import com.vita.home.api.Api
import com.vita.home.bean.Article
import com.vita.home.bean.Wrap
import com.vita.home.constant.Key
import kotlinx.android.synthetic.main.fragment_with_rv.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonArticlesFragment : Fragment() {

    private val TAG = "PersonArticlesFragment"

    private var mListener: OnFragmentInteractionListener? = null

    private var mUserId: Int = 0
    private var mUserArticleList: List<Article>? = ArrayList()
    private var mUserArticlesRvAdapter: UserArticlesRvAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getParams()
    }

    private fun getParams() {
        if (arguments != null) {
            mUserId = arguments.getInt(Key.KEY_USER_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_with_rv, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPersonArticlesRv()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initData()
    }

    private fun setupPersonArticlesRv() {
        rv_in_frag.layoutManager = LinearLayoutManager(context)
        rv_in_frag.hasFixedSize()
        rv_in_frag.itemAnimator = DefaultItemAnimator()
        mUserArticlesRvAdapter = UserArticlesRvAdapter(context, mUserArticleList, R.layout.item_person_article)
        rv_in_frag.adapter = mUserArticlesRvAdapter
        mUserArticlesRvAdapter?.setOnItemClickListener(object : RvCommonAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val intent = Intent(activity, ArticleShowActivity::class.java)
                intent.putExtra(Key.KEY_ARTICLE_ID, mUserArticleList?.get(position)?.id)
                startActivity(intent)
            }
        })
    }

    private fun initData() {
        Log.i(TAG, "userId: " + mUserId)
        Api.get(context).getUserArticles(mUserId, object : Callback<Wrap<List<Article>>> {
            override fun onFailure(call: Call<Wrap<List<Article>>>, t: Throwable) {
                Log.e(TAG, "onFailure: ", t)
            }

            override fun onResponse(call: Call<Wrap<List<Article>>>, response: Response<Wrap<List<Article>>>) {
                Log.i(TAG, "onResponse: " + response.body()?.message)
                if (response.body()?.status == 1) {
                    mUserArticleList = response.body()?.data
                    mUserArticlesRvAdapter?.replaceData(mUserArticleList)
                }
            }
        })
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        fun newInstance(userId: Int): PersonArticlesFragment {
            val fragment = PersonArticlesFragment()
            val args = Bundle()
            args.putInt(Key.KEY_USER_ID, userId)
            fragment.arguments = args
            return fragment
        }
    }
}

class UserArticlesRvAdapter(ctx: Context, dataList: List<Article>?, layoutId: Int)
    : RvCommonAdapter<Article>(ctx, dataList, layoutId) {
    override fun convert(holder: ViewHolder, item: Article, position: Int) {
        holder.setText(R.id.tv_article_title, item.title!!)
        holder.setText(R.id.tv_article_comments_count, item.commentsCount.toString())
        holder.setText(R.id.tv_article_likes_count, item.likesCount.toString())
        holder.setText(R.id.tv_article_created_at, item.createdAt.toString())
    }
}