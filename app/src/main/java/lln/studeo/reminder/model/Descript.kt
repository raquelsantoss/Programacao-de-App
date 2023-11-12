package lln.studeo.reminder.model

import androidx.annotation.ColorRes
import java.time.LocalDateTime

data class Descript(val time: LocalDateTime, val title: TypeTheme, val descpription: TypeTheme, @ColorRes val color: Int) {
    data class TypeTheme(val module: String, val study: String)
}
