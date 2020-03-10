package com.qrcodescanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qrcodescanner.lecturer.LecturerRegistrationFragment
import com.qrcodescanner.student.StudentProfileFragment
import kotlinx.android.synthetic.main.fragment_user_type.*

class UserTypeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_type, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        user_type_student_button.setOnClickListener {
            (activity as MainActivity).showFragment(StudentProfileFragment.newInstance())
        }
        user_type_lecturer_button.setOnClickListener {
            (activity as MainActivity).showFragment(LecturerRegistrationFragment.newInstance())
        }
    }

    companion object {
        fun newInstance() = UserTypeFragment()
    }
}
