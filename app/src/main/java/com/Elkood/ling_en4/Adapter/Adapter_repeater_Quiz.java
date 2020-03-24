package com.Elkood.ling_en4.Adapter;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Elkood.ling_en4.Views.TwoYear.English_3.English_4.Repeatrer_Quiz.R2012;
import com.Elkood.ling_en4.Views.TwoYear.English_3.English_4.Repeatrer_Quiz.R2013;
import com.Elkood.ling_en4.Views.TwoYear.English_3.English_4.Repeatrer_Quiz.R2014;
import com.Elkood.ling_en4.Views.TwoYear.English_3.English_4.Repeatrer_Quiz.R2015;
import com.Elkood.ling_en4.Models.item_repeater;
import com.Elkood.ling_en4.Views.TwoYear.English_3.English_4.Repeatrer_Quiz.r2016;
import com.Elkood.ling_en4.Views.TwoYear.English_3.English_4.Repeatrer_Quiz.r2017;
import com.Elkood.ling_en4.Views.TwoYear.English_3.English_4.Repeatrer_Quiz.r2017_2;
import com.Elkood.ling_en4.Views.TwoYear.English_3.English_4.Repeatrer_Quiz.r2018;
import com.Elkood.ling_en4.Utils.ItemClickListener;
import com.Elkood.ling_en4.R;

import java.util.ArrayList;

public class Adapter_repeater_Quiz extends RecyclerView.Adapter<Adapter_repeater_Quiz.Recycle_Adapter_Schdual_View_Holder> {
    private final ArrayList<item_repeater> list;
    private int lastPosition = -1;
    private final Context context;

    public static class Recycle_Adapter_Schdual_View_Holder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        final TextView name;
        final TextView type;
        final TextView number;

        private ItemClickListener itemClickListener;


        Recycle_Adapter_Schdual_View_Holder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.row_name_re);
            type = itemView.findViewById(R.id.type_of_rep);
            number = itemView.findViewById(R.id.number_quiz_re);
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

    public Adapter_repeater_Quiz(ArrayList<item_repeater> List, Context context) {
        this.list = List;
        this.context = context;
    }

    @NonNull
    @Override
    public Recycle_Adapter_Schdual_View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_repeter, parent, false);
        return new Recycle_Adapter_Schdual_View_Holder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull Recycle_Adapter_Schdual_View_Holder holder, int position) {
        item_repeater cureentItem = list.get(position);
        holder.name.setText(cureentItem.getName());
        holder.type.setText(cureentItem.getType());
        holder.number.setText(cureentItem.getNum());
        holder.setItemClickListener((view, position1, isLongClick) -> {
            if (isLongClick) {
                Toast.makeText(context, cureentItem.getName(), Toast.LENGTH_SHORT).show();
            } else {
                if (position1 == 0) {
                    Intent intent = new Intent(view.getContext(), r2018.class);
                    view.getContext().startActivity(intent);

                } else if (position1 == 1) {
                    Dialog dialogo = new Dialog(view.getContext());
                    dialogo.setContentView(R.layout.choice2);
                    dialogo.setCancelable(true);
                    dialogo.show();
                    RadioButton R2017_1 = dialogo.findViewById(R.id.f1);
                    RadioButton R2017_2 = dialogo.findViewById(R.id.f2);
                    R2017_1.setOnClickListener(v -> {
                        Intent intent = new Intent(view.getContext(), r2017.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                        view.getContext().startActivity(intent);

                        dialogo.dismiss();
                    });
                    R2017_2.setOnClickListener(v -> {
                        Intent intent = new Intent(view.getContext(), r2017_2.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                        view.getContext().startActivity(intent);

                        dialogo.dismiss();

                    });

                } else if (position1 == 2) {
                    Dialog dialogo = new Dialog(view.getContext());
                    dialogo.setContentView(R.layout.choice);
                    dialogo.setCancelable(true);
                    dialogo.show();
                    RadioButton R2016_1 = dialogo.findViewById(R.id.arbic_lan);
                    RadioButton R2016_3 = dialogo.findViewById(R.id.eng_lan);
                    R2016_1.setOnClickListener(v -> {
                        Intent intent = new Intent(view.getContext(), r2016.class);
                        view.getContext().startActivity(intent);

                        dialogo.dismiss();
                    });

                    R2016_3.setOnClickListener(v -> {
                        Intent intent = new Intent(view.getContext(), com.Elkood.ling_en4.Views.TwoYear.English_3.English_4.Repeatrer_Quiz.R2016_3.class);
                        view.getContext().startActivity(intent);

                        dialogo.dismiss();

                    });

                } else if (position1 == 3) {
                    Intent intent = new Intent(view.getContext(), R2015.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                    view.getContext().startActivity(intent);

                } else if (position1 == 4) {
                    Intent intent = new Intent(view.getContext(), R2014.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                    view.getContext().startActivity(intent);

                } else if (position1 == 5) {
                    Intent intent = new Intent(view.getContext(), R2013.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                    view.getContext().startActivity(intent);
                } else if (position1 == 6) {
                    Intent intent = new Intent(view.getContext(), R2012.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                    view.getContext().startActivity(intent);

                }
//                dialog = new SweetAlertDialog(view.getContext(), SweetAlertDialog.PROGRESS_TYPE);
//                dialog.getProgressHelper().setBarColor(Color.parseColor("#0FB2C0"));
//                dialog.setCancelable(false);
//                dialog.setTitleText(" الرجاء الإنتظار ..... ")
//                        .show();
//                Handler handler = new Handler();
//                handler.postDelayed(
//                        () -> {
//
//
//                        }, 1500L);

                //Here you can send the extras.


                // close this activity

//                new Timer().schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//
//                    }
//                }, 2000);

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
