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
import android.widget.AdapterView

import com.vita.home.R
import com.vita.home.activity.ArticleShowActivity
import com.vita.home.activity.PersonalCenterActivity
import com.vita.home.adapter.commonrvadapter.RvCommonAdapter
import com.vita.home.adapter.commonrvadapter.ViewHolder
import com.vita.home.api.Api
import com.vita.home.bean.Notification
import com.vita.home.bean.Wrap
import com.vita.home.constant.Key
import com.vita.home.helper.AccountHelper
import kotlinx.android.synthetic.main.fragment_with_rv.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NoticeFollowFragment : Fragment() {

    private val TAG = "NoticeFollowFragment"

    private var mListener: OnFragmentInteractionListener? = null

    private var mNoticeFollowList: List<Notification>? = ArrayList()
    private var mNoticeFollowRvAdapter: NoticeFollowRvAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getParams()
    }

    private fun getParams() {
        if (arguments != null) {
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_with_rv, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupEmpty()
        setupRv()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initData()
    }

    private fun setupEmpty() {
        tv_empty.text = "少侠，当前还没有人关注你，\n快去结交更多侠士吧~~"
    }

    private fun setupRv() {
        rv_in_frag.layoutManager = LinearLayoutManager(context)
        rv_in_frag.hasFixedSize()
        rv_in_frag.itemAnimator = DefaultItemAnimator()
        mNoticeFollowRvAdapter = NoticeFollowRvAdapter(context, mNoticeFollowList, R.layout.item_notice_follow)
        rv_in_frag.adapter = mNoticeFollowRvAdapter
    }

    private fun initData() {
        Api.get(context).getNoticeFollow(object : Callback<Wrap<List<Notification>>> {
            override fun onFailure(call: Call<Wrap<List<Notification>>>, t: Throwable) {
                Log.e(TAG, "onFailure: ", t)
            }

            override fun onResponse(call: Call<Wrap<List<Notification>>>, response: Response<Wrap<List<Notification>>>) {
                Log.i(TAG, "onResponse: " + response.body()?.message)
                if (response.body()?.status == 1) {
                    mNoticeFollowList = response.body()?.data
                    mNoticeFollowRvAdapter?.replaceData(mNoticeFollowList)
                    showEmpty(mNoticeFollowRvAdapter?.itemCount == 0)
                }
            }
        })
    }

    private fun showEmpty(show: Boolean) =
            if (show) {
                tv_empty.visibility = View.VISIBLE
                rv_in_frag.visibility = View.GONE
            } else {
                tv_empty.visibility = View.GONE
                rv_in_frag.visibility = View.VISIBLE
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
        fun newInstance(): NoticeFollowFragment {
            val fragment = NoticeFollowFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}

class NoticeFollowRvAdapter(private val ctx: Context, dataList: List<Notification>?, layoutId: Int)
    : RvCommonAdapter<Notification>(ctx, dataList, layoutId) {
    override fun convert(holder: ViewHolder, item: Notification, position: Int) {
        holder.setText(R.id.tv_notice_follow_name, item.data?.name!!)
        holder.setText(R.id.tv_notice_follow_create_at, item.createdAt!!)
        holder.setOnClickListener(R.id.tv_notice_follow_name,
                View.OnClickListener {
                    var intent = Intent(ctx, PersonalCenterActivity::class.java)
                    intent.putExtra(Key.KEY_USER_ID, item.data?.userId!!)
                    ctx.startActivity(intent)
                })
    }
}