package id.ac.umn.admincrownfit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class AdminMain extends AppCompatActivity {

    private Button member;
    private Button admin;
    private Button addAdmin;
    private ImageView qrscan1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        setTitle("Hello, Admin");

        member = (Button) findViewById(R.id.member);
        admin = (Button) findViewById(R.id.admin);
        addAdmin = (Button) findViewById(R.id.addAdmin);
        qrscan1 = (ImageView) findViewById(R.id.qrscan1);

        member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMain.this, MemberList.class);
                startActivity(intent);
            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMain.this, AdminList.class);
                startActivity(intent);
            }
        });

        addAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMain.this, AddAdmin.class);
                startActivity(intent);
            }
        });

        qrscan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMain.this, QRAdmin.class);
                startActivity(intent);
            }
        });

    }
}