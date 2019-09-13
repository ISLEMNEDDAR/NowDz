package com.example.nowdz.model.db

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.nowdz.model.Article
import com.example.nowdz.model.Converters
import com.example.nowdz.model.Dao.ArticleDao

@Database(entities = [Article::class],version = 1)
@TypeConverters(Converters::class)
abstract class ArticleDatebase : RoomDatabase() {
    abstract fun articleDao() : ArticleDao


    companion object{
        private var instance : ArticleDatebase? = null
        fun getInstance(context: Context) : ArticleDatebase?{
            if (instance == null){
                synchronized(ArticleDatebase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ArticleDatebase::class.java, "article_database"
                    )
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build()
                }
            }
            return instance
        }


        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(instance)
                    .execute()
            }
        }


        class PopulateDbAsyncTask(db: ArticleDatebase?) : AsyncTask<Unit, Unit, Unit>() {
            private val articleDao = db?.articleDao()
            override fun doInBackground(vararg p0: Unit?) {


            }

        }

    }

}