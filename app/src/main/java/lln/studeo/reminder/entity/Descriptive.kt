package lln.studeo.reminder.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime

@Parcelize
@Entity(tableName = "descriptive")
data class Descriptive(
    @ColumnInfo(name = "title") var name: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "module") var module: String,
    @ColumnInfo(name = "times") var times: String,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long = 0
) : Parcelable
