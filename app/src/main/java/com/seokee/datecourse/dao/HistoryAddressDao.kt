package com.seokee.datecourse.dao

import androidx.room.*
import com.seokee.datecourse.model.HistoryAddress


@Dao
interface HistoryAddressDao {
    @Query("select * from HistoryAddress")
    fun getAll(): List<HistoryAddress>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(historyAddress: HistoryAddress)

    @Delete
    fun delete(historyAddress: HistoryAddress)
}