package com.unknownadii.injecteat.Fragments

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.quotes.spot.quotmotiv.DataBase.AppointmentData
import com.quotes.spot.quotmotiv.DataBase.DoctersDatabase
import com.unknownadii.injecteat.R
import com.unknownadii.injecteat.Utils.UtilService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class DocterScheduleFragment : Fragment(R.layout.fragment_docter_schedule) {
    val myCalendar: Calendar = Calendar.getInstance()
    lateinit var editTextDate: TextView
    lateinit var editTextTime: TextView
    private lateinit var dialog: Dialog
    lateinit var dateAppointment: String
    lateinit var timeAppointment: String
    private lateinit var database: DoctersDatabase

    private val timePickerDialogListener: TimePickerDialog.OnTimeSetListener =
        object : TimePickerDialog.OnTimeSetListener {
            override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {

                // logic to properly handle
                // the picked timings by user
                val formattedTime: String = when {
                    hourOfDay == 0 -> {
                        if (minute < 10) {
                            "${hourOfDay + 12}:0${minute} am"
                        } else {
                            "${hourOfDay + 12}:${minute} am"
                        }
                    }
                    hourOfDay > 12 -> {
                        if (minute < 10) {
                            "${hourOfDay - 12}:0${minute} pm"
                        } else {
                            "${hourOfDay - 12}:${minute} pm"
                        }
                    }
                    hourOfDay == 12 -> {
                        if (minute < 10) {
                            "${hourOfDay}:0${minute} pm"
                        } else {
                            "${hourOfDay}:${minute} pm"
                        }
                    }
                    else -> {
                        if (minute < 10) {
                            "${hourOfDay}:${minute} am"
                        } else {
                            "${hourOfDay}:${minute} am"
                        }
                    }
                }

                editTextTime.text = formattedTime
            }
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editTextDate = view.findViewById(R.id.etDateDocter)
        editTextTime = view.findViewById(R.id.etTimeDocter)
        val ivDate = view.findViewById<ImageView>(R.id.ivDateDocter)
        val ivTime = view.findViewById<ImageView>(R.id.ivTimeDocter)
        val etDate = view.findViewById<TextView>(R.id.etDateDocter)
        val etTime = view.findViewById<TextView>(R.id.etTimeDocter)
        val btnBook = view.findViewById<Button>(R.id.bookAppointmentDocter)

        database = Room.databaseBuilder(
            requireActivity(),
            DoctersDatabase::class.java, "AppointmentDocters"
        ).build()

        val date =
            OnDateSetListener { view, year, month, day ->
                myCalendar[Calendar.YEAR] = year
                myCalendar[Calendar.MONTH] = month
                myCalendar[Calendar.DAY_OF_MONTH] = day
                updateLabel()
            }
        ivDate.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                date,
                myCalendar[Calendar.YEAR],
                myCalendar[Calendar.MONTH],
                myCalendar[Calendar.DAY_OF_MONTH]
            ).show()
        }

        ivTime.setOnClickListener {
            val timePicker: TimePickerDialog = TimePickerDialog(
                // pass the Context
                requireContext(),
                // listener to perform task
                // when time is picked
                timePickerDialogListener,
                // default hour when the time picker
                // dialog is opened
                12,
                // default minute when the time picker
                // dialog is opened
                10,
                // 24 hours time picker is
                // false (varies according to the region)
                false
            )

            // then after building the timepicker
            // dialog show the dialog to user
            timePicker.show()
        }

        btnBook.setOnClickListener {
            val utils = UtilService()
            if (!etDate.text.isNullOrBlank()) {
                if (!etTime.text.isNullOrBlank()) {
                    timeAppointment = etTime.text.toString()
                    dateAppointment = etDate.text.toString()
                    showDialog(it)
                } else {
                    utils.showSnackBar(it, "Please enter time")
                }
            } else {
                utils.showSnackBar(it, "Please enter Date")
            }
        }

    }

    private fun showDialog(view: View) {
        dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_for_appointment)
        val confirm = dialog.findViewById<Button>(R.id.confirmAppointmentDocter)
        val cancel = dialog.findViewById<Button>(R.id.cancelAppointmentDocter)

        val date = dialog.findViewById<TextView>(R.id.dateDocterAppointmentDialog)
        val time = dialog.findViewById<TextView>(R.id.timeDocterAppointmentDialog)

        date.text = dateAppointment
        time.text = timeAppointment

        confirm.setOnClickListener {
            bookAppointment()
            dialog.dismiss()
        }
        cancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun bookAppointment() {
        /*
        GlobalScope.launch {
            database.doctersDAO().insertAppointmentDocters(
                AppointmentData(
                    1,
                    "Aditya", "CardioLogist", dateAppointment,
                    timeAppointment
                )
            )
        }

         */
        Toast.makeText(requireContext(), "Appointment Booked", Toast.LENGTH_SHORT).show()
    }

    private fun updateLabel() {
        val myFormat = "MM/dd/yy"
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        editTextDate.setText(dateFormat.format(myCalendar.time))
    }

}