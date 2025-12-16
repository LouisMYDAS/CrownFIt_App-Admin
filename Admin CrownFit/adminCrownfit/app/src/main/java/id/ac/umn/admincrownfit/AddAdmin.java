package id.ac.umn.admincrownfit;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddAdmin extends AppCompatActivity {
    Button btnsubmitadmin;
    Intent tambahadmin;
    String passwordasli = "HALLO";
    EditText nama, tempattinggal, tempatkerja, email, notelp;
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);
        btnsubmitadmin = findViewById(R.id.btnsubmitadmin);
        nama = findViewById(R.id.nama);
        tempatkerja = findViewById(R.id.notelpon3);
        email = findViewById(R.id.email4);
        tempattinggal = findViewById(R.id.tempattinggal);
        notelp = findViewById(R.id.notelpon);

        btnsubmitadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
    }

    private void openDialog(){
        final Dialog dialog = new Dialog(AddAdmin.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.toastaddadmin);
        final EditText password = dialog.findViewById(R.id.passwordowner);
        Button btnokdialog = dialog.findViewById(R.id.btnkonfirmasinambahadmin);

        btnokdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.equals(password.getText().toString(),passwordasli)){
                    Toast.makeText(getApplicationContext(), "Welcome Boss", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                    //openinfoadmin();
                    registerAdmin();

                }
                /*((password.getText().toString()) != passwordasli ) {
                    Toast.makeText(getApplicationContext(), "Password Salah", Toast.LENGTH_LONG).show();
                }*/
            }
        });
        dialog.show();
    }

    private void registerAdmin() {
        String emails, name, tmpttgl, tmptkerja, notelpon;
        name = nama.getText().toString();
        emails = email.getText().toString();
        tmpttgl = tempattinggal.getText().toString();
        tmptkerja = tempatkerja.getText().toString();
        notelpon = notelp.getText().toString();


        rootNode = FirebaseDatabase.getInstance("https://tescrossfitfirebaseid-default-rtdb.asia-southeast1.firebasedatabase.app/");
        reference = rootNode.getReference("listadmin");


        String id = "unique"+name;


        if (TextUtils.isEmpty(name)) {
            Toast.makeText(getApplicationContext(),
                            "Please your name!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if (TextUtils.isEmpty(emails)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter email!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if (TextUtils.isEmpty(tmptkerja)) {
            Toast.makeText(getApplicationContext(),
                            "Masukkan tempat kerja!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if (TextUtils.isEmpty(tmpttgl)) {
            Toast.makeText(getApplicationContext(),
                            "Masukkan tempat tinggal!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if (TextUtils.isEmpty(notelpon)) {
            Toast.makeText(getApplicationContext(),
                            "Masukkan nomor telpon!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }

        AdminHelper helperClass = new AdminHelper(name, tmpttgl,tmptkerja, notelpon, emails);
        reference.child(notelpon).setValue(helperClass);
        Toast.makeText(getApplicationContext(),
                        "Registration successful!",
                        Toast.LENGTH_LONG)
                .show();
        Intent intent
                = new Intent(AddAdmin.this,
                AdminMain.class);
        startActivity(intent);

    }
}