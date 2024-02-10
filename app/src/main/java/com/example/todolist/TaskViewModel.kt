package com.example.todolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import db.MyApplication
import db.Task

class TaskViewModel: ViewModel() {
    private val repo = MyApplication.getApp().repo
    private val _listState = MutableLiveData<ListState>(ListState.EmptyList)
    val listState: LiveData<ListState> = _listState
    private val observer = Observer<List<Task>> {
        _listState.postValue(ListState.UpdatedList(list = it))
    }
    init {
        repo.getAllTasks().observeForever(observer)
    }
    fun addTask(title:String, description:String){
        repo.addTask(Task(title = title, description = description))
    }
    fun removeTask(task: Task){
        repo.removeTask(task)
    }
    fun updateTask(task: Task){
        repo.updateTask(task)
    }
    override fun onCleared() {
        repo.getAllTasks().removeObserver(observer)
        super.onCleared()
    }
    sealed class ListState {
        object EmptyList:ListState()
        class UpdatedList(val list:List<Task>):ListState()
    }
}