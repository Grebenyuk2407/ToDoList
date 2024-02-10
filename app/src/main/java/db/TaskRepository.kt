package db

import java.util.concurrent.Executors

class TaskRepository(private val database:TaskDatabase) {
    private val executor = Executors.newSingleThreadExecutor()
    fun getAllTasks() = database.taskDao().getAllTasks()
    fun addTask(task: Task) {
        executor.execute { database.taskDao().insertTask(task) }
    }
    fun removeTask(task: Task){
        executor.execute { database.taskDao().deleteTask(task) }
    }

    fun updateTask(task: Task){
        executor.execute { database.taskDao().updateTask(task) }
    }
}