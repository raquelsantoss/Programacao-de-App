package lln.studeo.reminder.db

import androidx.lifecycle.LiveData
import androidx.room.*
import lln.studeo.reminder.entity.Descriptive

@Dao
interface DescriptiveDao {
    @Query("SELECT * from descriptive")
    fun getAll(): LiveData<List<Descriptive>>

    @Query("SELECT * FROM descriptive WHERE id = :id ")
    fun getById(id: String): Descriptive

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(descriptive: Descriptive)

    @Delete
    fun delete(descriptive: Descriptive)

    @Query("UPDATE descriptive SET title =:title, description =:description WHERE id =:id")
    fun update(id: Long, title: String, description: String)
}
