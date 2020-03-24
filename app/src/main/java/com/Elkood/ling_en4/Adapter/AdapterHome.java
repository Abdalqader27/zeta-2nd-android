package com.Elkood.ling_en4.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Elkood.ling_en4.Models.CardHome;
import com.Elkood.ling_en4.Views.TwoYear.English_3.English_4.Important_quiz.Abbrevationss.Abbreviations;
import com.Elkood.ling_en4.Views.TwoYear.English_3.English_4.Important_quiz.Compound_Nouns.Compound_Nouns;
import com.Elkood.ling_en4.Views.TwoYear.English_3.English_4.Important_quiz.Extinsions.Extinsions;
import com.Elkood.ling_en4.Views.TwoYear.English_3.English_4.Important_quiz.True_false.True_false;
import com.Elkood.ling_en4.Views.TwoYear.English_3.English_4.Important_quiz.Vocabulary.Vocabulary;
import com.Elkood.ling_en4.Views.TwoYear.English_3.English_4.Important_quiz.molakas;
import com.Elkood.ling_en4.Views.TwoYear.English_3.English_4.Important_quiz.word;
import com.Elkood.ling_en4.Utils.ItemClickListener;
import com.Elkood.ling_en4.R;

import java.util.ArrayList;

public class AdapterHome extends RecyclerView.Adapter<AdapterHome.Recycle_Adapter_Schdual_View_Holder> {
    private final ArrayList<CardHome> list;
    private int lastPosition = -1;
    private final Context context;

    public static class Recycle_Adapter_Schdual_View_Holder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        final TextView title;
        final TextView desc;
        final TextView date;
        final ImageView pico;
        private ItemClickListener itemClickListener;


        Recycle_Adapter_Schdual_View_Holder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_home);
            desc = itemView.findViewById(R.id.desc_home);
            date = itemView.findViewById(R.id.datehome);
            pico = itemView.findViewById(R.id.picture);
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

    public AdapterHome(ArrayList<CardHome> List, Context context) {
        this.list = List;
        this.context = context;
    }

    @NonNull
    @Override
    public Recycle_Adapter_Schdual_View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_home, parent, false);
        return new Recycle_Adapter_Schdual_View_Holder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull Recycle_Adapter_Schdual_View_Holder holder, int position) {
        CardHome cureentItem = list.get(position);
        holder.title.setText(cureentItem.getTitle());
        holder.desc.setText(cureentItem.getDesc());
        holder.date.setText(cureentItem.getDate());
        holder.pico.setImageResource(cureentItem.getImage());
        holder.setItemClickListener((view, position1, isLongClick) -> {
            if (isLongClick) {
                Toast.makeText(context, "Ite 20", Toast.LENGTH_SHORT).show();

            } else {
                if (position1 == 0) {
                    Intent intent = new Intent(view.getContext(), word.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                    view.getContext().startActivity(intent);
                } else if (position1 == 1) {
                    Intent intent = new Intent(view.getContext(), Vocabulary.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                    view.getContext().startActivity(intent);
                } else if (position1 == 2) {
                    Intent intent = new Intent(view.getContext(), True_false.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    view.getContext().startActivity(intent);
                } else if (position1 == 3) {
                    Intent intent = new Intent(view.getContext(), Abbreviations.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    view.getContext().startActivity(intent);

                } else if (position1 == 4) {
                    Intent intent = new Intent(view.getContext(), Compound_Nouns.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    view.getContext().startActivity(intent);
                } else if (position1 == 5) {
                    Intent intent = new Intent(view.getContext(), Extinsions.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    view.getContext().startActivity(intent);
                }else if (position1 == 6) {
                    Intent intent = new Intent(view.getContext(), molakas.class);
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
