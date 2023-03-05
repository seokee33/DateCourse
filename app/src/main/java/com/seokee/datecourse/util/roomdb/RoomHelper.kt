package com.seokee.datecourse.util.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.seokee.datecourse.dao.BookmarkAddressDao
import com.seokee.datecourse.dao.HistoryAddressDao
import com.seokee.datecourse.model.BookmarkAddress
import com.seokee.datecourse.model.HistoryAddress

@Database(entities = [HistoryAddress::class, BookmarkAddress::class], version = 1, exportSchema = false)
abstract class RoomHelper: RoomDatabase() {
    abstract fun historyAddressDao(): HistoryAddressDao

    abstract fun bookmarkAddressDao(): BookmarkAddressDao
}