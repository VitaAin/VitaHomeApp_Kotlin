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
import com.bumptech.glide.Glide

import com.vita.home.R
import com.vita.home.activity.PersonalCenterActivity
import com.vita.home.adapter.commonrvadapter.RvCommonAdapter
import com.vita.home.adapter.commonrvadapter.ViewHolder
import com.vita.home.api.Api
import com.vita.home.bean.User
import com.vita.home.bean.Wrap
import com.vita.home.constant.Key
import kotlinx.android.synthetic.main.fragment_with_rv.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonFollowsFragment : Fragment() {

    private val TAG = "PersonFollowsFragment"

    private var mListener: OnFragmentInteractionListener? = null

    private var mUserId: Int = 0
    private var mUserFollowsList: List<User>? = ArrayList()
    private var mUserFollowsRvAdapter: UserFollowsRvAdapter? = null

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

        setupEmpty()
        setupPersonArticlesRv()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initData()
    }

    private fun setupEmpty() {
        tv_empty.text = "少侠还没有关注任何人~~"
    }

    private fun setupPersonArticlesRv() {
        rv_in_frag.layoutManager = LinearLayoutManager(context)
        rv_in_frag.hasFixedSize()
        rv_in_frag.itemAnimator = DefaultItemAnimator()
        mUserFollowsRvAdapter = UserFollowsRvAdapter(context, mUserFollowsList, R.layout.item_person_follow)
        rv_in_frag.adapter = mUserFollowsRvAdapter
        mUserFollowsRvAdapter?.setOnItemClickListener(object : RvCommonAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val intent = Intent(activity, PersonalCenterActivity::class.java)
                intent.putExtra(Key.KEY_USER_ID, mUserFollowsList?.get(position)?.id)
                startActivity(intent)
            }
        })
    }

    private fun initData() {
        Api.get(context).getUserFollowUsers(mUserId, object : Callback<Wrap<List<User>>> {
            override fun onFailure(call: Call<Wrap<List<User>>>, t: Throwable) {
                Log.e(TAG, "onFailure: ", t)
            }

            override fun onResponse(call: Call<Wrap<List<User>>>, response: Response<Wrap<List<User>>>) {
                Log.i(TAG, "onResponse: " + response.body()?.message)
                if (response.body()?.status == 1) {
                    mUserFollowsList = response.body()?.data
                    mUserFollowsRvAdapter?.replaceData(mUserFollowsList)
                    showEmpty(mUserFollowsRvAdapter?.itemCount == 0)
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
        fun newInstance(userId: Int): PersonFollowsFragment {
            val fragment = PersonFollowsFragment()
            val args = Bundle()
            args.putInt(Key.KEY_USER_ID, userId)
            fragment.arguments = args
            return fragment
        }
    }
}

class UserFollowsRvAdapter(private val ctx: Context, dataList: List<User>?, layoutId: Int)
    : RvCommonAdapter<User>(ctx, dataList, layoutId) {
    override fun convert(holder: ViewHolder, item: User, position: Int) {
        Glide.with(ctx).load(item.avatar).into(holder.getView(R.id.iv_follow_avatar))
        holder.setText(R.id.tv_follow_name, item.name!!)
    }
}