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

public class Adapter_Quiz extends RecyclerView.Adapter<Adapter_Quiz.Recycle_Adapter_Schdual_View_Holder> {
    private final ArrayList<Quiz> list;
    private final Context context;

    static class Recycle_Adapter_Schdual_View_Holder extends RecyclerView.ViewHolder {
        final TextView Quiz;
        final RadioGroup RG;
        final RadioButton R1;
        final RadioButton R2;
        final RadioButton R3;
        final RadioButton R4;
        final LinearLayout button;
        final ImageView[] check = new ImageView[8];


        Recycle_Adapter_Schdual_View_Holder(View itemView) {
            super(itemView);
            Quiz = itemView.findViewById(R.id.quiz_2017);
            RG = itemView.findViewById(R.id.RG1_2017);
            R1 = itemView.findViewById(R.id.rb1_2017);
            R2 = itemView.findViewById(R.id.rb2_2017);
            R3 = itemView.findViewById(R.id.rb3_2017);
            R4 = itemView.findViewById(R.id.rb4_2017);
            button = itemView.findViewById(R.id.solve);
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
    public Recycle_Adapter_Schdual_View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_row_2017, parent, false);
        return new Recycle_Adapter_Schdual_View_Holder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull final Recycle_Adapter_Schdual_View_Holder holder, final int position) {
        Quiz cureentItem = list.get(position);
        holder.Quiz.setText(cureentItem.getQuiz());

        holder.R1.setText(cureentItem.getR1_text());
        holder.R1.setOnClickListener(v -> {
            holder.R2.setChecked(false);
            holder.R3.setChecked(false);
            holder.R4.setChecked(false);
        });
        holder.R2.setText(cureentItem.getR2_text());
        holder.R2.setOnClickListener(v -> {
            holder.R1.setChecked(false);
            holder.R3.setChecked(false);
            holder.R4.setChecked(false);
        });
        if (cureentItem.getR3_check() == -1) {
            holder.R3.setVisibility(View.GONE);
            holder.check[5].setVisibility(View.GONE);
            holder.check[4].setVisibility(View.GONE);
        } else {
            holder.R3.setText(cureentItem.getR3_text());
            holder.R3.setOnClickListener(v -> {
                holder.R1.setChecked(false);
                holder.R2.setChecked(false);
                holder.R4.setChecked(false);
            });
        }
        if (cureentItem.getR4_check() == -1) {
            holder.R4.setVisibility(View.GONE);
            holder.check[6].setVisibility(View.GONE);
            holder.check[7].setVisibility(View.GONE);
        } else {
            holder.R4.setText(cureentItem.getR4_text());
            holder.R4.setOnClickListener(v -> {
                holder.R1.setChecked(false);
                holder.R2.setChecked(false);
                holder.R3.setChecked(false);
            });
        }
        holder.button.setOnClickListener(v -> {

            if (holder.R1.isChecked() || holder.R2.isChecked() || holder.R3.isChecked() || holder.R4.isChecked()) {
                holder.R1.setClickable(false);
                holder.R2.setClickable(false);
                holder.R3.setClickable(false);
                holder.R4.setClickable(false);
                if (list.get(position).getR1_check() == 1) {
//                        holder.R1.setChecked(true);
//                        holder.R2.setChecked(false);
//                        holder.R3.setChecked(false);
//                        holder.R4.setChecked(false);
                    holder.check[0].setVisibility(View.VISIBLE);
                    holder.check[1].setVisibility(View.GONE);
                    holder.R1.setTextColor(context.getResources().getColor(R.color.green));
                    holder.R2.setTextColor(Color.RED);
                    holder.check[2].setVisibility(View.GONE);
                    holder.check[3].setVisibility(View.VISIBLE);

                    if (cureentItem.getR3_check() != -1) {
                        holder.R3.setTextColor(Color.RED);
                        holder.check[4].setVisibility(View.GONE);
                        holder.check[5].setVisibility(View.VISIBLE);
                    }
                    if (cureentItem.getR4_check() != -1) {
                        holder.R4.setTextColor(Color.RED);
                        holder.check[6].setVisibility(View.GONE);
                        holder.check[7].setVisibility(View.VISIBLE);
                    }


                } else if (list.get(position).getR2_check() == 1) {
//                        holder.R1.setChecked(false);
//                        holder.R2.setChecked(true);
//                        holder.R3.setChecked(false);
//                        holder.R4.setChecked(false);
                    holder.R1.setTextColor(Color.RED);
                    holder.check[0].setVisibility(View.GONE);
                    holder.check[1].setVisibility(View.VISIBLE);
                    holder.R2.setTextColor(context.getResources().getColor(R.color.green));
                    holder.check[2].setVisibility(View.VISIBLE);
                    holder.check[3].setVisibility(View.GONE);
                    if (cureentItem.getR3_check() != -1) {
                        holder.R3.setTextColor(Color.RED);
                        holder.check[4].setVisibility(View.GONE);
                        holder.check[5].setVisibility(View.VISIBLE);
                    }
                    if (cureentItem.getR4_check() != -1) {

                        holder.R4.setTextColor(Color.RED);
                        holder.check[6].setVisibility(View.GONE);
                        holder.check[7].setVisibility(View.VISIBLE);
                    }
                } else if (list.get(position).getR3_check() == 1) {
//                        holder.R1.setChecked(false);
//                        holder.R2.setChecked(false);
//                        holder.R3.setChecked(true);
//                        holder.R4.setChecked(false);
                    holder.R1.setTextColor(Color.RED);
                    holder.check[0].setVisibility(View.GONE);
                    holder.check[1].setVisibility(View.VISIBLE);
                    holder.R2.setTextColor(Color.RED);
                    holder.check[2].setVisibility(View.GONE);
                    holder.check[3].setVisibility(View.VISIBLE);
                    if (cureentItem.getR3_check() != -1) {
                        holder.R3.setTextColor(context.getResources().getColor(R.color.green));
                        holder.check[4].setVisibility(View.VISIBLE);
                        holder.check[5].setVisibility(View.GONE);
                    }
                    if (cureentItem.getR4_check() != -1) {

                        holder.R4.setTextColor(Color.RED);
                        holder.check[6].setVisibility(View.GONE);
                        holder.check[7].setVisibility(View.VISIBLE);
                    }
                } else if (list.get(position).getR4_check() == 1) {
//                        holder.R1.setChecked(false);
//                        holder.R2.setChecked(false);
//                        holder.R3.setChecked(false);
//                        holder.R4.setChecked(true);
                    holder.R1.setTextColor(Color.RED);
                    holder.check[0].setVisibility(View.GONE);
                    holder.check[1].setVisibility(View.VISIBLE);
                    holder.R2.setTextColor(Color.RED);
                    holder.check[2].setVisibility(View.GONE);
                    holder.check[3].setVisibility(View.VISIBLE);
                    if (cureentItem.getR3_check() != -1) {

                        holder.R3.setTextColor(Color.RED);
                        holder.check[4].setVisibility(View.GONE);
                        holder.check[5].setVisibility(View.VISIBLE);
                    }
                    if (cureentItem.getR4_check() != -1) {

                        holder.R4.setTextColor(context.getResources().getColor(R.color.green));
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
