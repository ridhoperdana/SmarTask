package net.ridhoperdana.sqlite;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MainActivity extends ListActivity implements android.view.View.OnClickListener{

    FloatingActionButton tombol_tambah;
    TextView tugas_id;
    EditText tanggalKasih, tanggalKumpul, waktuKasih, waktuKumpul, tugasKompleksitas;
    int _id_tugas;

    Tugas tugas, tugass;

    TugasRepo repo;
    ArrayList<LinkedHashMap<String, String>> studentList;

    @Override
    public void onClick(View view) {
        if (view == findViewById(R.id.tombol_tambah_tugas)){
            Intent intent = new Intent(this,TugasDetil.class);
            intent.putExtra("id_tugas",0);
            startActivity(intent);
        }
        else {
            refreshList();
        }
    }

    public void updateAdapter()
    {
        repo = new TugasRepo(this);
        studentList = repo.getTugasList();
        tugas = new Tugas();
//        tugas = repo.getTugasById(_id_tugas);
        ListView lv = getListView();
        View x = lv.getChildAt(2);

        ArrayList<LinkedHashMap<String, String>> list_semua = repo.getTugasList();
        int count = 0;
        for(LinkedHashMap<String, String> res : list_semua)
        {
            System.out.println("masukin ke perulangan-----ini bisa masuk------");
            if(Integer.parseInt(res.get("status"))==1)
            {
                count++;
            }
        }

        TextView tugas_belum = (TextView)findViewById(R.id.total_tugas);
        tugas_belum.setText(count + "belum dikerjakan");

        if(count==0)
        {
            System.out.println("ini bisa masuk------");
            tugas_belum.setText("Semua tugas sudah dikerjakan");
        }
        else
            tugas_belum.setText(count + " belum dikerjakan");

        SimpleAdapter adapter_checklist = new SimpleAdapter(MainActivity.this, studentList,
                R.layout.view_masuk_tugas, new String[]{"id", "nama", "status", "tanggalDiberikan",
                "tanggalDikumpulkan", "waktuDikasih", "waktuDikumpul", "kompleksitas", "checkbox"}, new int[]{R.id.tugas_id, R.id.tugas_judul, R.id.status_tugas,
                R.id.tanggal_diberikan, R.id.tanggal_kumpul, R.id.waktu_diberikan, R.id.waktu_dikumpulkan, R.id.isi_kompleksitas, R.id.status_kerja});

//        SimpleAdapter.ViewBinder binder = new SimpleAdapter.ViewBinder() {
//            @Override
//            public boolean setViewValue(View view, Object object, String value) {
//
//                if (tugas.status==1)
//                {
//                    CheckBox checkbox_cek = (CheckBox) view.findViewById(R.id.status_kerja);
////                    checkbox_cek.setChecked(true);
//                    //Change color/answer/etc for ricettaTipo
//                    System.out.println("belum dikerjakan" + checkbox_cek.isChecked());
//                }
//                else
//                {
//                    CheckBox checkbox_cek = (CheckBox) view.findViewById(R.id.status_kerja);
////                    checkbox_cek.setChecked(false);
//                    System.out.println("belum dikerjakan" + checkbox_cek.isChecked());
//                }
//                return false;
//            }
//        };
//        adapter_checklist.setViewBinder(binder);
        setListAdapter(adapter_checklist);
    }

    public void kotakCentang(View view) {
        RelativeLayout vwParentRow = (RelativeLayout)view.getParent();
        TextView id_test = (TextView)vwParentRow.getChildAt(0);
        int id_tugas_sementara = Integer.parseInt(id_test.getText().toString());

        TugasRepo repo = new TugasRepo(this);
        Tugas tugas = new Tugas();

        tugass = repo.getTugasById(id_tugas_sementara);
        _id_tugas = id_tugas_sementara;
        tugas.id_tugas = tugass.id_tugas;
        tugas.nama = tugass.nama;
        tugas.waktuDikasih = tugass.waktuDikasih;
        tugas.waktuDikumpul = tugass.waktuDikumpul;
        tugas.tanggalDikasih = tugass.tanggalDikasih;
        tugas.tanggalDikumpul = tugass.tanggalDikumpul;
        tugas.kompleksitas = tugass.kompleksitas;

        tugas.status = tugass.status * -1;
        Log.d("id tugas--->", String.valueOf(tugass.id_tugas));
        Log.d("status nama--->", tugass.nama);
        Log.d("status tugas awal--->", String.valueOf(tugass.status));
        Log.d("status tugas akhir--->", String.valueOf(tugas.status));
        Log.d("tanggalksh tugas--->", String.valueOf(tugass.tanggalDikasih));
        Log.d("tanggalkmpl tugas--->", String.valueOf(tugass.tanggalDikumpul));
        Log.d("wktukasih tugas--->", String.valueOf(tugass.waktuDikasih));
        Log.d("wktukumpul tugas--->", String.valueOf(tugass.waktuDikumpul));
        Log.d("kompleksitas tugas--->", String.valueOf(tugas.kompleksitas));

        repo.update_status(tugas);
        repo.delete(tugas.id_tugas);

        updateAdapter();
    }

    public void detilTugas(View view)
    {
        RelativeLayout vwParentRow = (RelativeLayout)view.getParent();
        tugas_id = (TextView)vwParentRow.getChildAt(0);
        int tugasid = Integer.parseInt(tugas_id.getText().toString());
        Intent intent = new Intent(getApplicationContext(), DetilTugas.class);
        intent.putExtra("id_tugas", tugasid);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        tombol_tambah = (FloatingActionButton) findViewById(R.id.tombol_tambah_tugas);
        tombol_tambah.setOnClickListener(this);

        refreshList();
        updateAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateAdapter();
    }

    public void refreshList()
    {
        repo = new TugasRepo(this);
        studentList =  repo.getTugasList();
        if(studentList.size()!=0) {
            ListView lv = getListView();
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    tugas_id = (TextView) view.findViewById(R.id.tugas_id);

                    waktuKasih = (EditText) view.findViewById(R.id.waktu_diberikan);
                    waktuKumpul = (EditText) view.findViewById(R.id.waktu_dikumpulkan);
                    tanggalKasih = (EditText) view.findViewById(R.id.tanggal_diberikan);
                    tanggalKumpul = (EditText) view.findViewById(R.id.tanggal_kumpul);
                    tugasKompleksitas = (EditText) view.findViewById(R.id.isi_kompleksitas);

                    String tugasid = tugas_id.getText().toString();
                    Intent objIndent = new Intent(getApplicationContext(), TugasDetil.class);
                    objIndent.putExtra("id_tugas", Integer.parseInt(tugasid));
                    startActivity(objIndent);
                    }
                });
                ListAdapter adapter = new SimpleAdapter(MainActivity.this, studentList,
                        R.layout.view_masuk_tugas, new String[]{"id", "nama", "status", "tanggalDiberikan",
                        "tanggalDikumpulkan", "waktuDikasih", "waktuDikumpul", "kompleksitas", "checkbox"}, new int[]{R.id.tugas_id, R.id.tugas_judul, R.id.status_tugas,
                        R.id.tanggal_diberikan, R.id.tanggal_kumpul, R.id.waktu_diberikan, R.id.waktu_dikumpulkan, R.id.isi_kompleksitas, R.id.status_kerja});
                setListAdapter(adapter);
            }else{
            Toast.makeText(this, "Tidak ada tugas", Toast.LENGTH_SHORT).show();
        }
    }
}