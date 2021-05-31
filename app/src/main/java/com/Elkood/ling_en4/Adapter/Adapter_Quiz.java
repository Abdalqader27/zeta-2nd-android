package com.Elkood.ling_en4.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Elkood.ling_en4.Models.Quiz;
import com.Elkood.ling_en4.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class Adapter_Quiz extends RecyclerView.Adapter<Adapter_Quiz.Recycle_Adapter_Schedule_View_Holder> {
    private final ArrayList<Quiz> list;
    private final Context context;

    static class Recycle_Adapter_Schedule_View_Holder extends RecyclerView.ViewHolder {
        TextView Quiz;

        RadioGroup radioGroup;

        RadioButton r1Options;

        RadioButton r2Options;

        RadioButton r3Options;

        RadioButton r4Options;
        LinearLayout solveButton;

        final ImageView[] check = new ImageView[8];


        Recycle_Adapter_Schedule_View_Holder(View itemView) {
            super(itemView);
             Quiz = itemView.findViewById(R.id.Quiz);
            radioGroup = itemView.findViewById(R.id.radioGroup);
            r1Options = itemView.findViewById(R.id.r1Options);
            r2Options = itemView.findViewById(R.id.r2Options);
            r3Options = itemView.findViewById(R.id.r3Options);
            r4Options = itemView.findViewById(R.id.r4Options);
            solveButton = itemView.findViewById(R.id.solveButton);
            check[0] = itemView.findViewById(R.id.r1_right);
            check[1] = itemView.findViewById(R.id.r1_wrong);
            check[2] = itemView.findViewById(R.id.r2_right);
            check[3] = itemView.findViewById(R.id.r2_wrong);
            check[4] = itemView.findViewById(R.id.r3_right);
            check[5] = itemView.findViewById(R.id.r3_wrong);
            check[6] = itemView.findViewById(R.id.r4_right);
            check[7] = itemView.findViewById(R.id.r4_wrong);


        }


    }

    public Adapter_Quiz(ArrayList<Quiz> List, Context context) {
        this.list = List;
        this.context = context;
    }

    @NonNull
    @Override
    public Recycle_Adapter_Schedule_View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_row_2017, parent, false);
        return new Recycle_Adapter_Schedule_View_Holder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull final Recycle_Adapter_Schedule_View_Holder holder, final int position) {
        Quiz cureentItem = list.get(position);
        holder.Quiz.setText(cureentItem.getQuiz());

        holder.r1Options.setText(cureentItem.getR1_text());
        holder.r1Options.setOnClickListener(v -> {
            holder.r2Options.setChecked(false);
            holder.r3Options.setChecked(false);
            holder.r4Options.setChecked(false);
        });
        holder.r2Options.setText(cureentItem.getR2_text());
        holder.r2Options.setOnClickListener(v -> {
            holder.r1Options.setChecked(false);
            holder.r3Options.setChecked(false);
            holder.r4Options.setChecked(false);
        });
        if (cureentItem.getR3_check() == -1) {
            holder.r3Options.setVisibility(View.GONE);
            holder.check[5].setVisibility(View.GONE);
            holder.check[4].setVisibility(View.GONE);
        } else {
            holder.r3Options.setText(cureentItem.getR3_text());
            holder.r3Options.setOnClickListener(v -> {
                holder.r1Options.setChecked(false);
                holder.r2Options.setChecked(false);
                holder.r4Options.setChecked(false);
            });
        }
        if (cureentItem.getR4_check() == -1) {
            holder.r4Options.setVisibility(View.GONE);
            holder.check[6].setVisibility(View.GONE);
            holder.check[7].setVisibility(View.GONE);
        } else {
            holder.r4Options.setText(cureentItem.getR4_text());
            holder.r4Options.setOnClickListener(v -> {
                holder.r1Options.setChecked(false);
                holder.r2Options.setChecked(false);
                holder.r3Options.setChecked(false);
            });
        }
        holder.solveButton.setOnClickListener(v -> {

            if (holder.r1Options.isChecked() || holder.r2Options.isChecked() || holder.r3Options.isChecked() || holder.r4Options.isChecked()) {
                holder.r1Options.setClickable(false);
                holder.r2Options.setClickable(false);
                holder.r3Options.setClickable(false);
                holder.r4Options.setClickable(false);
                if (list.get(position).getR1_check() == 1) {
//                        holder.R1.setChecked(true);
//                        holder.R2.setChecked(false);
//                        holder.R3.setChecked(false);
//                        holder.R4.setChecked(false);
                    holder.check[0].setVisibility(View.VISIBLE);
                    holder.check[1].setVisibility(View.GONE);
                    holder.r1Options.setTextColor(context.getResources().getColor(R.color.green));
                    holder.r2Options.setTextColor(Color.RED);
                    holder.check[2].setVisibility(View.GONE);
                    holder.check[3].setVisibility(View.VISIBLE);

                    if (cureentItem.getR3_check() != -1) {
                        holder.r3Options.setTextColor(Color.RED);
                        holder.check[4].setVisibility(View.GONE);
                        holder.check[5].setVisibility(View.VISIBLE);
                    }
                    if (cureentItem.getR4_check() != -1) {
                        holder.r4Options.setTextColor(Color.RED);
                        holder.check[6].setVisibility(View.GONE);
                        holder.check[7].setVisibility(View.VISIBLE);
                    }


                } else if (list.get(position).getR2_check() == 1) {
//                        holder.R1.setChecked(false);
//                        holder.R2.setChecked(true);
//                        holder.R3.setChecked(false);
//                        holder.R4.setChecked(false);
                    holder.r1Options.setTextColor(Color.RED);
                    holder.check[0].setVisibility(View.GONE);
                    holder.check[1].setVisibility(View.VISIBLE);
                    holder.r2Options.setTextColor(context.getResources().getColor(R.color.green));
                    holder.check[2].setVisibility(View.VISIBLE);
                    holder.check[3].setVisibility(View.GONE);
                    if (cureentItem.getR3_check() != -1) {
                        holder.r3Options.setTextColor(Color.RED);
                        holder.check[4].setVisibility(View.GONE);
                        holder.check[5].setVisibility(View.VISIBLE);
                    }
                    if (cureentItem.getR4_check() != -1) {

                        holder.r4Options.setTextColor(Color.RED);
                        holder.check[6].setVisibility(View.GONE);
                        holder.check[7].setVisibility(View.VISIBLE);
                    }
                } else if (list.get(position).getR3_check() == 1) {
//                        holder.R1.setChecked(false);
//                        holder.R2.setChecked(false);
//                        holder.R3.setChecked(true);
//                        holder.R4.setChecked(false);
                    holder.r1Options.setTextColor(Color.RED);
                    holder.check[0].setVisibility(View.GONE);
                    holder.check[1].setVisibility(View.VISIBLE);
                    holder.r2Options.setTextColor(Color.RED);
                    holder.check[2].setVisibility(View.GONE);
                    holder.check[3].setVisibility(View.VISIBLE);
                    if (cureentItem.getR3_check() != -1) {
                        holder.r3Options.setTextColor(context.getResources().getColor(R.color.green));
                        holder.check[4].setVisibility(View.VISIBLE);
                        holder.check[5].setVisibility(View.GONE);
                    }
                    if (cureentItem.getR4_check() != -1) {

                        holder.r4Options.setTextColor(Color.RED);
                        holder.check[6].setVisibility(View.GONE);
                        holder.check[7].setVisibility(View.VISIBLE);
                    }
                } else if (list.get(position).getR4_check() == 1) {
//                        holder.R1.setChecked(false);
//                        holder.R2.setChecked(false);
//                        holder.R3.setChecked(false);
//                        holder.R4.setChecked(true);
                    holder.r1Options.setTextColor(Color.RED);
                    holder.check[0].setVisibility(View.GONE);
                    holder.check[1].setVisibility(View.VISIBLE);
                    holder.r2Options.setTextColor(Color.RED);
                    holder.check[2].setVisibility(View.GONE);
                    holder.check[3].setVisibility(View.VISIBLE);
                    if (cureentItem.getR3_check() != -1) {

                        holder.r3Options.setTextColor(Color.RED);
                        holder.check[4].setVisibility(View.GONE);
                        holder.check[5].setVisibility(View.VISIBLE);
                    }
                    if (cureentItem.getR4_check() != -1) {

                        holder.r4Options.setTextColor(context.getResources().getColor(R.color.green));
                        holder.check[6].setVisibility(View.VISIBLE);
                        holder.check[7].setVisibility(View.GONE);
                    }

                } else {
                    Toast.makeText(context, "للأ سف ماعرفنا حل هاد السؤال ", Toast.LENGTH_LONG).show();
                }
            } else {

                Snackbar.make(v, "يرجى اختيار الجواب", Snackbar.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}
