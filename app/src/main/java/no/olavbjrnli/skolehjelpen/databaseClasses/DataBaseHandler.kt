package no.olavbjrnli.skolehjelpen.databaseClasses

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

val DATABASE_NAME = "myDB"
val TABLE_NAME = "Users"
val COL_NAME = "name"
val COL_AGE = "age"
val COL_ID = "id"


//Source for this class is https://github.com/kmvignesh/SqliteExample/tree/master/app/src/main/java/com/example/vicky/sqliteexample
class DataBaseHandler(context : Context) : SQLiteOpenHelper (context, DATABASE_NAME, null, 1 ) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME +" (" +
                COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME + " VARCHAR(256)," +
                COL_AGE + " INTEGER)"
        db?.execSQL(createTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
    fun insertData(user : User) {
        val db = this.writableDatabase
        val contentVals = ContentValues()
        contentVals.put(COL_NAME, user.name)
        contentVals.put(COL_AGE, user.age)
        db.insert(TABLE_NAME, null, contentVals)

    }
    fun readData() : MutableList<User> {
        val list : MutableList<User> = ArrayList()

        val db = this.readableDatabase
        val query = "select * from $TABLE_NAME order by ID desc limit 1"
        val result = db.rawQuery(query, null)
        if(result.moveToFirst()) {
            do {
                val user = User()
                user.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                user.name = result.getString(result.getColumnIndex(COL_NAME))
                user.age = result.getString(result.getColumnIndex(COL_AGE)).toInt()
                list.add(user)
            }while (result.moveToNext())
        }
        result.close()
        db.close()
        return list


    }


}
