package com.Elkood.ling_en4.EN4.Adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Elkood.ling_en4.EN4.Important_quiz.unit_package.unit10;
import com.Elkood.ling_en4.EN4.Important_quiz.unit_package.unit11;
import com.Elkood.ling_en4.EN4.Important_quiz.unit_package.unit12;
import com.Elkood.ling_en4.EN4.Important_quiz.unit_package.unit13;
import com.Elkood.ling_en4.EN4.Important_quiz.unit_package.unit14;
import com.Elkood.ling_en4.EN4.Important_quiz.unit_package.unit8;
import com.Elkood.ling_en4.EN4.Important_quiz.unit_package.unit9;
import com.Elkood.ling_en4.EN4.Utils.ItemClickListener;
import com.Elkood.ling_en4.EN4.Utils.item_words;
import com.Elkood.ling_en4.R;

import java.util.ArrayList;

public class Adapter_Unit extends RecyclerView.Adapter<Adapter_Unit.Recycle_Adapter_Schdual_View_Holder> {
    private final ArrayList<item_words> list;
    private int lastPosition = -1;
    private final Context context;

    public static class Recycle_Adapter_Schdual_View_Holder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        final TextView Unit;
        final TextView Number;
        private ItemClickListener itemClickListener;


        Recycle_Adapter_Schdual_View_Holder(View itemView) {
            super(itemView);
            Unit = itemView.findViewById(R.id.unit);
            Number = itemView.findViewById(R.id.number);

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

    public Adapter_Unit(ArrayList<item_words> List, Context context) {
        this.list = List;
        this.context = context;
    }

    @NonNull
    @Override
    public Recycle_Adapter_Schdual_View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_unit, parent, false);
        return new Recycle_Adapter_Schdual_View_Holder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull Recycle_Adapter_Schdual_View_Holder holder, int position) {
        holder.Unit.setText(list.get(position).getWords());
        holder.Number.setText(list.get(position).getMeans());

        holder.setItemClickListener((view, position1, isLongClick) -> {
            if (isLongClick) {
                Toast.makeText(context, "Ite 20", Toast.LENGTH_SHORT).show();

            } else {
                if (position == 0) {
                    Intent intent = new Intent(view.getContext(), unit8.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);


                    view.getContext().startActivity(intent);
                } else if (position == 1) {
                    Intent intent = new Intent(view.getContext(), unit9.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                    view.getContext().startActivity(intent);
                } else if (position == 2) {
                    Intent intent = new Intent(view.getContext(), unit10.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                    view.getContext().startActivity(intent);
                } else if (position == 3) {
                    Intent intent = new Intent(view.getContext(), unit11.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                    view.getContext().startActivity(intent);
                } else if (position == 4) {
                    Intent intent = new Intent(view.getContext(), unit12.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                    view.getContext().startActivity(intent);
                } else if (position == 5) {
                    Intent intent = new Intent(view.getContext(), unit13.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                    view.getContext().startActivity(intent);
                } else if (position == 6) {
                    Intent intent = new Intent(view.getContext(), unit14.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                    view.getContext().startActivity(intent);
                }


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
