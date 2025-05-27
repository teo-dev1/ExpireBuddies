package com.example.expirebuddies.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "food_table")
data class Food(
    @PrimaryKey(autoGenerate = true)
    val id:Int?=0,
    val name: String,
    val expiryDate: String,
    val timestamp: Long/*=System.currentTimeMillis()*/
    )
