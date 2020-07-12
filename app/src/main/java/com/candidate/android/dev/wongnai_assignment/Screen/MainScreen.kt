package com.candidate.android.dev.wongnai_assignment.Screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.candidate.android.dev.wongnai_assignment.Adapter.CoinAdapter
import com.candidate.android.dev.wongnai_assignment.BaseAdapter.BaseScreen
import com.candidate.android.dev.wongnai_assignment.Data.repository.coin.CoinServiceImpl
import com.candidate.android.dev.wongnai_assignment.Extension.hideSoftKeyboard
import com.candidate.android.dev.wongnai_assignment.Extension.simpleName
import com.candidate.android.dev.wongnai_assignment.databinding.FragmentMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainScreen : BaseScreen() {

    private val viewModel: MainScreenViewModel by viewModel()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val adapterCoin = CoinAdapter()
    private var scrollController = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("${simpleName()} Created")
        initInstance()
    }

    private fun initInstance() {
        setCollapseTextColor()
        setupSwipeRefresh()
        setupRecycleView()
        listenerEditText()
        showLoading()

        viewModel.coinData.observe(viewLifecycleOwner, Observer { _ ->
            setupCoinData()
        })
    }

    private fun listenerEditText() {
        binding.searchEditText.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    searchData()
                    hideSoftKeyboard()
                    binding.searchEditText.clearFocus()
                    return@setOnEditorActionListener true
                }
                else -> return@setOnEditorActionListener false
            }
        }
    }

    private fun setCollapseTextColor() {
        binding.collapsingToolbar.setCollapsedTitleTextColor(
            requireContext().getColor(android.R.color.white)
        )
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            fetchCoinData()
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun setupRecycleView() {
        binding.rvCoin.apply {
            this.adapter = adapterCoin
            this.layoutManager = GridLayoutManager(context, 1)
            this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    if (!recyclerView.canScrollVertically(1)) {
                        setupScroll(scrollController)
                        fetchNextCoinData()
                    }
                }
            })
        }
    }

    private fun setupScroll(canScroll: Boolean) {
        binding.rvCoin.isVerticalScrollBarEnabled = canScroll
    }

    private fun fetchCoinData() {
        showLoading()
        binding.let { v ->
            v.searchEditText.clearFocus()
            v.searchEditText.text?.clear()
        }
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getCoinData()
        }
    }

    private fun searchData() {
        val prefix = binding.searchEditText.text.toString()
        showLoading()
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.searchCoinData(prefix)
        }
    }

    private fun fetchNextCoinData() {
        scrollController = !scrollController
        showLoading()
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getNextCoin()
        }
    }

    private fun setupCoinData() {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.coinData.value?.let {
                when (viewModel.firstSearch) {
                    true -> {
                        adapterCoin.setCoinData(true, coinData = it)
                        binding.rvCoin.scrollToPosition(0)
                    }
                    false -> {
                        adapterCoin.setCoinData(false, coinData = it)
                    }
                    null -> {
                        adapterCoin.setCoinData(null, coinData = it)
                        binding.rvCoin.scrollToPosition(0)
                    }
                }

                hideLoading()
                setupScroll(scrollController)
                viewModel.setNonFirstSearch()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}