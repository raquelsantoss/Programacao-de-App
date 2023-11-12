package lln.studeo.reminder.db

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lln.studeo.reminder.entity.Descriptive
import java.time.LocalDateTime

class DescriptiveRepository {
    companion object {
        var appDatabase: AppDataBase? = null

        var descriptive: LiveData<List<Descriptive>>? = null

        fun initializeDB(context: Context): AppDataBase {
            return AppDataBase.getInstance(context)
        }

        fun insertData(context: Context, title: String, description: String, module: String, time: String) {

            appDatabase = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                val descriptive = Descriptive(title, description, module, time)
                appDatabase!!.descriptiveDao().insert(descriptive)
            }

        }

        fun getDescriptiveDetails(context: Context): LiveData<List<Descriptive>>? {

            appDatabase = initializeDB(context)

            descriptive = appDatabase!!.descriptiveDao().getAll()

            return descriptive
        }

    }
}
