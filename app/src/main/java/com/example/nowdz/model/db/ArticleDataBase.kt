package com.example.nowdz.model.db

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.nowdz.R
import com.example.nowdz.model.Article
import com.example.nowdz.model.Categories
import com.example.nowdz.model.Converters
import com.example.nowdz.model.Dao.ArticleDao
import com.example.nowdz.model.Dao.CategoryDao

@Database(entities = [Article::class,Categories::class],version = 8)
@TypeConverters(Converters::class)
abstract class ArticleDatebase : RoomDatabase() {
    abstract fun articleDao() : ArticleDao
    abstract fun categoryDao() : CategoryDao

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
            private val categoryDao = db?.categoryDao()
            override fun doInBackground(vararg p0: Unit?) {
                categoryDao?.insert(Categories(R.string.international,1,0))
                categoryDao?.insert(Categories(R.string.algerie,1,0))
                categoryDao?.insert(Categories(R.string.sport,1,0))
                categoryDao?.insert(Categories(R.string.sante,1,0))
                categoryDao?.insert(Categories(R.string.emploi,1,0))
                categoryDao?.insert(Categories(R.string.culture,1,0))
                categoryDao?.insert(Categories(R.string.politics,1,0))
                categoryDao?.insert(Categories(R.string.economie,1,0))
            }

        }

    }

}