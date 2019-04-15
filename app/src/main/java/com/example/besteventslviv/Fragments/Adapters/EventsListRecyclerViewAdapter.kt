package com.example.besteventslviv.Fragments.Adapters

import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

import com.example.besteventslviv.Database.Entities.Event
import com.example.besteventslviv.Fragments.CustomDataListFragment
import com.example.besteventslviv.Fragments.CustomDataListFragment.OnListFragmentInteractionListener
import com.example.besteventslviv.R

import kotlinx.android.synthetic.main.fragment_events_list_item.view.*

/**
 * [RecyclerView.Adapter] that can display an [Event] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class EventsListRecyclerViewAdapter(
    private val mValues: List<Event>,
    private val mListener: CustomDataListFragment.OnListFragmentInteractionListener<Event>?
) : RecyclerView.Adapter<EventsListRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Event
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_groups_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.ID.toString()
        holder.mNameView.text = item.Name
        holder.mDateView.text = item.Date.toString()
        holder.mLocationView.text = item.Location
        holder.mImageView.setImageBitmap(BitmapFactory.decodeByteArray(item.Image, 0, item.Image.size))

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.event_id
        val mNameView: TextView = mView.event_name
        val mDateView: TextView = mView.date
        val mLocationView: TextView = mView.location
        val mGetMoreInfoButton: Button = mView.get_more_info
        val mImageView: ImageView = mView.event_image

        override fun toString(): String {
            return super.toString() + " '${mNameView.text}' ${mLocationView.text} ${mDateView.text}"
        }
    }
}