package lln.studeo.reminder.model

enum class DayOwner {
    PREVIOUS_MONTH,
    THIS_MONTH,
    NEXT_MONTH
}

enum class OutDateStyle {
    END_OF_ROW,
    END_OF_GRID,
    NONE
}

enum class InDateStyle {
    ALL_MONTHS,
    FIRST_MONTH,
    NONE
}

enum class ScrollMode {
    CONTINUOUS,
    PAGED
}
