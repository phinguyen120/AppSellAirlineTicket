package com.example.app_sellairlineticket.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_sellairlineticket.Model.Photo;
import com.example.app_sellairlineticket.R;

import java.util.List;

public class PhotoViewpageAdapter extends RecyclerView.Adapter<PhotoViewpageAdapter.PhotoViewHolder>{
    private List<Photo> mListPhoto;

    public PhotoViewpageAdapter(List<Photo> mListPhoto) {
        this.mListPhoto = mListPhoto;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slide,parent,false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
         Photo photo = mListPhoto.get(position);
         if(photo == null){
             return;
         }
         holder.imgPhoto.setImageResource(photo.getIdPhoto());
    }

    @Override
    public int getItemCount() {
        if(mListPhoto != null){
            return mListPhoto.size();
        }
        return 0;
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgPhoto;
        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_silde);
        }
    }
}
