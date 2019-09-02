package io.etna.whatstheweather.ui.main.BookmarksFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import dagger.android.support.DaggerFragment
import io.etna.whatstheweather.ui.main.MainViewModel
import javax.inject.Inject
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import io.etna.whatstheweather.R


class BookmarksFragment: DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var adapter: BookmarksRecycleAdapter

    private lateinit var mViewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bookmarks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter

        val touchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: ViewHolder, target: ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
                mViewModel.removeFavoriteLocationWeather(adapter.locations[viewHolder.adapterPosition])
            }
        })

        touchHelper.attachToRecyclerView(recyclerView)

        mViewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)

        mViewModel.favoriteLocationWeather.removeObservers(viewLifecycleOwner)
        mViewModel.favoriteLocationWeather.observe(viewLifecycleOwner, Observer{
            adapter.locations = it
            adapter.notifyDataSetChanged()
        })

    }
}