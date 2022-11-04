package com.example.watchlater.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.watchlater.MovieReminder

@Dao
interface MovieReminderDAO {
    @Query("select * from movie_reminders")
    fun getMovieReminders(): LiveData<List<MovieReminder>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieReminder(movieReminder: MovieReminder)

    @Query("select * from movie_reminders where reminderDataInput = :date")
    fun getMovieReminderByDate(date: String): LiveData<List<MovieReminder>>

    @Query("delete from movie_reminders where id == :id")
    fun deleteMovieReminderById(id: String)

}

@Database(entities = [MovieReminder::class], version = 1)
abstract class MovieRemindersDatabase : RoomDatabase() {
    abstract val movieReminderDAO: MovieReminderDAO
}

private lateinit var INSTANCE: MovieRemindersDatabase

fun getDatabase(context: Context): MovieRemindersDatabase {
    synchronized(MovieRemindersDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                MovieRemindersDatabase::class.java,
                "movie").build()
        }

    }
    return INSTANCE
}