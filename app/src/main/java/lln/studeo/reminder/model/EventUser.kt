package lln.studeo.reminder.model

import java.time.LocalDateTime

data class EventUser(
    val time: String,
    val title: String,
    val description: String,
    val module: String
)
