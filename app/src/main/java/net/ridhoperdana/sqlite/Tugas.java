package net.ridhoperdana.sqlite;
//package net.ridhoperdana.sqlitedb;
/**
 * Created by RIDHO on 12/5/2015.
 */
public class Tugas {
    // Labels table name
    public static final String TABLE = "Tugas";

    // Labels Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_nama = "nama";
    public static final String KEY_tanggalDikasih = "tanggalDikasih";
    public static final String KEY_tanggalDikumpul = "tanggalDikumpul";
    public static final String KEY_waktuDikasih = "waktuDikasih";
    public static final String KEY_waktuDikumpul = "waktuDikumpul";
    public static final String KEY_kompleksitas = "kompleksitas";
    public static final String KEY_status = "status";

    // property help us to keep data
    public int id_tugas;
    public String nama;
    public String tanggalDikasih;
    public String tanggalDikumpul;
    public String waktuDikasih;
    public String waktuDikumpul;
    public int kompleksitas;
    public int status;

    public int getId_tugas() {
        return id_tugas;
    }

    public String getNama() {
        return nama;
    }

    public String getTanggalDikasih() {
        return tanggalDikasih;
    }

    public String getTanggalDikumpul() {
        return tanggalDikumpul;
    }

    public String getWaktuDikasih() {
        return waktuDikasih;
    }

    public String getWaktuDikumpul() {
        return waktuDikumpul;
    }

    public int getKompleksitas() {
        return kompleksitas;
    }

    public int getStatus() {
        return status;
    }

    public void setId_tugas(int id_tugas) {
        this.id_tugas = id_tugas;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setTanggalDikasih(String tanggalDikasih) {
        this.tanggalDikasih = tanggalDikasih;
    }

    public void setTanggalDikumpul(String tanggalDikumpul) {
        this.tanggalDikumpul = tanggalDikumpul;
    }

    public void setWaktuDikasih(String waktuDikasih) {
        this.waktuDikasih = waktuDikasih;
    }

    public void setWaktuDikumpul(String waktuDikumpul) {
        this.waktuDikumpul = waktuDikumpul;
    }

    public void setKompleksitas(int kompleksitas) {
        this.kompleksitas = kompleksitas;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
