package drawable

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_NAME = "bookLibrary"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "book"
        private const val COLUMN_ID = "id"
        private const val COLUMN_BOOK_NAME = "book_name"
        private const val COLUMN_AUTHOR = "author"
        private const val COLUMN_PUBLISHER = "publisher"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_BOOK_NAME TEXT, $COLUMN_AUTHOR  TEXT, $COLUMN_PUBLISHER TEXT)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTable = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTable)
        onCreate(db)
    }

    fun CreateBook (books: bookModel) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_BOOK_NAME, books.book_name)
            put(COLUMN_PUBLISHER, books.publisher)
            put(COLUMN_AUTHOR, books.author)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getAllBooks (): List<bookModel> {
        val bookList = mutableListOf<bookModel>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BOOK_NAME))
            val publisher = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PUBLISHER))
            val author = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_AUTHOR))

            val books = bookModel(id, name, publisher, author)
            bookList.add(books)

        }
        cursor.close()
        db.close()
        return bookList
    }

}