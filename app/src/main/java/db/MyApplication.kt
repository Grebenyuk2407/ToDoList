package db

import android.app.Application
import androidx.room.Room

class MyApplication : Application() {
    lateinit var repo:TaskRepository
    override fun onCreate() {
        super.onCreate()
        instance = this
        val db = Room.databaseBuilder(this, TaskDatabase::class.java, "employee_database").build()
        repo = TaskRepository(db)
    }
    companion object {
        private lateinit var instance:MyApplication
        fun getApp() = instance
    }
}