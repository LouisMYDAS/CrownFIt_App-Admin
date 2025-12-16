package id.ac.umn.admincrownfit;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity {

    private TextView register;
    private TextView lupa;
    private Button butLogin;
    private EditText email;
    private EditText password;
    private boolean admin = true;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        butLogin = (Button)findViewById(R.id.butLogin);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        auth = FirebaseAuth.getInstance();

        butLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getEmail = email.getText().toString();
                String getPw = password.getText().toString();

                if(TextUtils.isEmpty(getEmail)){
                    Toast.makeText(getApplicationContext(),
                                    "Please your username!!",
                                    Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                if(TextUtils.isEmpty(getPw)){
                    Toast.makeText(getApplicationContext(),
                                    "Please your password!!",
                                    Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                if(getEmail.equals("owner") && getPw.equals("HALLO")){
                    Toast.makeText(LoginPage.this, "Welcome Owner/SuperAdmin", Toast.LENGTH_LONG).show();
                    Intent adminLog = new Intent(LoginPage.this, AdminMain.class);
                    startActivity(adminLog);
                }
                if(getEmail.equals("admin") && getPw.equals("iniadmin")){
                    Toast.makeText(LoginPage.this, "Welcome Admin", Toast.LENGTH_LONG).show();
                    Intent adminLog = new Intent(LoginPage.this, AdminMain.class);
                    startActivity(adminLog);
                }
            }
        });
    }
}