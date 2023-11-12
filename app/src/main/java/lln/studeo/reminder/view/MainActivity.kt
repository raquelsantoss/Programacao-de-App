package lln.studeo.reminder.view

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import lln.studeo.reminder.R
import lln.studeo.reminder.adapter.EventListAdapter
import lln.studeo.reminder.custom.CalenderDialog
import lln.studeo.reminder.custom.CalenderDialogListener
import lln.studeo.reminder.databinding.ActivityMainBinding
import lln.studeo.reminder.databinding.CalenderDialogBinding
import lln.studeo.reminder.databinding.Example1CalendarDayBinding
import lln.studeo.reminder.model.CalendarDay
import lln.studeo.reminder.model.DayOwner
import lln.studeo.reminder.model.EventUser
import lln.studeo.reminder.ui.DayBinder
import lln.studeo.reminder.ui.ViewContainer
import lln.studeo.reminder.utils.Constant.Companion.BRAZIL
import lln.studeo.reminder.utils.daysOfWeekFromLocale
import lln.studeo.reminder.utils.generateColleges
import lln.studeo.reminder.utils.setTextColorRes
import lln.studeo.reminder.viewmodel.CalendarViewModel
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle

class MainActivity : AppCompatActivity(), CalenderDialogListener {

    private lateinit var binding: ActivityMainBinding

    private val selectedDates = mutableSetOf<LocalDate>()
    private val monthTitleFormatter = DateTimeFormatter.ofPattern("MMMM").withLocale(BRAZIL)

    //Config Calender
    private val today = LocalDate.now()
    private val currentMonth = YearMonth.now()
    private val startMonth = currentMonth.minusMonths(10)
    private val endMonth = currentMonth.plusMonths(10)
    private val daysOfWeek = daysOfWeekFromLocale()

    lateinit var calendarViewModel: CalendarViewModel
    lateinit var dialogBinding: CalenderDialogBinding
    //private val dialog = CalenderDialog(this@MainActivity)

    private val colleges = generateColleges().groupBy { it.time.toLocalDate() }
    private val fab: FloatingActionButton by lazy { findViewById<FloatingActionButton>(R.id.floating_action_button) }

    private val recyclerView: RecyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerViewEvent) }
    private val adapter: EventListAdapter by lazy { EventListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        dialogBinding = CalenderDialogBinding.inflate(layoutInflater)

        setContentView(binding.root)


        initViewModel()
        configYearsAndMonths()
        configDaysOfWeek()
        configMonths()
        configDays()
        restoreDB()
        initRecyclerView()
        initListener()
    }
    
    private fun initListener() {
        fab.setOnClickListener {
            //openDialog()
        }
    }

    private fun initViewModel() {
        calendarViewModel = ViewModelProvider(this@MainActivity).get(CalendarViewModel::class.java)
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            adapter = this@MainActivity.adapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun showListUsers(list: List<EventUser>) {
        adapter.event = list
    }

    private fun restoreDB() {
        calendarViewModel.getDescriptiveDetails(this)!!.observe(this, Observer {

            if (!it.isNullOrEmpty()) {
                var event = emptyList<EventUser>().toMutableList()

                it.forEachIndexed { index, descriptive ->
                    event.add(index, EventUser(
                        descriptive.times,
                        descriptive.name,
                        descriptive.description,
                        descriptive.module
                    ))
                }
                showListUsers(event)
                recyclerView.adapter = adapter
            } else {
                //binding.tvSaved.text = it[it.size - 1].name
            }
        })
    }

    private fun restoreDBClick(day: CalendarDay) {
        calendarViewModel.getDescriptiveDetails(this)!!.observe(this, Observer {

            if (!it.isNullOrEmpty()) {
                var event = emptyList<EventUser>().toMutableList()

                it.forEachIndexed { index, descriptive ->
                    if (descriptive.times.toString() == day.toString()) {
                        event[index] = EventUser(
                            descriptive.times,
                            descriptive.name,
                            descriptive.description,
                            descriptive.module
                        )
                    }
                }
                showListUsers(event)
                adapter.event = event
                recyclerView.adapter = adapter
            }
        })
    }

    private fun insert(title: String, description: String, module: String, time: String) {
        calendarViewModel.insertData(this@MainActivity, title, description, module, time)
    }

    private fun openDialog() {
        CalenderDialog.display(supportFragmentManager)
    }

    private fun configYearsAndMonths() {
        binding.exOneCalendar.monthScrollListener = {
            binding.exOneYearText.text = it.yearMonth.year.toString()
            binding.exOneMonthText.text = monthTitleFormatter.format(it.yearMonth).capitalize()
        }
    }

    private fun configDaysOfWeek() {
        binding.legendLayout.root.children.forEachIndexed { index, view ->
            (view as TextView).apply {
                text = daysOfWeek[index].getDisplayName(TextStyle.SHORT, BRAZIL).toUpperCase(
                    BRAZIL
                )
                setTextColorRes(R.color.white)
            }
        }
    }

    private fun configMonths() {
        binding.exOneCalendar.setup(startMonth, endMonth, daysOfWeek.first())
        binding.exOneCalendar.scrollToMonth(currentMonth)
    }

    private fun configDays() {
        binding.exOneCalendar.dayBinder = object : DayBinder<DayViewContainer> {
            override fun create(view: View) = DayViewContainer(view, selectedDates, binding)
            override fun bind(container: DayViewContainer, day: CalendarDay) {
                container.day = day
                val textView = container.textView
                textView.text = day.date.dayOfMonth.toString()
                if (day.owner == DayOwner.THIS_MONTH) {
                    when {
                        selectedDates.contains(day.date) -> {
                            //insert(day.day.toString(), "2021")
                            //textView.setTextColorRes(R.color.example_1_bg)
                            //textView.setBackgroundResource(R.drawable.example_1_selected_bg)
                            //openDialog()
                            CalenderDialog.newTime(day.day)
                            openDialog()
                            restoreDBClick(day)
                        }
                        today == day.date -> {
                            //textView.setTextColorRes(R.color.example_1_white)
                            textView.setBackgroundResource(R.drawable.example_1_today_bg)
                        }
                        else -> {
                            //textView.setTextColorRes(R.color.example_1_white)
                            textView.background = null
                        }
                    }


                    val colleges = colleges[day.date]
                    if (colleges != null) {
                        if (colleges.count() == 1) {
                            textView.setBackgroundResource(R.drawable.ring_module_51_studeo)
                        } else {
                            textView.setBackgroundResource(R.drawable.ring_module_52_studeo)
                        }
                    }


                } else {
                    textView.setTextColorRes(R.color.gray)
                    textView.background = null
                }
            }
        }
    }

    private class DayViewContainer(
        view: View,
        selectedDates: MutableSet<LocalDate>,
        binding: ActivityMainBinding
    ) : ViewContainer(view) {
        lateinit var day: CalendarDay
        val textView = Example1CalendarDayBinding.bind(view).exOneDayText

        init {
            view.setOnClickListener {
                if (day.owner == DayOwner.THIS_MONTH) {
                    if (selectedDates.contains(day.date)) {
                        selectedDates.remove(day.date)
                    } else {
                        selectedDates.add(day.date)
                    }
                    binding.exOneCalendar.notifyDayChanged(day)
                }
            }
        }
    }

    override fun onSaveNotes(
        title: String,
        description: String,
        module: String,
        time: String
    ) {
        insert(title, description, module, time)
    }
}