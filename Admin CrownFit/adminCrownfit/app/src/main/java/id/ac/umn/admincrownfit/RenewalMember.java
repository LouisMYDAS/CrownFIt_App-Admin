package id.ac.umn.admincrownfit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

import id.ac.umn.admincrownfit.databinding.ActivityRenewalMemberBinding;

public class RenewalMember extends AppCompatActivity {
    ActivityRenewalMemberBinding binding;
    DatabaseReference reference;
    private Button btnsubmtirenewal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRenewalMemberBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.btnsubmtirenewal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = binding.inputjangka.getText().toString();
                String email = binding.inputjangka2.getText().toString();
                String pw = binding.inputjangka3.getText().toString();
                String tgl = binding.inputlokasi.getText().toString();
                String title = binding.inputlokasi3.getText().toString();

                updateData(nama, email, pw, tgl, title);
            }
        });



    }

    private void updateData(String nama, String email, String pw, String tgl, String title) {
        HashMap member = new HashMap();
        member.put("email", email);
        member.put("nama", nama);
        //member.put("pw", pw);
        member.put("tgl", tgl);
        member.put("title", title);

        reference = FirebaseDatabase.getInstance("https://tescrossfitfirebaseid-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("subs_data");
        reference.child(pw).updateChildren(member).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    binding.inputjangka.setText("");
                    binding.inputjangka2.setText("");
                    binding.inputjangka3.setText("");
                    binding.inputlokasi.setText("");
                    binding.inputlokasi3.setText("");
                    Toast.makeText(RenewalMember.this, "Successfuly Updated", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RenewalMember.this, MemberList.class);
                    startActivity(intent);


                }else{
                    Toast.makeText(RenewalMember.this, "Failed to update", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}