package com.example.myapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Bookseats;
import com.example.myapplication.R;
import com.example.myapplication.model.Theatre;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;


public class UserTheatreAdapter extends RecyclerView.Adapter<UserTheatreAdapter.MyViewHolder> {
    // instance variables
    private Context context;
    ArrayList<Theatre> theatres;

    DatabaseReference databaseReference;


    public UserTheatreAdapter(Context context, ArrayList<Theatre> theatres) {
        this.context = context;
        this.theatres = theatres;
    }

    @NonNull
    @Override
    public UserTheatreAdapter.MyViewHolder onCreateViewHolder (@NonNull ViewGroup parent,
                                                           int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.activity_user_theater_list, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder (@NonNull UserTheatreAdapter.MyViewHolder holder,int position){

        databaseReference = FirebaseDatabase.getInstance().getReference("Theatres");

        Theatre theatre = theatres.get(position);
        holder.tv_theatreName.setText(theatre.getTheatreName());
        holder.tv_theatreRating.setText(theatre.getTheatreRating());
        holder.tv_theatreLocation.setText(theatre.getTheatreLocation());

        holder.btn_selectTheatre.setOnClickListener(v -> {
            context.startActivity(new Intent(context, Bookseats.class));
        });


//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if(user.getEmail().
//
//                contains("admin")) {
//
//            holder.btn_deleteTheatre.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    databaseReference.child(theatre.getTheatreName()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
//                            holder.notify();
//                        }
//                    });
//                }
//            });
//        }
    }

    @Override
    public int getItemCount () {
        return theatres.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_theatreName, tv_theatreRating, tv_theatreLocation;
        Button btn_selectTheatre;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_theatreName = itemView.findViewById(R.id.name);
            tv_theatreLocation = itemView.findViewById(R.id.locationTF);
            tv_theatreRating = itemView.findViewById(R.id.rating);
            btn_selectTheatre = itemView.findViewById(R.id.btn_selectTheatre);
        }
    }
//
}
