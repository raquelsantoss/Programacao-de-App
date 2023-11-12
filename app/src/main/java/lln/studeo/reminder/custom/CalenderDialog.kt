package lln.studeo.reminder.custom

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.textfield.TextInputEditText
import lln.studeo.reminder.R
import lln.studeo.reminder.utils.Constant.Companion.TIME

interface CalenderDialogListener {
    fun onSaveNotes(title: String, description: String, module: String, time: String)
}

class CalenderDialog : DialogFragment() {

    private var toolbar: Toolbar? = null
    lateinit var titleTextInputEditText: TextInputEditText
    lateinit var descriptionTextInputEditText: TextInputEditText
    lateinit var radioGroupModule: RadioGroup
    private lateinit var listener: CalenderDialogListener
    private var time: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppTheme_FullScreenDialog)
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window?.apply {
                setLayout(width, height)
                setWindowAnimations(R.style.AppTheme_Slide)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.calender_dialog, container, false)
        toolbar = view.findViewById(R.id.toolbar)
        titleTextInputEditText = view.findViewById(R.id.titleEditText)
        descriptionTextInputEditText = view.findViewById(R.id.descriptionEditText)
        radioGroupModule = view.findViewById(R.id.radioGroupModule)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar?.apply {
            setNavigationOnClickListener { dismiss() }
            title = "Agendar"
            inflateMenu(R.menu.calender_dialog)
            setOnMenuItemClickListener {
                listener.onSaveNotes(
                    titleTextInputEditText.text.toString(),
                    descriptionTextInputEditText.text.toString(),
                    verifyModule(radioGroupModule),
                    time.toString()
                )
                dismiss()
                true
            }
        }
    }

    private fun verifyModule(radioGroup: RadioGroup): String {
        return when (radioGroup.checkedRadioButtonId) {
            R.id.radioButton51 -> "51"
            R.id.radioButton52 -> "52"
            R.id.radioButton53 -> "53"
            R.id.radioButton54 -> "54"
            else -> "N/A"
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is CalenderDialogListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement CalenderDialogListener")
        }
        arguments?.getInt(TIME)?.let {
            time = it
        }
    }

    companion object {
        const val TAG = "calender_dialog"

        fun newTime(time: Int) = CalenderDialog().apply {
            arguments = Bundle().apply {
                putInt(TIME, time)
            }
        }


        fun display(fragmentManager: FragmentManager?): CalenderDialog {
            val exampleDialog = CalenderDialog()
            exampleDialog.show(fragmentManager!!, TAG)
            return exampleDialog
        }
    }
}