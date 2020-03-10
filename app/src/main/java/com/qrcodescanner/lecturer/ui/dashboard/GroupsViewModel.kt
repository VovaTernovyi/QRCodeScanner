package com.qrcodescanner.lecturer.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader


class GroupsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    public fun readCSV(inputStream: InputStream): List<String> {
        val resultList = ArrayList<String>()
        val bufferReader: BufferedReader = BufferedReader(InputStreamReader(inputStream))
        try {
            var csvLine: String
            while (bufferReader.readLine() != null) {
                csvLine = bufferReader.readLine()
                val row: List<String> = csvLine.split(";")
                resultList.add(row.toString())
            }
        } catch (ex: IOException) {
            throw RuntimeException("Error in reading CSV file: $ex")
        } finally {
            try {
                inputStream.close()
            } catch (e: IOException) {
                throw RuntimeException("Error while closing input stream: $e");
            }
        }
        return resultList
    }
}