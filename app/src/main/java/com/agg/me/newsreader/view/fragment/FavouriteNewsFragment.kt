package com.agg.me.newsreader.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.agg.me.newsreader.R
import com.agg.me.newsreader.data.model.Article
import com.agg.me.newsreader.data.util.NetworkResponse
import com.agg.me.newsreader.databinding.FragmentFavNewsBinding
import com.agg.me.newsreader.view.adapter.NewsAdapter
import com.agg.me.newsreader.view.adapter.OnFavButtonClickListener
import com.agg.me.newsreader.view.viewmodel.NewsViewModel
import com.agg.me.newsreader.view.viewmodel.NewsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavouriteNewsFragment : Fragment() {

    private var _binding: FragmentFavNewsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy {
        ViewModelProvider(this, factory)[NewsViewModel::class.java]
    }

    @Inject
    lateinit var factory: NewsViewModelFactory

    private var newsAdapter: NewsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavNewsBinding.inflate(inflater, container, false)
        newsAdapter = NewsAdapter( object: OnFavButtonClickListener {
            override fun onFavButtonClicked(isFav: Boolean, article: Article) {
                if (isFav) {
                    viewModel.saveArticle(article)
                } else {
                    viewModel.unSaveArticle(article)
                }
            }
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            viewModel.getFavArticles()
            viewModel.favArticlesData.observe(viewLifecycleOwner, ::setData)

            viewPager.apply {
                adapter = newsAdapter
            }
        }
    }

    private fun setData(resource: NetworkResponse<List<Article>>?) {
        when (resource) {
            is NetworkResponse.Success -> {
                resource.data?.let {
                    newsAdapter?.differ?.submitList(it)
                    showLoadingAnimation()
                }
            }

            is NetworkResponse.Error -> {
                resource.message?.let { error ->
                    showLoadingAnimation(false)
                    Toast.makeText(activity,getString(R.string.try_later), Toast.LENGTH_SHORT).show()
                    Log.e(TAG, error)
                }
            }

            is NetworkResponse.Loading -> {
                showLoadingAnimation(true)
            }

            else -> Log.d(TAG, "Resource not found!")
        }
    }


    private fun showLoadingAnimation(isShown: Boolean = false) {
        with(binding) {
            lottieAnimation.visibility = if (isShown) View.VISIBLE else View.INVISIBLE
            viewPager.visibility = if (isShown) View.INVISIBLE else View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private val TAG = NewsFragment::class.simpleName.toString()
    }
}