package id.ac.umn.admincrownfit;

public class AdminHelper {
    String nama, tempattinggal, tempatkerja, notelp, email;

    public AdminHelper() {
    }

    public AdminHelper(String nama, String tempattinggal, String tempatkerja, String notelp, String email) {
        this.nama = nama;
        this.tempattinggal = tempattinggal;
        this.tempatkerja = tempatkerja;
        this.notelp = notelp;
        this.email = email;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTempattinggal() {
        return tempattinggal;
    }

    public void setTempattinggal(String tempattinggal) {
        this.tempattinggal = tempattinggal;
    }

    public String getTempatkerja() {
        return tempatkerja;
    }

    public void setTempatkerja(String tempatkerja) {
        this.tempatkerja = tempatkerja;
    }

    public String getNotelp() {
        return notelp;
    }

    public void setNotelp(String notelp) {
        this.notelp = notelp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
