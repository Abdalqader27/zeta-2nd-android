package com.Elkood.ling_en4.EN4.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Elkood.ling_en4.R;
import com.Elkood.ling_en4.EN4.Utils.ItemClickListener;
import com.Elkood.ling_en4.EN4.Utils.item_true_false;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter_true_false extends RecyclerView.Adapter<Adapter_true_false.Recycle_Adapter_Schdual_View_Holder> {
    private final ArrayList<item_true_false> list;
    private int lastPosition = -1;
    private final Context context;

    public static class Recycle_Adapter_Schdual_View_Holder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        final TextView text;
        final TextView desc;

        final ImageView pic;
        private ItemClickListener itemClickListener;


        Recycle_Adapter_Schdual_View_Holder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text_true_false);
            desc = itemView.findViewById(R.id.desc_true_false);
            pic = itemView.findViewById(R.id.pic_true_false);
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

    public Adapter_true_false(ArrayList<item_true_false> List, Context context) {
        this.list = List;
        this.context = context;
    }

    @NonNull
    @Override
    public Recycle_Adapter_Schdual_View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_true_false, parent, false);
        return new Recycle_Adapter_Schdual_View_Holder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull Recycle_Adapter_Schdual_View_Holder holder, int position) {
        item_true_false cureentItem = list.get(position);
        holder.text.setText(cureentItem.getText());
        holder.desc.setText(cureentItem.getDesc());
        holder.pic.setImageResource(cureentItem.getImage());
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
