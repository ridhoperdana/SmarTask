package net.ridhoperdana.sqlite;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditTugas extends ActionBarActivity implements android.view.View.OnClickListener{

    Button btnSave;
    EditText editTextNama;
    EditText editTextDiberikan;
    EditText editTextDikumpulkan;
    EditText waktuDiberikan;
    EditText waktuDikumpulkan;

    private int _id_tugas=0;
    private DatePickerDialog TextDiberikan;
    private DatePickerDialog TextDikumpulkan;
    private SimpleDateFormat dateFormatter;

    private TimePickerDialog waktuDiberikanDialog;
    private TimePickerDialog waktuDikumpulkanDialog;

    private RadioGroup radioGroup;

    private int kesulitan_int;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tugas);
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        btnSave = (Button) findViewById(R.id.tambah_tugas);
        editTextNama = (EditText) findViewById(R.id.judul_tugas);
        editTextDiberikan = (EditText) findViewById(R.id.tanggal_diberikan);
        editTextDiberikan.setInputType(InputType.TYPE_NULL);
        editTextDikumpulkan = (EditText) findViewById(R.id.tanggal_kumpul);
        editTextDikumpulkan.setInputType(InputType.TYPE_NULL);
        waktuDiberikan = (EditText) findViewById(R.id.waktu_diberikan);
        waktuDiberikan.setInputType(InputType.TYPE_NULL);
        waktuDikumpulkan = (EditText) findViewById(R.id.waktu_dikumpulkan);
        waktuDikumpulkan.setInputType(InputType.TYPE_NULL);

        btnSave.setOnClickListener(this);

        _id_tugas =0;
        Intent intent = getIntent();
        _id_tugas = intent.getIntExtra("id_tugas", 0);
        TugasRepo repo = new TugasRepo(this);
        Tugas tugas = new Tugas();
        tugas = repo.getTugasById(_id_tugas);

        setDateTimeField();
        setTime();
        radioButton();

        editTextNama.setText(tugas.nama);
        editTextDikumpulkan.setText(tugas.tanggalDikumpul);
        editTextDiberikan.setText(tugas.tanggalDikasih);
        waktuDiberikan.setText(tugas.waktuDikasih);
        waktuDikumpulkan.setText(tugas.waktuDikumpul);
    }

    private int radioButton()
    {
        radioGroup = (RadioGroup) findViewById(R.id.grupKompleksitas);
        radioGroup.clearCheck();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                String mudah = "Mudah";
                String biasa = "Biasa";
                String sulit = "Sulit";

                if(checkedId > -1)
                {
                    String input  = rb.getText().toString();
                    if(input.equals(mudah))
                    {
                        kesulitan_int = 3;
                    }
                    else if(input.equals(biasa))
                    {
                        kesulitan_int = 2;
                    }
                    else if(input.equals(sulit))
                    {
                        kesulitan_int = 1;
                    }
                }
            }
        });

        return kesulitan_int;
    }

    private void setDateTimeField()
    {
        editTextDiberikan.setOnClickListener(this);
        editTextDikumpulkan.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        int hari = newCalendar.get(Calendar.DAY_OF_MONTH);
        int bulan = newCalendar.get(Calendar.MONTH);
        int tahun = newCalendar.get(Calendar.YEAR);

        TextDiberikan = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                editTextDiberikan.setText(dayOfMonth + "-" + monthOfYear + "-" + year);
            }

        },tahun, bulan, hari);

        TextDikumpulkan = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                editTextDikumpulkan.setText(dayOfMonth + "-" + monthOfYear + "-" + year);
            }

        },tahun, bulan, hari);
    }

    private void setTime()
    {
        waktuDiberikan.setOnClickListener(this);
        waktuDikumpulkan.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        int hour = newCalendar.get(Calendar.HOUR_OF_DAY);
        int minutes = newCalendar.get(Calendar.MINUTE);

        waktuDiberikanDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
//            @Override

            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                waktuDiberikan.setText(hourOfDay + ":" + minute);
            }
        }, hour, minutes, true);

        waktuDikumpulkanDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
//            @Override

            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                waktuDikumpulkan.setText(hourOfDay + ":" + minute);
            }
        }, hour, minutes, true);
    }


    public void onClick(View view) {
        if(view == editTextDiberikan) {
            TextDiberikan.show();
        } else if(view == editTextDikumpulkan) {
            TextDikumpulkan.show();
        }
        if(view == waktuDiberikan) {
            waktuDiberikanDialog.show();
        } else if(view == waktuDikumpulkan) {
            waktuDikumpulkanDialog.show();
        }
        if (view == findViewById(R.id.tambah_tugas)) {
            TugasRepo repo = new TugasRepo(this);
            Tugas tugas = new Tugas();
            tugas.nama = editTextNama.getText().toString();
            tugas.kompleksitas = radioButton();
            tugas.tanggalDikasih = editTextDiberikan.getText().toString();
            tugas.tanggalDikumpul = editTextDikumpulkan.getText().toString();
            tugas.waktuDikasih = waktuDiberikan.getText().toString();
            tugas.waktuDikumpul = waktuDikumpulkan.getText().toString();
            tugas.id_tugas = _id_tugas;

            if (_id_tugas == 0) {
                _id_tugas = repo.insert(tugas);

                Toast.makeText(this, "Tugas sudah dibuat " + tugas.waktuDikumpul + tugas.kompleksitas, Toast.LENGTH_SHORT).show();
                System.out.println(tugas.nama + "-- nama BUAT TUGAS");
                System.out.println(tugas.kompleksitas + "-- kompleksitas");
                System.out.println(tugas.tanggalDikasih + "-- tanggal dikasih");
                System.out.println(tugas.tanggalDikumpul + "-- tanggal dikumpul");
                System.out.println(tugas.waktuDikasih + "-- waktu dikasih");
                System.out.println(tugas.waktuDikumpul + "-- waktu dikumpul");
                finish();
            } else {
                repo.update(tugas);
                Toast.makeText(this, "Deskripsi tugas telah diperbaharui", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}
