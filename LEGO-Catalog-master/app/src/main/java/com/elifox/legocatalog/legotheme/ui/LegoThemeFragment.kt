package com.elifox.legocatalog.legotheme.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.elifox.legocatalog.R
import com.elifox.legocatalog.data.Result
import com.elifox.legocatalog.databinding.FragmentThemesBinding
import com.elifox.legocatalog.ui.VerticalItemDecoration
import com.elifox.legocatalog.ui.hide
import com.elifox.legocatalog.ui.show
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class LegoThemeFragment : Fragment() {

    private val legoThemeViewModel: LegoThemeViewModel by viewModel()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentThemesBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = LegoThemeAdapter()
        binding.recyclerView.addItemDecoration(
                VerticalItemDecoration(resources.getDimension(R.dimen.margin_normal).toInt(), true)
        )
        binding.recyclerView.adapter = adapter

        subscribeUi(binding, adapter)

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun subscribeUi(binding: FragmentThemesBinding, adapter: LegoThemeAdapter) {
        legoThemeViewModel.legoThemes.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    binding.progressBar.hide()
                    result.data?.let { adapter.submitList(it.results) }
                }
                Result.Status.LOADING -> binding.progressBar.show()
                Result.Status.ERROR -> {
                    binding.progressBar.hide()
                    Snackbar.make(binding.root, result.message!!, Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }
}