package id.ac.umn.admincrownfit;

import com.google.firebase.database.PropertyName;

public class MemberListHelper {
        private String email, nama, pw, tgl, title;

    public MemberListHelper() {
    }

    public MemberListHelper(String email, String nama, String pw, String tgl, String title) {
        this.email = email;
        this.nama = nama;
        this.pw = pw;
        this.tgl = tgl;
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @PropertyName("nama")
    public String getName() {
        return nama;
    }

    public void setName(String nama) {
        this.nama = nama;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
