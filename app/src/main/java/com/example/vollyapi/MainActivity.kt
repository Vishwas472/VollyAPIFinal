package com.example.vollyapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.apiapplication.APIAdapter
import com.example.vollyapi.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    lateinit var Binding : ActivityMainBinding
    var url = "https://jsonplaceholder.typicode.com/posts"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(Binding.root)
        initviwe()
    }

    private fun initviwe() {
        Binding.BtnGetdata1.setOnClickListener {

            getdata()
        }
    }

    private fun getdata() {
        var requestQueue = Volley.newRequestQueue(this)

        var request : StringRequest = StringRequest(
            Request.Method.GET,url,object : Response.Listener<String>{
            override fun onResponse(response: String?) {

                var token = object : TypeToken<List<response2Item>>(){}.type
                var result : List<response2Item> = Gson().fromJson(response,token)

                var adapter : APIAdapter = APIAdapter(this@MainActivity,result) {

                        id: Int ->
                    var I = Intent(this@MainActivity, DisplayDataActivity::class.java)
                    I.putExtra("id", id)
                    startActivity(I)

                }
                var manager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL,false)
                Binding.rcv1.layoutManager = manager
                Binding.rcv1.adapter = adapter
            }

        },
            Response.ErrorListener {

                Log.e("TAG", "getdata: "+ it )
            }

        )

        requestQueue.add(request)
    }
}