package com.example.todolist

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: TaskViewModel by viewModels()
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = TaskAdapter(viewModel)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }

        adapter.attachSwipeToDelete(binding.recyclerView)

        viewModel.listState.observe(this, Observer { state ->
            when (state) {
                is TaskViewModel.ListState.EmptyList -> {

                }
                is TaskViewModel.ListState.UpdatedList -> {
                    adapter.submitList(state.list)
                }
            }
        })

        binding.btnAddTask.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val description = binding.etDescription.text.toString()
            if (title.isNotBlank()) {
                viewModel.addTask(title, description)
                binding.etTitle.text.clear()
                binding.etDescription.text.clear()
            }
        }
    }
}

