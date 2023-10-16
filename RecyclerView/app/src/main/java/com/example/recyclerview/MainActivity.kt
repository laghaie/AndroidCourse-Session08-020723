package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.data.PhotoListAdapter
import com.example.recyclerview.data.PlaceListAdapter
import com.example.recyclerview.model.Photo
import com.example.recyclerview.model.Place
import com.example.recyclerview.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    var countryList: ArrayList<Place>? = null
    var displayList = ArrayList<Place>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    var photoList = ArrayList<Photo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar()

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)

        /*countryList = ArrayList()

        val countryNameList: Array<String> = arrayOf(
            "Canada", "Iran", "USA", "Columbia", "Spain", "Peru", "Italy", "India"
        )
        val citiesNameList: Array<String> = arrayOf(
            "Ottawa", "Tehran", "Washington", "Bogota", "Madrid", "Lima", "Rome", "Delhi"
        )

        for (i in 0..7) {
            val place = Place().apply {
                countryName = countryNameList[i]
                cityName = citiesNameList[i]
            }
            countryList!!.add(place)
        }

        var adapter: PlaceListAdapter?
        var linearLayoutManager: RecyclerView.LayoutManager?

        linearLayoutManager = LinearLayoutManager(this)
        adapter = PlaceListAdapter(displayList, this)

        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter

        displayList.addAll(countryList!!)
        adapter.notifyDataSetChanged()*/

        val adapter = PhotoListAdapter(photoList, this)
        val layoutManager = LinearLayoutManager(this)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService = retrofit.create(ApiService::class.java)
        val call = apiService.getPhotos()
        progressBar.visibility = View.VISIBLE
        call.enqueue(object : Callback<List<Photo>> {
            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                if (response.isSuccessful) {
                    photoList.addAll(response.body() as ArrayList<Photo>)
                    adapter.notifyDataSetChanged()
                    progressBar.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {

            }

        })

    }

    private fun setupToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setOnMenuItemClickListener { item ->
            when (item!!.itemId) {
                R.id.exit -> {
                    finish()
                    true
                }

                R.id.search -> {
                    true
                }

                else -> false
            }
        }
    }

    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val menuItem = menu!!.findItem(R.id.search)
        if (menuItem != null) {
            val searchView = menuItem.actionView as SearchView

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText!!.isNotEmpty()) {
                        displayList.clear()
                        val search = newText.lowercase()
                        countryList!!.forEach {
                            if (it.countryName!!.lowercase().contains(search)) {
                                displayList.add(it)
                            }
                            recyclerView.adapter!!.notifyDataSetChanged()
                        }
                    } else {
                        displayList.clear()
                        displayList.addAll(countryList!!)
                        recyclerView.adapter!!.notifyDataSetChanged()
                    }
                    return true
                }
            })
        }

        return super.onCreateOptionsMenu(menu)
    }*/
}