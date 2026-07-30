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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Elkood.ling_en4.Models.CardHome;
import com.Elkood.ling_en4.ui.screens.wordlist.WordIndexActivity;
import com.Elkood.ling_en4.ui.screens.reference.ReferenceAccordionActivity;
import com.Elkood.ling_en4.ui.screens.truefalse.TrueFalseListActivity;
import com.Elkood.ling_en4.ui.screens.molakhs.MolakhsActivity;
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
            Context ctx = view.getContext();
            Intent intent;
            switch (position1) {
                case 0: // word
                    intent = new Intent(ctx, WordIndexActivity.class);
                    break;
                case 1: // Vocabulary
                    intent = new Intent(ctx, ReferenceAccordionActivity.class);
                    intent.putExtra("extra_topic", "VOCABULARY");
                    break;
                case 2: // True_false
                    intent = new Intent(ctx, TrueFalseListActivity.class);
                    break;
                case 3: // Abbreviations
                    intent = new Intent(ctx, ReferenceAccordionActivity.class);
                    intent.putExtra("extra_topic", "ABBREVIATIONS");
                    break;
                case 4: // Compound_Nouns
                    intent = new Intent(ctx, ReferenceAccordionActivity.class);
                    intent.putExtra("extra_topic", "COMPOUND_NOUNS");
                    break;
                case 5: // Extinsions
                    intent = new Intent(ctx, ReferenceAccordionActivity.class);
                    intent.putExtra("extra_topic", "EXTENSIONS");
                    break;
                case 6: // molakas
                default:
                    intent = new Intent(ctx, MolakhsActivity.class);
                    break;
            }
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            ctx.startActivity(intent);
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
