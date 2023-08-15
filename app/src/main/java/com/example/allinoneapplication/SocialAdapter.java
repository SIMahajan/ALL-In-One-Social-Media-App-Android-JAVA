package com.example.allinoneapplication;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SocialAdapter extends RecyclerView.Adapter<SocialAdapter.SocialViewHoler> {


    private int[]  profile;
    public SocialAdapter(int[] profile)
    {

        this.profile=profile;
    }


    @NonNull
    @Override
    public SocialViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.activity_social_icons,parent,false);
        return new SocialViewHoler((view));
    }

    @Override
    public void onBindViewHolder(@NonNull SocialViewHoler holder, int position) {
        int imageView=profile[position];
        holder.imgIcon.setImageResource(profile[position]);

    }

    @Override
    public int getItemCount() {
        return profile.length;
    }

    public class SocialViewHoler extends RecyclerView.ViewHolder {
        ImageButton imgIcon;

        public SocialViewHoler(@NonNull View itemView) {
            super(itemView);
            imgIcon=(ImageButton) itemView.findViewById(R.id.logos);
            imgIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    switch (getAdapterPosition()){
                        case 0:
                            Intent intent=new Intent(v.getContext(),YouTube.class);
                            v.getContext().startActivity(intent);


                            break;
                        case 1:
                            Intent intent1=new Intent(v.getContext(),FaceBook.class);
                            v.getContext().startActivity(intent1);
                            break;

                        case 2:
                            Intent intent2=new Intent(v.getContext(),Instagram.class);
                            v.getContext().startActivity(intent2);

                            break;
                        case 3:
                            Intent intent3=new Intent(v.getContext(),Snapchat.class);
                            v.getContext().startActivity(intent3);

                            break;
                        case 4:
                            Intent intent4=new Intent(v.getContext(),GooglePlus.class);
                            v.getContext().startActivity(intent4);

                            break;

                        case 5:
                            Intent intent5=new Intent(v.getContext(), Spotify.class);
                            v.getContext().startActivity(intent5);

                            break;
                        case 6:
                            Intent intent6=new Intent(v.getContext(), Twitter.class);
                            v.getContext().startActivity(intent6);

                            break;
                        case 7:
                            Intent intent7=new Intent(v.getContext(),Linkedin.class);
                            v.getContext().startActivity(intent7);

                            break;

                        default:


                    }
                }
            });
        }
    }
}