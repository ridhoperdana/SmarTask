package net.ridhoperdana.sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DetilTugas extends AppCompatActivity {

    Button btnSave;
    EditText editTextNama;
    EditText editTextDiberikan;
    EditText editTextDikumpulkan;
    EditText editTextKompleksitas;
    EditText waktuDiberikan;
    EditText waktuDikumpulkan;
    EditText statusTugas;

    private int _id_tugas;

    public void hapus_tugas(View view) {
        TugasRepo repo = new TugasRepo(this);
        repo.delete(_id_tugas);
        Toast.makeText(this, "Tugas telah dihapus", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void edit_tugas(View view) {
        Intent intent = new Intent(getApplicationContext(), EditTugas.class);
        intent.putExtra("id_tugas", _id_tugas);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil_tugas);

        btnSave = (Button) findViewById(R.id.tambah_tugas);
        editTextNama = (EditText) findViewById(R.id.judul_tugas);

        editTextDiberikan = (EditText) findViewById(R.id.tanggal_diberikan);

        editTextDikumpulkan = (EditText) findViewById(R.id.tanggal_kumpul);

        waktuDiberikan = (EditText) findViewById(R.id.waktu_diberikan);

        waktuDikumpulkan = (EditText) findViewById(R.id.waktu_dikumpulkan);

        editTextKompleksitas = (EditText) findViewById(R.id.isi_kompleksitas);

//        statusTugas = (EditText) findViewById(R.id.status_tugas);

        Intent intent = getIntent();
        _id_tugas = intent.getIntExtra("id_tugas", 0);
        Log.d("id yang diterima >>>>> ", String.valueOf(_id_tugas));
        TugasRepo repo = new TugasRepo(this);
        Tugas tugas = new Tugas();
        tugas = repo.getTugasById(_id_tugas);


        editTextNama.setText(tugas.nama);
        editTextDikumpulkan.setText(tugas.tanggalDikumpul);
        editTextDiberikan.setText(tugas.tanggalDikasih);
        waktuDiberikan.setText(tugas.waktuDikasih);
        waktuDikumpulkan.setText(tugas.waktuDikumpul);
        if(tugas.kompleksitas==0)
        {
            editTextKompleksitas.setText("Sulit");
        }
        else if(tugas.kompleksitas==1)
        {
            editTextKompleksitas.setText("Biasa");
        }
        else if(tugas.kompleksitas==2)
        {
            editTextKompleksitas.setText("Mudah");
        }
//        editTextKompleksitas.setText(String.valueOf(tugas.kompleksitas));
//        statusTugas.setText(String.valueOf(tugas.status));
    }
}
