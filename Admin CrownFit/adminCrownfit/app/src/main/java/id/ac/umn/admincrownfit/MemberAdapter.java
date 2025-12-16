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

public class MemberAdapter extends RecyclerView.Adapter<MemberHolder> {

    List<String> items;
    public MemberAdapter(List<String> items){
        this.items = items;
    }
    @NonNull
    @Override
    public MemberHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_member, parent, false);
        return new MemberHolder(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberHolder holder, int position) {
        holder.membername.setText(items.get(position));
        holder.membername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MemberInfo.class);
                v.getContext().startActivity(intent);
            }
        });
        holder.memberprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MemberInfo.class);
                v.getContext().startActivity(intent);
            }
        });
        holder.subsdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MemberInfo.class);
                v.getContext().startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return items.size();
    }
}

class MemberHolder extends RecyclerView.ViewHolder{
    TextView membername;
    ImageView memberprofile;
    TextView subsdate;
    ImageView delButton;
    View v;
    private MemberAdapter adapter;


    public MemberHolder(@NonNull View itemView) {
        super(itemView);
        membername = itemView.findViewById(R.id.membername);
        memberprofile = itemView.findViewById(R.id.memberpict);
        subsdate = itemView.findViewById(R.id.subsdate);
        delButton = itemView.findViewById(R.id.deleteicon);
        v = itemView;
    }

    public MemberHolder linkAdapter(MemberAdapter adapter){
        this.adapter = adapter;
        return this;
    }
}
