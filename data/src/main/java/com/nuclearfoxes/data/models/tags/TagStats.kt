package com.nuclearfoxes.data.models.tags

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class TagStats(
    @PrimaryKey var id:String,
    @ColumnInfo var name:String,
    @ColumnInfo var rateAllTime:Double
    )