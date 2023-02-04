package com.example.aston_final.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.aston_final.App
import com.example.aston_final.MainViewModel
import com.example.aston_final.MainViewModelFactory
import com.example.aston_final.model.dto.CharacterForListDto
import com.example.aston_final.model.dto.LocationDto
import com.example.aston_final.view.recycler_view.CharacterRecyclerAdapter
import com.example.aston_final.model.retrofit.Status
import com.example.aston_final.utils.CharacterDiffUtilCallback
import com.example.aston_final.utils.RecyclerDecorator
import com.example.aston_final.R
import com.example.aston_final.viewmodel.LocationDetailsViewModel
import com.example.aston_final.viewmodel.factory.LocationDetailsViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val ARG_LOCATION_ID = "locationId"

class LocationDetailsFragment : Fragment(),
    CharacterRecyclerAdapter.CharacterViewHolder.ItemClickListener {
    private var locationId: Int? = null

    @Inject
    lateinit var vmMainFactory: MainViewModelFactory

    @Inject
    lateinit var vmFactory: LocationDetailsViewModelFactory
    private lateinit var mainViewModel: MainViewModel
    private lateinit var viewModel: LocationDetailsViewModel
    private var listForRecycler: MutableList<CharacterForListDto> = mutableListOf()
    private lateinit var recyclerCharacterList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            locationId = it.getInt(ARG_LOCATION_ID)
        }
        val locationDetailsComponent =
            (requireActivity().applicationContext as App).appComponent.getLocationDetailsComponentBuilder()
                .locationId(locationId!!)
                .characterItemClickListener(this)
                .build()
        locationDetailsComponent.inject(this)
        mainViewModel =
            ViewModelProvider(requireActivity(), vmMainFactory)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_location_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initView()
        val swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout)
        swipeRefreshLayout.setOnRefreshListener {
            this.viewModelStore.clear()
            initView()
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun initView() {
        viewModel = ViewModelProvider(this, vmFactory)[LocationDetailsViewModel::class.java]
        val detailsLayout = view?.findViewById<ConstraintLayout>(R.id.location_detailsLayout)
        detailsLayout?.visibility = View.INVISIBLE
        val pbView = view?.findViewById<ProgressBar>(R.id.progress)
        pbView?.visibility = View.VISIBLE
        val pbViewRecycler = view?.findViewById<ProgressBar>(R.id.progressRecycler)
        pbViewRecycler?.visibility = View.VISIBLE

        lifecycleScope.launch {
            viewModel.location.collect {
                when (it.status) {
                    Status.LOADING -> {
                        detailsLayout?.visibility = View.INVISIBLE
                        pbView?.visibility = View.VISIBLE
                    }
                    Status.SUCCESS -> {
                        detailsLayout?.visibility = View.VISIBLE
                        pbView?.visibility = View.GONE
                        it.data?.let { location ->
                            updateView(location)
                        }
                    }
                    Status.ERROR -> {
                        pbView?.visibility = View.GONE
                        Log.e("AAA", "${it.message}")
                    }
                }
            }
        }
        lifecycleScope.launch {
            viewModel.characters.collect {
                when (it.status) {
                    Status.LOADING -> {
                        pbViewRecycler?.visibility = View.VISIBLE
                    }
                    Status.SUCCESS -> {
                        pbViewRecycler?.visibility = View.GONE
                        it.data?.let { characters ->
                            val oldList = listForRecycler.map { character -> character.copy() }
                            val errorTextTitle = view?.findViewById<TextView>(R.id.errorTextTitle)
                            val errorText = view?.findViewById<TextView>(R.id.errorText)
                            if (characters.isEmpty()) {
                                errorTextTitle?.visibility = View.VISIBLE
                                errorText?.visibility = View.VISIBLE
                            } else {
                                errorTextTitle?.visibility = View.GONE
                                errorText?.visibility = View.GONE
                            }
                            listForRecycler.clear()
                            listForRecycler.addAll(characters)
                            notifyWithDiffUtil(oldList.toMutableList())
                        }
                    }
                    Status.ERROR -> {
                        pbView?.visibility = View.GONE
                        Log.e("AAAList", "${it.message}")
                    }
                }
            }
        }
    }

    private fun updateView(currentLocation: LocationDto) {
        val textViewName = view?.findViewById<TextView>(R.id.textView_name)
        val textViewType = view?.findViewById<TextView>(R.id.textView_type)
        val textViewDimension = view?.findViewById<TextView>(R.id.textView_dimension)

        textViewName?.text = currentLocation.name
        textViewType?.text = currentLocation.type
        textViewDimension?.text = currentLocation.dimension
    }

    private fun initRecyclerView() {
        val mAdapter = CharacterRecyclerAdapter(
            listForRecycler, this
        )
        recyclerCharacterList = requireView().findViewById(R.id.recycler_residents)
        recyclerCharacterList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(RecyclerDecorator())
            adapter = mAdapter
        }
    }

    private fun notifyWithDiffUtil(oldCharacters: MutableList<CharacterForListDto>) {
        val characterDiffUtilCallback = CharacterDiffUtilCallback(oldCharacters, listForRecycler)
        val characterDiffResult = DiffUtil.calculateDiff(characterDiffUtilCallback)
        recyclerCharacterList.adapter?.let { characterDiffResult.dispatchUpdatesTo(it) }
    }

    companion object {
        @JvmStatic
        fun newInstance(locationId: Int) =
            LocationDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_LOCATION_ID, locationId)
                }
            }
    }

    override fun onItemClick(character: CharacterForListDto?) {
        if (character?.name != "") {
            val fragment: Fragment = CharacterDetailsFragment.newInstance(character?.id!!)
            mainViewModel.changeCurrentDetailsFragment(fragment)
        }
    }
}