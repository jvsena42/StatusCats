package com.bulletapps.statuscats.activity

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bulletapps.statuscats.R
import com.bulletapps.statuscats.databinding.ActivityMainBinding
import com.bulletapps.statuscats.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var frontAnim:AnimatorSet
    private lateinit var backAnim:AnimatorSet
    private lateinit var mViewModel: MainActivityViewModel
    var isFront = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        mViewModel.binding = binding

        initAnimation()
        getImages()
        observe()
    }

    private fun initAnimation(){
        //Modify the camera scale
        val scale = applicationContext.resources.displayMetrics.density
        binding.cvFront.cameraDistance = 8000*scale
        binding.cvBack.cameraDistance = 8000*scale

        //Set de front animation
        frontAnim = AnimatorInflater.loadAnimator(applicationContext, R.animator.front_animator) as AnimatorSet
        backAnim = AnimatorInflater.loadAnimator(applicationContext, R.animator.back_animator) as AnimatorSet

        //Event Listener
        binding.btFlip.setOnClickListener {
            if (isFront){
                frontAnim.setTarget(binding.cvFront)
                backAnim.setTarget(binding.cvBack)
                frontAnim.start()
                backAnim.start()
                isFront = false
            }else{
                frontAnim.setTarget(binding.cvBack)
                backAnim.setTarget(binding.cvFront)
                backAnim.start()
                frontAnim.start()
                isFront = true
            }
        }
    }

    private fun getImages(){
        mViewModel.getPhoto("200")
    }

    private fun observe(){
        mViewModel.catPhoto.observe(this, Observer {
            if (!it.success) {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}