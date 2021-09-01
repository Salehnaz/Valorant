package com.example.viewtest

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class Detailed_activity : AppCompatActivity() {








    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_activity2)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#201b40")))
         var pic :ImageView
         var txt :TextView?
         var nati :TextView?
         var hti :TextView?
         var wti :TextView?
         var descr :TextView?

        var gt=intent.getStringExtra("name")
        var ig=intent.getStringExtra("ima")
        var na=intent.getStringExtra("nationality")
        var we=intent.getStringExtra("weight")
        var he=intent.getStringExtra("he")
        var Dsr=intent.getStringExtra("desc")

        txt= findViewById(R.id.d_fname)
        pic= findViewById(R.id.d_pic_id)
        nati=findViewById(R.id.nat)
        hti=findViewById(R.id.ht)
        wti=findViewById(R.id.wt)
        descr=findViewById(R.id.des_id)

        txt.text=gt
        nati.text= na.toString()
        hti.text= he.toString()
        wti.text=we.toString()
        descr.text=Dsr


        Glide.with(this)
            .load(ig)
            .into(pic)



    }
}