package com.qrcodescanner.lecturer

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.qrcodescanner.R
import com.qrcodescanner.model.UserTypeEnum
import com.qrcodescanner.preferences.QRCodeScannerPreferences
import com.qrcodescanner.student.User
import kotlinx.android.synthetic.main.fragment_lecturer_registration.*

class LecturerRegistrationFragment : Fragment() {

    private lateinit var mPreferences: QRCodeScannerPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lecturer_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPreferences = QRCodeScannerPreferences.getInstance(requireContext())
        lecturer_registration_register_button.setOnClickListener {
            saveLecturer()
            startActivity(Intent(activity, BottomNavigationActivity::class.java))
        }
    }

    private fun saveLecturer() {
        val name = lecturer_registration_name.text ?: ""
        val middleName = lecturer_registration_middle_name.text ?: ""
        val surname = lecturer_registration_surname.text ?: ""
        val documentId = lecturer_registration_document_id.text ?: ""

        mPreferences.setUserType(UserTypeEnum.LECTURER.type)
        mPreferences.setUser(
            Gson().toJson(
                User(
                    name = name.toString(),
                    middleName = middleName.toString(),
                    surname = surname.toString(),
                    documentId = documentId.toString(),
                    type = UserTypeEnum.LECTURER.type
                )
            )
        )
    }

    companion object {
        fun newInstance() = LecturerRegistrationFragment()
    }

}
