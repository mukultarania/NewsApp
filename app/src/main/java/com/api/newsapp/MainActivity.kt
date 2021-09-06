package com.api.newsapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NewsItemClicked {

    private lateinit var mAdapter: NewsListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler.layoutManager = LinearLayoutManager(this)
//        fetchData()
//        mAdapter = NewsListAdapter(this)
//        recycler.adapter = mAdapter
        Toast.makeText(this, "sssssssssssssssssssssssss", Toast.LENGTH_SHORT)

    }

    private fun fetchData() {
        val newsArray = ArrayList<News>()
        val url =
            "https://newsapi.org/v2/top-headlines?country=in&apiKey=533a150c941c47f4bff6fe597da02bba"
        val jsonObject = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            {
                val newsJSONArray = it.getJSONArray("articles")
                for (i in 0 until newsJSONArray.length()) {
                    val newJsonObject = newsJSONArray.getJSONObject(i)
                    val news = News(
                        newJsonObject.getString("title"),
                        newJsonObject.getString("author"),
                        newJsonObject.getString("url"),
                        newJsonObject.getString("urlToImage")
                    )
                    newsArray.add(news)
                    Toast.makeText(this, "Working", Toast.LENGTH_SHORT)
                }
                mAdapter.upadateNews(newsArray)

            },
            {
                Toast.makeText(this, "Not Working", Toast.LENGTH_SHORT)
            }
        )
        NewsSingleton.getInstance(this).addToRequestQueue(jsonObject)
    }

    override fun onItemClicked(item: News) {
        TODO("Not yet implemented")
    }
}