package com.example.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Theatre;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;


public class TheatreAdapter extends RecyclerView.Adapter<TheatreAdapter.MyViewHolder> {
    // instance variables
    private Context context;
    ArrayList<Theatre> theatres;

    DatabaseReference databaseReference;


    public TheatreAdapter(Context context, ArrayList<Theatre> theatres) {
        this.context = context;
        this.theatres = theatres;
    }

        @NonNull
        @Override
        public TheatreAdapter.MyViewHolder onCreateViewHolder (@NonNull ViewGroup parent,
        int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.theater_ui, parent, false);
        return new MyViewHolder(view);
    }


        @Override
        public void onBindViewHolder (@NonNull TheatreAdapter.MyViewHolder holder,int position){

        databaseReference = FirebaseDatabase.getInstance().getReference("Theatres");

        Theatre theatre = theatres.get(position);
        holder.tv_theatreName.setText(theatre.getTheatreName());
        holder.tv_theatreRating.setText(theatre.getTheatreRating());
        holder.tv_theatreLocation.setText(theatre.getTheatreLocation());
                holder.btn_deleteTheatre.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        databaseReference.child(theatre.getTheatreName()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                                holder.notify();
                            }
                        });
                    }
                });
            }

        @Override
        public int getItemCount () {
        return theatres.size();
    }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tv_theatreName, tv_theatreRating, tv_theatreLocation;
            Button btn_deleteTheatre;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                tv_theatreName = itemView.findViewById(R.id.name);
                tv_theatreLocation = itemView.findViewById(R.id.locationTF);
                tv_theatreRating = itemView.findViewById(R.id.rating);
                btn_deleteTheatre = itemView.findViewById(R.id.btn_deleteTheatre);
            }
        }
//
    }
