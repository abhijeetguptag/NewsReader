package com.agg.me.newsreader.view.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.agg.me.newsreader.R
import com.agg.me.newsreader.databinding.ActivityHomeBinding
import com.agg.me.newsreader.view.theme.NEWSReaderTheme
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityHomeBinding

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
}
