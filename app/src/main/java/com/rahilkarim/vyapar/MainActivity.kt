package com.rahilkarim.vyapar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.rahilkarim.vyapar.util.GlobalClass
import com.rahilkarim.vyapar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var tag = "MainActivity"
    private var activity = this

    lateinit var navController: NavController

    lateinit var binding: ActivityMainBinding
    lateinit var globalClass: GlobalClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        setUpNavController()
    }

    private fun init() {
        globalClass = GlobalClass.getInstance(activity)
    }

    private fun setUpNavController() {
        navController = findNavController(R.id.fragment)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->

            globalClass.log(tag, "onDestinationChanged: "+destination.label);
        }
    }
}