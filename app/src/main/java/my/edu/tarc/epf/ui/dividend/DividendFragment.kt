package my.edu.tarc.epf.ui.dividend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import my.edu.tarc.epf.R
import my.edu.tarc.epf.databinding.FragmentDividendBinding

/**
 * A simple [Fragment] subclass.
 * Use the [DividendFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DividendFragment : Fragment() {
    private var _binding: FragmentDividendBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDividendBinding.inflate(inflater, container, false)
        return binding.root
    }
}