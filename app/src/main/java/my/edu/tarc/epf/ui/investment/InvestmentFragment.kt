package my.edu.tarc.epf.ui.investment

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import my.edu.tarc.epf.databinding.FragmentInvestmentBinding
import java.util.*
import java.util.Calendar;

/**
 * A simple [Fragment] subclass.
 * Use the [InvestmentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InvestmentFragment : Fragment() {
    private var _binding: FragmentInvestmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInvestmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonDOB.setOnClickListener {
            val dateDialogFragment = DateDialogFragment {
                year, month, day -> onDateSelected(year, month, day)
            }
            dateDialogFragment.show(parentFragmentManager, "DatePicker")
        }

        binding.buttonCalculate.setOnClickListener {

        }

        binding.buttonReset.setOnClickListener {

        }
    }

    private fun onDateSelected(year: Int, month: Int, day: Int) {
        binding.buttonDOB.text = String.format("%02d/%02d/%4d", day, month + 1, year)

        // Set year, month, day to the module level variable
        with(calendar) {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month)
            set(Calendar.DAY_OF_MONTH, day)
        }

        val age = dayBetween(calendar, Calendar.getInstance()) / 365
        binding.textViewAge.text = age.toString()
    }

    fun dayBetween(startDate: Calendar, endDate: Calendar): Long {
        val date = startDate.clone() as Calendar
        var daysBetween: Long = 0
        while (date.before(endDate)) {
            date.add(Calendar.DAY_OF_MONTH, 1)
            daysBetween++
        }
        return daysBetween
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    class DateDialogFragment(val dateSetListener: (year: Int, month: Int, day: Int) -> Unit): DialogFragment(), DatePickerDialog.OnDateSetListener {
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            return DatePickerDialog(requireContext(), this, year, month, day)
        }

        override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
            dateSetListener(year, month, day)
        }
    }
}
