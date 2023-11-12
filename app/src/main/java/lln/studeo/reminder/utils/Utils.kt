package lln.studeo.reminder.utils

import lln.studeo.reminder.R
import lln.studeo.reminder.model.Descript
import java.time.YearMonth

private typealias College = Descript.TypeTheme

fun generateColleges(): List<Descript> {
    val list = mutableListOf<Descript>()
    val currentMonth = YearMonth.now()

    val currentMonth17 = currentMonth.atDay(17)
    list.add(
        Descript(
            currentMonth17.atTime(14, 0),
            College("Lagos", "LOS"),
            College("Abuja", "ABV"),
            R.color.colorAccent
        )
    )
    list.add(
        Descript(
            currentMonth17.atTime(21, 30),
            College("Enugu", "ENU"),
            College("Owerri", "QOW"),
            R.color.colorAccent
        )
    )

    val currentMonth22 = currentMonth.atDay(22)
    list.add(
        Descript(
            currentMonth22.atTime(13, 20),
            College("Ibadan", "IBA"),
            College("Benin", "BNI"),
            R.color.colorAccent
        )
    )
    list.add(
        Descript(
            currentMonth22.atTime(17, 40),
            College("Sokoto", "SKO"),
            College("Ilorin", "ILR"),
            R.color.colorAccent
        )
    )

    list.add(
        Descript(
            currentMonth.atDay(3).atTime(20, 0),
            College("Makurdi", "MDI"),
            College("Calabar", "CBQ"),
            R.color.colorAccent
        )
    )

    list.add(
        Descript(
            currentMonth.atDay(12).atTime(18, 15),
            College("Kaduna", "KAD"),
            College("Jos", "JOS"),
            R.color.colorAccent
        )
    )

    val nextMonth13 = currentMonth.plusMonths(1).atDay(13)
    list.add(
        Descript(
            nextMonth13.atTime(7, 30),
            College("Kano", "KAN"),
            College("Akure", "AKR"),
            R.color.colorAccent
        )
    )
    list.add(
        Descript(
            nextMonth13.atTime(10, 50),
            College("Minna", "MXJ"),
            College("Zaria", "ZAR"),
            R.color.colorAccent
        )
    )

    list.add(
        Descript(
            currentMonth.minusMonths(1).atDay(9).atTime(20, 15),
            College("Asaba", "ABB"),
            College("Port Harcourt", "PHC"),
            R.color.colorAccent
        )
    )

    return list
}
