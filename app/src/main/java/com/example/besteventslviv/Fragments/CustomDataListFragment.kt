package com.example.besteventslviv.Fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.besteventslviv.Database.AppDatabase
import com.example.besteventslviv.Database.Dao.ItemListDao
import com.example.besteventslviv.Fragments.ListFactories.ListFactory
import com.example.besteventslviv.Fragments.ListFactories.ListType
import com.example.besteventslviv.R

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [CustomDataListFragment.OnListFragmentInteractionListener] interface.
 */
class CustomDataListFragment : Fragment() {

    private lateinit var listType: ListType
    private var appDatabase: AppDatabase? = null
    private var viewAdapter: RecyclerView.Adapter<*>? = null
    private var listDao: ItemListDao<*>? = null
    private var listener: OnListFragmentInteractionListener<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            listType = ListType.valueOf(it.getString(ARG_LIST_TYPE))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_custom_data_list, container, false)
        initComponents()

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                adapter = viewAdapter
            }
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as OnListFragmentInteractionListener<*>
            initComponents()
        }
        catch (e: Exception){
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        appDatabase = null
        viewAdapter = null
        listDao = null
        listener = null
    }

    private fun initComponents(){
        val factory = ListFactory.getFactory(listType)!!

        appDatabase = AppDatabase.getAppDatabase(activity!!.baseContext)!!
        listDao = factory.getDao(appDatabase!!)
        val args = factory.getArgs()
        val itemList = listDao!!.getListItems(args)
        viewAdapter = factory.getAdapter(itemList, listener!!)
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnListFragmentInteractionListener<T> {
        fun onListFragmentInteraction(item: T?)
        fun onListFragmentSelect(item: T?)
        fun onListFragmentDelete(item: T?)
    }

    companion object {

        const val ARG_LIST_TYPE = "list_type"

        @JvmStatic
        fun newInstance(listType: ListType) =
            CustomDataListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_LIST_TYPE, listType.toString())
                }
            }
    }
}
