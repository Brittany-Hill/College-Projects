package com.example.petheart

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Memory(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    var name: String = "",
    var description: String = "",
    var date: Date = Date(),
    var favorite: Boolean = false
) {
    val photoFileName
        get() = "IMG_$id.jpg"
}