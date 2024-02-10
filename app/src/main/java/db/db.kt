package db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val description: String,
    var completed: Boolean = false

)


@Dao
interface TaskDao{

    @Query("SELECT * FROM tasks")
    fun getAllTasks(): LiveData<List<Task>>

    @Insert
    fun insertTask(task: Task)

    @Delete
    fun deleteTask(task: Task)


    @Update
    fun updateTask(task: Task)
}

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase:RoomDatabase(){
    abstract fun taskDao():TaskDao
}