package com.example.viewtest

import android.app.ActionBar
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.viewtest.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL= "Your_API"
class MainActivity : AppCompatActivity() {


    lateinit var myAdapter: MyAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#201b40")))



        binding.recyclerviewUsers.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerviewUsers.layoutManager =linearLayoutManager


       getMyData();
    }

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build().create(ApiInterface::class.java)


        val retrofitData = retrofitBuilder.getData()
        retrofitData.enqueue(object : Callback<List<MyDataItem>?> {
            override fun onResponse(
                    call: Call<List<MyDataItem>?>,
                    response: Response<List<MyDataItem>?>
            ) {
                val responseBody = response.body()!!


                myAdapter = MyAdapter(baseContext, responseBody)
                myAdapter.notifyDataSetChanged()
                binding.recyclerviewUsers.adapter = myAdapter

                myAdapter.setOnItemClickListner(object :MyAdapter.onItemClickListner{
                    override fun onItemClick(position: Int) {

                       // Toast.makeText(this@MainActivity,"clicked $position",Toast.LENGTH_SHORT).show()
                       // Log.d("main",myAdapter.userList[position].firstname)

                        val intent = Intent(this@MainActivity,Detailed_activity::class.java)
                        intent.putExtra("ima",myAdapter.userList[position].Image)
                        intent.putExtra("name",myAdapter.userList[position].firstname)
                        intent.putExtra("nationality",myAdapter.userList[position].Nationality)
                        intent.putExtra("height",myAdapter.userList[position].Height)
                        intent.putExtra("weight",myAdapter.userList[position].Weight)
                        intent.putExtra("desc",myAdapter.userList[position].description)
                        startActivity(intent)

                    }

                })


            }

            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
                Log.d("MainActivity", "onFailure" + t.message)
            }
        })


    }
}