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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import id.ac.umn.admincrownfit.databinding.ActivityAdminEditBinding;
import id.ac.umn.admincrownfit.databinding.ActivityRenewalMemberBinding;

public class AdminEdit extends AppCompatActivity {
    ActivityAdminEditBinding binding;
    DatabaseReference reference;
    private Button btnsubmtirenewal;
    String passwordasli = "HALLO";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.btnsubmtirenewal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = binding.inputjangka.getText().toString();
                String email = binding.inputjangka2.getText().toString();
                String notelp = binding.inputjangka3.getText().toString();
                String kerja = binding.inputlokasi.getText().toString();
                String tinggal = binding.inputlokasi3.getText().toString();

                openDialog(nama, email, notelp, kerja, tinggal);
                //updateData(nama, email, notelp, kerja, tinggal);
            }
        });

    }

    private void openDialog(String nama, String email, String notelp, String kerja, String tinggal){
        final Dialog dialog = new Dialog(AdminEdit.this);
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
                    updateData(nama, email, notelp, kerja, tinggal);

                }else{
                    Toast.makeText(AdminEdit.this, "Password Salah", Toast.LENGTH_LONG).show();
                }
                /*((password.getText().toString()) != passwordasli ) {
                    Toast.makeText(getApplicationContext(), "Password Salah", Toast.LENGTH_LONG).show();
                }*/
            }
        });
        dialog.show();
    }

    private void updateData(String nama, String email, String notelp, String kerja, String tinggal) {
        HashMap member = new HashMap();
        member.put("email", email);
        member.put("nama", nama);
        //member.put("pw", pw);
        member.put("tempatkerja", kerja);
        member.put("tempattinggal", tinggal);

        reference = FirebaseDatabase.getInstance("https://tescrossfitfirebaseid-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("listadmin");
        reference.child(notelp).updateChildren(member).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    binding.inputjangka.setText("");
                    binding.inputjangka2.setText("");
                    binding.inputjangka3.setText("");
                    binding.inputlokasi.setText("");
                    binding.inputlokasi3.setText("");
                    Toast.makeText(AdminEdit.this, "Successfuly Updated", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AdminEdit.this, AdminList.class);
                    startActivity(intent);


                }else{
                    Toast.makeText(AdminEdit.this, "Failed to update", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}