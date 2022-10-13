package com.example.notlonesomegeorge


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notlonesomegeorge.databinding.ActivityNetWorkBinding
import retrofit2.HttpException
import java.io.IOException



class NetWorkActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNetWorkBinding

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNetWorkBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()

        lifecycleScope.launchWhenCreated {
            binding.NetWorkProgressBar.isVisible = true
            val response = try {
                RetrofitInstance.api.getTodos()
            } catch(e: IOException) {
                Log.e("NetWorkActivity", "IOException, you might not have internet connection")
                binding.NetWorkProgressBar.isVisible = false
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e("NetWorkActivity", "HttpException, unexpected response")
                binding.NetWorkProgressBar.isVisible = false
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body() != null) {
                todoAdapter.todos = response.body()!!
            } else {
                Log.e("NetWorkActivity", "Response not successful")
            }
            binding.NetWorkProgressBar.isVisible = false
        }
    }

    private fun setupRecyclerView() = binding.rvTodos.apply {
        todoAdapter = TodoAdapter()
        adapter = todoAdapter
        layoutManager = LinearLayoutManager(this@NetWorkActivity)
    }
}