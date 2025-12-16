package id.ac.umn.admincrownfit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class admininfo extends AppCompatActivity {
    private Button editAdmin;
    TextView nama, email, notelp, temptinggal, tempkerja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admininfo);

        nama = findViewById(R.id.namaadmin);
        email = findViewById(R.id.emailadmin);
        notelp = findViewById(R.id.notelpadmin);
        tempkerja = findViewById(R.id.tempatkerjaadmin);
        temptinggal = findViewById(R.id.tempattinggaladmin);
        editAdmin = findViewById(R.id.editAdmin);

        Intent intent = getIntent();
        String namas = intent.getStringExtra("adminName");
        String emails = intent.getStringExtra("adminEmail");
        String notelps = intent.getStringExtra("adminNotelp");
        String tempkerjas = intent.getStringExtra("adminKerja");
        String temptinggals = intent.getStringExtra("adminTinggal");

        nama.setText(namas);
        email.setText("Email: "+emails);
        notelp.setText(notelps);
        tempkerja.setText("Lokasi Kerja: "+tempkerjas);
        temptinggal.setText(temptinggals);

        editAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admininfo.this, AdminEdit.class);
                startActivity(intent);
            }
        });
    }
}