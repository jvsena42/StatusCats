package com.bulletapps.statuscats

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.bulletapps.statuscats.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var front_anim:AnimatorSet
    private lateinit var back_anim:AnimatorSet
    var isFront = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //Modify the camera scale
        val scale = applicationContext.resources.displayMetrics.density
        binding.cvFront.cameraDistance = 8000*scale
        binding.cvBack.cameraDistance = 8000*scale

        //Set de front animation
        front_anim = AnimatorInflater.loadAnimator(applicationContext,R.animator.front_animator) as AnimatorSet
        back_anim = AnimatorInflater.loadAnimator(applicationContext,R.animator.back_animator) as AnimatorSet

        //Event Listener
        binding.fabRefresh.setOnClickListener {
            if (isFront){
                front_anim.setTarget(binding.cvFront)
                back_anim.setTarget(binding.cvBack)
                front_anim.start()
                back_anim.start()
                isFront = false
            }else{
                front_anim.setTarget(binding.cvBack)
                back_anim.setTarget(binding.cvFront)
                back_anim.start()
                front_anim.start()
                isFront = true
            }
        }


    }
}