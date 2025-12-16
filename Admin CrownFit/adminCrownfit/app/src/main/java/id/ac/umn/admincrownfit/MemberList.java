package id.ac.umn.admincrownfit;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
import com.squareup.picasso.Picasso;

import java.util.LinkedList;
import java.util.List;

public class MemberList extends AppCompatActivity {
    private final List<String> items = new LinkedList<>();
    FirebaseRecyclerOptions<MemberListHelper> options;
    FirebaseRecyclerAdapter<MemberListHelper,MemberHolder> adapter;
    DatabaseReference reference;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);

        reference = FirebaseDatabase.getInstance("https://tescrossfitfirebaseid-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("subs_data");

//        items.add("Member 1");
//        items.add("Member 2");
//        items.add("Member 3");
//        items.add("Member 4");
//        items.add("Member 5");
//        items.add("Member 6");
//        items.add("Member 7");
//        items.add("Member 8");
//        items.add("Member 9");

        recyclerView = findViewById(R.id.listMember);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
        LoadData();
    }
    private void LoadData() {
        options = new FirebaseRecyclerOptions.Builder<MemberListHelper>().setQuery(reference, MemberListHelper.class).build();
        adapter = new FirebaseRecyclerAdapter<MemberListHelper, MemberHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MemberHolder holder, int position, @NonNull MemberListHelper model) {
                holder.membername.setText(model.getName());
                holder.subsdate.setText(model.getTgl());
               // Log.d("TES",model.getName());
                holder.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MemberList.this, MemberInfo.class);

                        intent.putExtra("workoutKey", getRef(holder.getAdapterPosition()).getKey());
                        intent.putExtra("memName", model.getName());
                        intent.putExtra("memEmail",model.getEmail());
                        intent.putExtra("memDate", model.getTgl());
                        intent.putExtra("memTitle", model.getTitle());
                        intent.putExtra("memPass", model.getPw());

                        startActivity(intent);

                    }
                });
                holder.delButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openDialog(model.getName());

                    }
                });
            }

            @NonNull
            @Override
            public MemberHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_member, parent, false);

                return new MemberHolder(v);
            }
        };
        adapter.startListening();

        recyclerView.setAdapter(adapter);
    }

    private void openDialog(String name){
        final Dialog dialog = new Dialog(MemberList.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.toastremovemember);

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