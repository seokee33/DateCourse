package com.seokee.datecourse.dao

import androidx.room.Dao
import androidx.room.Query
import com.seokee.datecourse.model.HistoryAddress

@Dao
interface BookmarkAddressDao {
    @Query("SELECT * FROM BookmarkAddress")
    fun getAll(): List<HistoryAddress>
}