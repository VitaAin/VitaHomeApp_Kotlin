package com.vita.home.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.vita.home.R
import com.vita.home.api.Api
import com.vita.home.bean.Articles
import com.vita.home.bean.Wrap
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonArticlesFragment : Fragment() {

    private val TAG = "PersonArticlesFragment"

    private var mUserId: Int = 0

    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getParams()
    }

    private fun getParams() {
        if (arguments != null) {
            mUserId = arguments.getInt(PARAM_KEY_USER_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_person_articles, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initData()
    }

    private fun initData() {
        Api.get().getUserArticles(mUserId, object : Callback<Wrap<Articles>> {
            override fun onFailure(call: Call<Wrap<Articles>>, t: Throwable) {
                Log.e(TAG, "onFailure: " + t.toString())
            }

            override fun onResponse(call: Call<Wrap<Articles>>, response: Response<Wrap<Articles>>) {
                Log.i(TAG, "onResponse: " + response.body()?.message)
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
        private val PARAM_KEY_USER_ID = "UserId"

        fun newInstance(userId: Int): PersonArticlesFragment {
            val fragment = PersonArticlesFragment()
            val args = Bundle()
            args.putInt(PARAM_KEY_USER_ID, userId)
            fragment.arguments = args
            return fragment
        }
    }
}
