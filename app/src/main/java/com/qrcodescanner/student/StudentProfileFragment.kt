package com.qrcodescanner.student

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.qrcodescanner.R
import com.qrcodescanner.model.UserTypeEnum
import com.qrcodescanner.preferences.QRCodeScannerPreferences
import kotlinx.android.synthetic.main.fragment_student_profile.*

class StudentProfileFragment : Fragment() {

    private lateinit var mPreferences: QRCodeScannerPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_student_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPreferences = QRCodeScannerPreferences.getInstance(requireContext())
        student_profile_generate_qr_code_button.setOnClickListener {
            mPreferences.setUserType(UserTypeEnum.STUDENT.type)
            mPreferences.setUser(getText())
            generateQRCode(mPreferences.getUser()!!)
        }
    }

    private fun getText(): String {
        val name = student_profile_name.text ?: ""
        val middleName = student_profile_middle_name.text ?: ""
        val surname = student_profile_surname.text ?: ""
        val documentId = student_profile_document_id.text ?: ""
        return Gson().toJson(
            User(
                name = name.toString(),
                middleName = middleName.toString(),
                surname = surname.toString(),
                documentId = documentId.toString(),
                type = UserTypeEnum.STUDENT.type
            )
        )
    }

    private fun generateQRCode(text: String) {
        val multiFormatWriter: MultiFormatWriter = MultiFormatWriter()
        try {
            val bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 250, 250)
            val barcodeEncoder = BarcodeEncoder()
            val bitmap = barcodeEncoder.createBitmap(bitMatrix)
            student_profile_qr_code_image_view.setImageBitmap(bitmap)
        } catch (e: WriterException) {
            e.printStackTrace()
        }
    }

    companion object {
        fun newInstance() = StudentProfileFragment()
    }

}
