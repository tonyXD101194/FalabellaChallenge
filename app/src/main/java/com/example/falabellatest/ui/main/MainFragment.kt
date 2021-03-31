package com.example.falabellatest.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.falabellatest.ui.MainActivity
import com.example.falabellatest.R
import com.example.falabellatest.adapters.measure.MeasureAdapter
import com.example.falabellatest.extensions.alert
import com.example.falabellatest.interfaces.MeasureAction
import com.example.falabellatest.interfaces.NavigationInterface
import com.example.falabellatest.model.room.MeasureEntity
import com.example.falabellatest.ui.detail.DetailFragment
import com.example.falabellatest.ui.dialogs.LoadingDialog
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(), MeasureAction {

    companion object {

        fun newInstance(navigationInterface: NavigationInterface): MainFragment {

            val fragment = MainFragment()

            fragment.navigation = navigationInterface

            return fragment
        }
    }

    private lateinit var viewModel: MainViewModel

    private lateinit var navigation: NavigationInterface

    private val dialogLoading: LoadingDialog by lazy {

        LoadingDialog.newInstance()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        (requireActivity() as MainActivity).supportActionBar?.title =
            requireContext().resources.getString(R.string.fragment_main_toolbar_title)

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialogLoading.show(childFragmentManager, LoadingDialog.DIALOG_LOADING_TAG)

        this.initializeObservers()
    }

    private fun initializeObservers() {

        this.viewModel.getMeasures()

        this.viewModel.measures.observe(viewLifecycleOwner, Observer {

            if (it.isNotEmpty()) {

                this.fragmentMainTextViewError.visibility = View.GONE
                this.fragmentMainRecyclerViewMeasures.visibility = View.VISIBLE

                this.initializeAdapter(it)
            } else {

                this.fragmentMainTextViewError.visibility = View.VISIBLE
                this.fragmentMainRecyclerViewMeasures.visibility = View.GONE
            }

            dialogLoading.dismiss()
        })

        this.viewModel.message.observe(viewLifecycleOwner, Observer {

            if (dialogLoading.isAdded) {

                dialogLoading.dismiss()
            }

            requireActivity().alert(
                messageStringId = it,
                positiveStringId = R.string.accept_button
            )
        })
    }

    private fun initializeAdapter(list: List<MeasureEntity>) {

        this.fragmentMainRecyclerViewMeasures.adapter = MeasureAdapter(
            list = list,
            callback = this
        )
    }

    override fun onClickMeasure(id: Long) {

        val fragment = DetailFragment.newInstance(id)

        navigation.pushFragment(fragment)
    }

    // region actions search

    fun searchByCode(code: String) {

        this.viewModel.searchMeasureByCode(code)
    }

    fun resetList() {

        this.viewModel.resetList()
    }

    // endregion
}