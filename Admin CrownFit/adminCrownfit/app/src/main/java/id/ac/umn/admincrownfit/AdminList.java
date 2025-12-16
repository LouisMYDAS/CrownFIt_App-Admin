package id.ac.umn.admincrownfit;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.List;

public class AdminList extends AppCompatActivity {

    private final List<String> items = new LinkedList<>();
    FirebaseRecyclerOptions<AdminHelper> options;
    FirebaseRecyclerAdapter<AdminHelper,AdminHolder> adapter;
    DatabaseReference reference;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_list);
        reference = FirebaseDatabase.getInstance("https://tescrossfitfirebaseid-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("listadmin");


//        items.add("Admin 1");
//        items.add("Admin 2");
//        items.add("Admin 3");


        recyclerView = findViewById(R.id.listAdmin);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        LoadData();
    }

    private void LoadData() {
        options = new FirebaseRecyclerOptions.Builder<AdminHelper>().setQuery(reference, AdminHelper.class).build();
        adapter = new FirebaseRecyclerAdapter<AdminHelper, AdminHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull AdminHolder holder, int position, @NonNull AdminHelper model) {
                holder.membername.setText(model.getNama());
                holder.location.setText(model.getTempatkerja());
                // Log.d("TES",model.getName());
                holder.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(AdminList.this, admininfo.class);

                        intent.putExtra("workoutKey", getRef(holder.getAdapterPosition()).getKey());
                        intent.putExtra("adminName", model.getNama());
                        intent.putExtra("adminEmail",model.getEmail());
                        intent.putExtra("adminNotelp", model.getNotelp());
                        intent.putExtra("adminTinggal", model.getTempattinggal());
                        intent.putExtra("adminKerja", model.getTempatkerja());
                        startActivity(intent);

                    }
                });
                holder.delButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openDialog(model.getNama());

                    }
                });
            }

            @NonNull
            @Override
            public AdminHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_admin, parent, false);

                return new AdminHolder(v);
            }
        };
        adapter.startListening();

        recyclerView.setAdapter(adapter);
    }
    private void openDialog(String name){
        final Dialog dialog = new Dialog(AdminList.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.toastremoveadmin);

        Button btnokdialog = dialog.findViewById(R.id.btnkonfirmasinambahadmin);
        Button btncanceldialog = dialog.findViewById(R.id.btnkonfirmasinambahadmin2);

        btnokdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query deleteQuery = reference.orderByChild("nama").equalTo(name);

                deleteQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot snapshot1: snapshot.getChildren()){
                            snapshot1.getRef().removeValue();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                dialog.dismiss();
            }

        });
        btncanceldialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}