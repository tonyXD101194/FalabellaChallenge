package com.example.falabellatest.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.falabellatest.ui.MainActivity
import com.example.falabellatest.R
import com.example.falabellatest.extensions.format
import com.example.falabellatest.model.room.MeasureEntity
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    companion object {

        fun newInstance(id: Long): DetailFragment {

            val fragment = DetailFragment()

            fragment.id = id

            return fragment
        }
    }

    private lateinit var viewModel: DetailViewModel

    private var id: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.initializeObservers()
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)

        val menuItem = menu.findItem(R.id.action_search)

        menuItem.isVisible = false
    }

    private fun initializeObservers() {

        this.viewModel.getMeasure(id)

        this.viewModel.measure.observe(viewLifecycleOwner, Observer {

            this.showMeasureInformation(it)
        })
    }

    private fun showMeasureInformation(measure: MeasureEntity) {

        (requireActivity() as MainActivity).supportActionBar?.title = measure.title

        this.fragmentDetailTextViewCode.text = requireContext().format(R.string.fragment_detail_code, measure.code)
        this.fragmentDetailTextViewName.text = requireContext().format(R.string.fragment_detail_name, measure.name)
        this.fragmentDetailTextViewMeasure.text = requireContext().format(R.string.fragment_detail_measure, measure.name)
        this.fragmentDetailTextViewDate.text = requireContext().format(R.string.fragment_detail_date, measure.date)
        this.fragmentDetailTextViewValue.text = requireContext().format(R.string.fragment_detail_value, measure.value.toString())
    }
}