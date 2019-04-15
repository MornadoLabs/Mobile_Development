package com.example.besteventslviv.Fragments.Adapters

import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView

import com.example.besteventslviv.Fragments.CustomDataListFragment
import com.example.besteventslviv.Models.DayEvent
import com.example.besteventslviv.Fragments.CustomDataListFragment.OnListFragmentInteractionListener
import com.example.besteventslviv.R

import kotlinx.android.synthetic.main.fragment_day_events_list_item.view.*

/**
 * [RecyclerView.Adapter] that can display an [DayEvent] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class DayEventsListRecyclerViewAdapter(
    private val mValues: List<DayEvent>,
    private val mListener: CustomDataListFragment.OnListFragmentInteractionListener<DayEvent>?
) : RecyclerView.Adapter<DayEventsListRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as DayEvent
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_day_events_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.UserEventID.toString()
        holder.mNameView.text = item.Event.Name
        holder.mTimeView.text = item.Event.Date.time.toString()
        holder.mNotifyView.isChecked = item.Notyfi
        holder.mImageView.setImageBitmap(BitmapFactory.decodeByteArray(item.Event.Image, 0, item.Event.Image.size))

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.day_event_id
        val mNameView: TextView = mView.event_name
        val mTimeView: TextView = mView.time
        val mNotifyView: CheckBox = mView.notify
        val mCancelButton: Button = mView.cancel_event
        val mImageView: ImageView = mView.event_image

        override fun toString(): String {
            return super.toString() + " '${mNameView.text}' ${mTimeView.text}"
        }
    }
}