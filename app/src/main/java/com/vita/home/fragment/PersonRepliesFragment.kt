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
import com.vita.home.bean.Reply
import com.vita.home.bean.Wrap
import com.vita.home.constant.Key
import kotlinx.android.synthetic.main.fragment_person_articles.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonRepliesFragment : Fragment() {

    private val TAG = "PersonRepliesFragment"

    private var mListener: OnFragmentInteractionListener? = null

    private var mUserId: Int = 0
    private var mUserReplyList: List<Reply>? = ArrayList()
    private var mUserRepliesRvAdapter: UserRepliesRvAdapter? = null

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
        return inflater!!.inflate(R.layout.fragment_person_articles, container, false)
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
        rv_person_articles.layoutManager = LinearLayoutManager(context)
        rv_person_articles.hasFixedSize()
        rv_person_articles.itemAnimator = DefaultItemAnimator()
        mUserRepliesRvAdapter = UserRepliesRvAdapter(context, mUserReplyList, R.layout.item_person_reply)
        rv_person_articles.adapter = mUserRepliesRvAdapter
        mUserRepliesRvAdapter?.setOnItemClickListener(object : RvCommonAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val intent = Intent(activity, ArticleShowActivity::class.java)
                intent.putExtra(Key.KEY_ARTICLE_ID, mUserReplyList?.get(position)?.commentable?.id)
                startActivity(intent)
            }
        })
    }

    private fun initData() {
        Api.get(context).getUserReplies(mUserId, object : Callback<Wrap<List<Reply>>> {
            override fun onFailure(call: Call<Wrap<List<Reply>>>, t: Throwable) {
                Log.e(TAG, "onFailure: ", t)
            }

            override fun onResponse(call: Call<Wrap<List<Reply>>>, response: Response<Wrap<List<Reply>>>) {
                Log.i(TAG, "onResponse: " + response.body()?.message)
                if (response.body()?.status == 1) {
                    mUserReplyList = response.body()?.data
                    mUserRepliesRvAdapter?.replaceData(mUserReplyList)
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
        fun newInstance(userId: Int): PersonRepliesFragment {
            val fragment = PersonRepliesFragment()
            val args = Bundle()
            args.putInt(Key.KEY_USER_ID, userId)
            fragment.arguments = args
            return fragment
        }
    }
}

class UserRepliesRvAdapter(ctx: Context, dataList: List<Reply>?, layoutId: Int)
    : RvCommonAdapter<Reply>(ctx, dataList, layoutId) {
    override fun convert(holder: ViewHolder, item: Reply, position: Int) {
        holder.setText(R.id.tv_reply_content, item.content!!)
    }
}