package com.example.artbook.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.artbook.local.dao.ArtDao
import com.example.artbook.local.entity.ArtModel

/**
 * Created by AralBenli on 25.04.2023.
 */

@Database(entities = [ArtModel::class], version = 1)
abstract class ArtDatabase : RoomDatabase() {
    abstract fun artDao() : ArtDao

}