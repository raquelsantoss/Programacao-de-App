package lln.studeo.reminder.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import lln.studeo.reminder.db.DescriptiveRepository
import lln.studeo.reminder.entity.Descriptive
import java.time.LocalDateTime

class CalendarViewModel : ViewModel() {
    var liveDataDescriptive: LiveData<List<Descriptive>>? = null

    fun insertData(context: Context, title: String, description: String, module: String, time: String) {
        DescriptiveRepository.insertData(context, title, description, module, time)
    }

    fun getDescriptiveDetails(context: Context): LiveData<List<Descriptive>>? {
        liveDataDescriptive = DescriptiveRepository.getDescriptiveDetails(context)
        return liveDataDescriptive
    }
}
