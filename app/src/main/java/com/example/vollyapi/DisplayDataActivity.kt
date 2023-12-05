package com.example.vollyapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.vollyapi.databinding.ActivityDisplayDataBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DisplayDataActivity : AppCompatActivity() {
    lateinit var Binding : ActivityDisplayDataBinding
    var id : Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        Binding = ActivityDisplayDataBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(Binding.root)

        initview()    }

    private fun initview() {
        id = intent.getIntExtra("id",id)
        var url = "https://jsonplaceholder.typicode.com/posts/$id"
        var requestQueue = Volley.newRequestQueue(this)

        var request : StringRequest = StringRequest(Request.Method.GET,url,object : Response.Listener<String>{

            override fun onResponse(response: String?){

                var token = object : TypeToken<response2Item>(){}.type
                var result : response2Item = Gson().fromJson(response,token)

                Binding.txttitle.text = result.title.toString()
                Binding.txtbody.text = result.body.toString()
                Binding.txtid.text = result.id.toString()
                Binding.txtuserId.text = result.userId.toString()
            }
        }, Response.ErrorListener {
            Log.e("TAG","error: ")

        })
        requestQueue.add(request)

    }
}