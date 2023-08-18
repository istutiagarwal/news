package com.example.rvcamera.view

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Adapter
import android.widget.SearchView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.rvcamera.R
import com.example.rvcamera.adapter.BaseAdapter
import com.example.rvcamera.adapter.BaseViewHolder
import com.example.rvcamera.adapter.interfaceL
import com.example.rvcamera.data.ArticlesModel
import com.example.rvcamera.databinding.ActivityMainBinding
import com.example.rvcamera.model.BaseRepositoryImpl
import com.example.rvcamera.model.BaseUseCase
import com.example.rvcamera.source.BaseService
import com.example.rvcamera.source.RetrofitHelper
import com.example.rvcamera.viewModel.BaseViewModel
import com.example.rvcamera.viewModel.BaseViewModelFactory
import java.util.Locale.filter
import android.widget.AbsListView
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : AppCompatActivity(), interfaceL {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: BaseViewModel
    private lateinit var binding: ActivityMainBinding
    private  var adapter: BaseAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        val repository = BaseRepositoryImpl()

        viewModel = ViewModelProvider(
            this,
            BaseViewModelFactory(repository)
        ).get(BaseViewModel::class.java)

        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycler_view)

        viewModel.topHeadingLiveData.observe(this) {
            adapter = it.data?.let { it1 ->
                val totalPages = it1.totalResults / 500 + 2
                isLastPage = viewModel.breakingNewsPage == totalPages
                BaseAdapter(it1.article).apply {
                    newsListener(this@MainActivity)
                }
            }
            recyclerView.adapter = adapter
            recyclerView.addOnScrollListener(this@MainActivity.scrollListener)
        }

    }

    override fun onClick(position: Int, list: ArticlesModel) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(list.url))
        startActivity(browserIntent)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val searchItem: MenuItem = menu.findItem(R.id.search)
        val searchView: SearchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            override fun onQueryTextChange(qString: String): Boolean {
                return true
            }
            override fun onQueryTextSubmit(qString: String): Boolean {
                viewModel.getSearchResults(q = qString, sortBy = null)
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.popularity -> {
                viewModel.getSearchResults(q = "apple", sortBy = "popularity")
                Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    val scrollListener = object : RecyclerView.OnScrollListener(){
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= 500L
            val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning &&
                    isTotalMoreThanVisible && isScrolling
            if(shouldPaginate) {
                viewModel.getTopHeading()
                isScrolling = false
            } else {
                binding.recyclerView.setPadding(0, 0, 0, 0)
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                isScrolling = true
            }
        }
    }
}