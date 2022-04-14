package com.rahilkarim.vyapar.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.button.MaterialButton
import com.rahilkarim.vyapar.R
import com.rahilkarim.vyapar.util.GlobalClass
import com.rahilkarim.vyapar.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private var TAG = "HomeActivity"
    private var activity = this

    lateinit var binding: ActivityHomeBinding
    lateinit var globalClass: GlobalClass

    lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var drawerLayout: DrawerLayout
    lateinit var navController: NavController
    lateinit var listener: NavController.OnDestinationChangedListener
    lateinit var toggle: ActionBarDrawerToggle

    lateinit var companyLogo: ImageView
    lateinit var changeCompanyLayout: LinearLayout
    lateinit var ivChangeCompanyArrow: ImageView
    lateinit var dividerView: View
    lateinit var manageCompaniesbt: MaterialButton

    var isCompanyVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        setNavDrawer()
        bindingNavHeader()
        onClick()
    }

    private fun init() {
        globalClass = GlobalClass.getInstance(activity)
    }

    fun setToolbar(toolbar: Toolbar?): Toolbar? {

        drawerLayout = binding.drawerLayout

        if (toolbar != null) {
            setSupportActionBar(toolbar)
            toggle = ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
            )
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()
        }
        return toolbar
    }

    fun setNavDrawer() {

        navController = findNavController(R.id.fragment)
        binding.navigationView.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(navController.graph,binding.drawerLayout)
//        setupActionBarWithNavController(navController,appBarConfiguration)

        listener = NavController.OnDestinationChangedListener {
                controller,
                destination,
                arguments ->

            val menuLabel = destination.label.toString()
            globalClass.log(TAG,menuLabel)
            showHideManageCompanies(menuLabel)
        }
    }

    fun bindingNavHeader() {
        companyLogo = binding.navigationView.getHeaderView(0).findViewById(R.id.companyLogo)
        changeCompanyLayout = binding.navigationView.getHeaderView(0).findViewById(R.id.changeCompanyLayout)
        ivChangeCompanyArrow = binding.navigationView.getHeaderView(0).findViewById(R.id.ivChangeCompanyArrow)
        dividerView = binding.navigationView.getHeaderView(0).findViewById(R.id.dividerView)
        manageCompaniesbt = binding.navigationView.getHeaderView(0).findViewById(R.id.manageCompaniesbt)

        val requestOptions: RequestOptions = RequestOptions
            .bitmapTransform(RoundedCorners(16))
        Glide.with(activity)
            .load(R.drawable.vyapar_app_icon)
            .apply(requestOptions)
            .into(companyLogo);
    }

    fun onClick() {

        changeCompanyLayout.setOnClickListener {

            ivChangeCompanyArrow.animate().rotationBy(180f).setDuration(1).start()
            if(isCompanyVisible) {

                isCompanyVisible = false
                dividerView.visibility = View.VISIBLE
                manageCompaniesbt.visibility = View.GONE
                binding.navigationView.menu.clear()
                binding.navigationView.inflateMenu(R.menu.home_nav_drawer_menu)
                binding.navigationView.menu.getItem(0).isChecked = true;
            }
            else {
                isCompanyVisible = true
                binding.navigationView.menu.clear()
                val menu: Menu = binding.navigationView.menu

                /*//todo Add Account
                menu.add(0, 111, 0, "Add Account")
                menu.getItem(0).setIcon(R.drawable.ic_sales)*/

                dividerView.visibility = View.GONE
                manageCompaniesbt.visibility = View.VISIBLE

                menu.add("Company One")
                menu.getItem(0).setIcon(R.drawable.ic_companies)
                menu.getItem(0).setOnMenuItemClickListener {

                    globalClass.toastshort(it.toString())
                    true
                }

                menu.add("Company Two")
                menu.getItem(1).setIcon(R.drawable.ic_companies)
                menu.getItem(1).setOnMenuItemClickListener {

                    globalClass.toastshort(it.toString())
                    true
                }

                menu.add("Company Three")
                menu.getItem(2).setIcon(R.drawable.ic_companies)
                menu.getItem(2).setOnMenuItemClickListener {

                    globalClass.toastshort(it.toString())
                    true
                }

                binding.navigationView.menu.getItem(1).isChecked = true;
            }
        }
    }

    fun showHideManageCompanies(menu: String) {
        if(menu == resources.getString(R.string.dashboard)) {
            changeCompanyLayout.visibility = View.VISIBLE
        }
        else {
            changeCompanyLayout.visibility = View.GONE
        }
    }

    fun closeNavDrawer() {
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

    //    todo for expand/collapse menu
    override fun onSupportNavigateUp(): Boolean {

        val navController = findNavController(R.id.fragment)
        return navController.navigateUp(appBarConfiguration)||super.onSupportNavigateUp()
    }

    override fun onResume() {
        super.onResume()

        navController.addOnDestinationChangedListener(listener)
    }

    override fun onPause() {
        super.onPause()

        navController.removeOnDestinationChangedListener(listener)
    }

    override fun onBackPressed() {

        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            closeNavDrawer()
        }
        else {
            super.onBackPressed()
        }
    }
}