package com.Elkood.ling_en4.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.Elkood.ling_en4.R;
import com.Elkood.ling_en4.Utils.ItemClickListener;
import com.Elkood.ling_en4.Utils.item_words;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter_words extends RecyclerView.Adapter<Adapter_words.Recycle_Adapter_Schdual_View_Holder> {
    private final ArrayList<item_words> list;
    private int lastPosition = -1;
    private final Context context;

    public static class Recycle_Adapter_Schdual_View_Holder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        final TextView words;
        final TextView means;
        private ItemClickListener itemClickListener;


        Recycle_Adapter_Schdual_View_Holder(View itemView) {
            super(itemView);
            words = itemView.findViewById(R.id.wordo);
            means=itemView.findViewById(R.id.means);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        void setItemClickListener(ItemClickListener itemClickListener) {

            this.itemClickListener = itemClickListener;

        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), false);

        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), true);
            return true;
        }

    }

    public Adapter_words(ArrayList<item_words> List, Context context) {
        this.list = List;
        this.context = context;
    }

    @NonNull
    @Override
    public Recycle_Adapter_Schdual_View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_words, parent, false);
        return new Recycle_Adapter_Schdual_View_Holder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull Recycle_Adapter_Schdual_View_Holder holder, int position) {
        holder.words.setText(list.get(position).getWords());
        holder.means.setText(list.get(position).getMeans());

        holder.setItemClickListener((view, position1, isLongClick) -> {
            if (isLongClick) {
                Toast.makeText(context, "Ite 20", Toast.LENGTH_SHORT).show();

            }
        });
        if (position > lastPosition) {

            Animation animation = AnimationUtils.loadAnimation(context,
                    R.anim.bbb);
            holder.itemView.startAnimation(animation);
            lastPosition = position;
            lastPosition = -1;
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}
