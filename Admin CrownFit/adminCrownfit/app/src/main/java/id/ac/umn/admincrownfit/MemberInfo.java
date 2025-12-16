package id.ac.umn.admincrownfit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MemberInfo extends AppCompatActivity {

    Button btnrenewal;

    TextView nama, email, title, periode, tgl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_info);
        nama = findViewById(R.id.namaMember);
        email = findViewById(R.id.emailMember);
        title = findViewById(R.id.titleMember);
        periode = findViewById(R.id.memberPeriode);
        tgl = findViewById(R.id.memberTgl);
        btnrenewal = findViewById(R.id.btnrenewal);

        Intent intent = getIntent();
        String names = intent.getStringExtra("memName");
        String emails = intent.getStringExtra("memEmail");
        String date = intent.getStringExtra("memDate");
        String titles = intent.getStringExtra("memTitle");
        String pw = intent.getStringExtra("memPass");

        if(titles.equals("Bronze")) {
            periode.setText("Periode Membership: 1 Bulan");
        }if(titles.equals("Silver")) {
            periode.setText("Periode Membership: 3 Bulan");
        }if(titles.equals("Gold")) {
            periode.setText("Periode Membership: 6 Bulan");
        }

        nama.setText(names);
        email.setText(emails);
        title.setText("Membership Title: " + titles);
        tgl.setText("Tanggal Daftar: " + date);


        btnrenewal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent halamanrenewal = new Intent(MemberInfo.this, RenewalMember.class );
                startActivity(halamanrenewal);
            }
        });
    }
}