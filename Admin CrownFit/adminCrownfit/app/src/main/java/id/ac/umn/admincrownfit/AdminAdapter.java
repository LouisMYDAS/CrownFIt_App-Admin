package id.ac.umn.admincrownfit;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdminAdapter extends RecyclerView.Adapter<AdminHolder> {

    List<String> items;
    public AdminAdapter(List<String> items){
        this.items = items;
    }
    @NonNull
    @Override
    public AdminHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_admin, parent, false);

        return new AdminHolder(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminHolder holder, int position) {
        holder.membername.setText(items.get(position));
        holder.membername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), admininfo.class);
                v.getContext().startActivity(intent);
            }
        });
        holder.memberprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), admininfo.class);
                v.getContext().startActivity(intent);
            }
        });
        holder.location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), admininfo.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

class AdminHolder extends RecyclerView.ViewHolder{
    TextView membername;
    ImageView memberprofile;
    TextView location;
    ImageView delButton;
    View v;
    private AdminAdapter adapter;


    public AdminHolder(@NonNull View itemView) {
        super(itemView);
        membername = itemView.findViewById(R.id.membername);
        memberprofile = itemView.findViewById(R.id.memberpict);
        location = itemView.findViewById(R.id.location);
        delButton = itemView.findViewById(R.id.deleteicon);
        v=itemView;
    }

    public AdminHolder linkAdapter(AdminAdapter adapter){
        this.adapter = adapter;
        return this;
    }
}
