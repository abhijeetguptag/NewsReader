package com.agg.me.newsreader.view.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.agg.me.newsreader.R
import com.agg.me.newsreader.data.util.NetworkResponse
import com.agg.me.newsreader.databinding.ActivityHomeBinding
import com.agg.me.newsreader.view.viewmodel.NewsViewModel
import com.agg.me.newsreader.view.viewmodel.NewsViewModelFactory
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityHomeBinding

    private val viewModel by lazy {
        ViewModelProvider(this, factory)[NewsViewModel::class.java]
    }

    @Inject
    lateinit var factory: NewsViewModelFactory
    private var mOptionsMenu: Menu? = null

    private var appBarConfiguration: AppBarConfiguration? = null
    private var drawerLayout: DrawerLayout? = null
    private var toolbar: Toolbar? = null
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toggle =
            ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)

        binding.apply {
            navigationDrawer.setNavigationItemSelectedListener(this@HomeActivity)
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()
            buttonDrawerMenu.setOnClickListener { drawerLayout.open() }
        }
        drawerLayout = findViewById(R.id.drawerLayout)
        val navView: NavigationView = findViewById(R.id.navigationDrawer)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
         navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.main,
                R.id.fav_main
            ), drawerLayout
        )
//        setupActionBarWithNavController(navController!!, appBarConfiguration!!) //the most important part
        navView.setupWithNavController(navController!!) //the second most important part

    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavArticlesCount()
        viewModel.favArticlesCountData.observe(this, ::setData)
    }
    private fun setData(resource: NetworkResponse<Int>?) {
        when (resource) {
            is NetworkResponse.Success -> {
                resource.data?.let {
                mOptionsMenu?.getItem(0)?.setTitle(getString(R.string.article)+ it.toString())
                }
            }

            else -> Log.d("HomeActivity", "Resource not found!")
        }
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemShare -> {
                Toast.makeText(this, getString(R.string.coming_soon), Toast.LENGTH_SHORT).show()
            }
            R.id.itemFav -> {
                Toast.makeText(this, "Hurray !!!!!", Toast.LENGTH_SHORT).show()
            }
        }
        binding.drawerLayout.close()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.navigation_drawer, menu)
        this.mOptionsMenu = menu
        return super.onCreateOptionsMenu(menu)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.fragmentContainerView).navigateUp(drawerLayout)
    }

}
