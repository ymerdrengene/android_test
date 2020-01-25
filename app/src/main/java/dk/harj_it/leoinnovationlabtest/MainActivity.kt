package dk.harj_it.leoinnovationlabtest

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import dk.harj_it.leoinnovationlabtest.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setSupportActionBar(binding.toolbar)

        setupNavigation()

        viewModel.username.observe(this, Observer { username ->
            if (username.isNotBlank()) {
                lifecycleScope.launch {
                    viewModel.fetchFromGithub(username)
                }
            }
        })
    }

    private fun setupNavigation() {
        findNavController(R.id.nav_host_fragment).let {
            val appBarConfiguration = AppBarConfiguration(
                setOf(R.id.navigation_profile, R.id.navigation_repositories)
            )
            setupActionBarWithNavController(it, appBarConfiguration)
            binding.navView.setupWithNavController(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val searchView = menu?.findItem(R.id.search_username)?.actionView as SearchView
        searchView.apply {
            queryHint = getString(R.string.main_search_hint)
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String?): Boolean {
                    query ?: return false

                    viewModel.username.value = query
                    return false
                }

                override fun onQueryTextChange(query: String?) = false
            })
        }

        return true
    }
}
