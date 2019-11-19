package buu.informatics.s59160134.pinkbirdapplication

import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import buu.informatics.s59160134.pinkbirdapplication.R.layout.fragment_getstarted
import buu.informatics.s59160134.pinkbirdapplication.databinding.ActivityMainBinding
import buu.informatics.s59160134.pinkbirdapplication.databinding.FragmentGetstartedBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import buu.informatics.s59160134.pinkbirdapplication.getstarted.GetStartedFragment as GetStartedFragment1

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration : AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNUSED_VARIABLE")


        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        drawerLayout = binding.drawerLayout


        val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment? ?: return
        val navController = host.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.home_dest, R.id.setting_dest, R.id.history_dest),
            drawerLayout)


        setupActionBar(navController, appBarConfiguration)
        setupBottomNavMenu(navController)
        NavigationUI.setupWithNavController(binding.navView, navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.getStartedFragment
                || destination.id == R.id.fragment_flow_step_one
                || destination.id == R.id.fragment_flow_step_two
                || destination.id == R.id.fragment_flow_step_three){
                binding.bottomNavView.visibility = View.GONE
                supportActionBar?.hide()

            }else{
                binding.bottomNavView.visibility = View.VISIBLE
                supportActionBar?.show()

            }
            val dest: String = try {
                resources.getResourceName(destination.id)
            } catch (e: Resources.NotFoundException) {
                Integer.toString(destination.id)
            }
            Log.d("NavigationActivity", "Navigated to $dest")
        }

//        if(false){
//            navController.navigate(R.id.getStartedFragment)
//        }else{
//            navController.navigate(R.id.home_dest)
//        }



    }

    private fun setupBottomNavMenu(navController: NavController) {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        bottomNav?.setupWithNavController(navController)
    }

    private fun setupActionBar(navController: NavController,
                               appBarConfig : AppBarConfiguration) {
        setupActionBarWithNavController(navController, appBarConfig)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        return item.onNavDestinationSelected(findNavController(R.id.my_nav_host_fragment))
                || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.my_nav_host_fragment).navigateUp(appBarConfiguration)
    }

}
