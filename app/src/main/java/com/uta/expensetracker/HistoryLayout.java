package com.uta.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HistoryLayout extends ArrayAdapter<Expense> {

ImageView imageView;
    public HistoryLayout(Context context,int resource, List<Expense> expenses) {
        super(context, 0, expenses);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Expense expense = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_history_layout, parent, false);
        }

        TextView nameTextView = convertView.findViewById(R.id.item_name);
        TextView amountTextView = convertView.findViewById(R.id.item_amount);
        TextView categoryTextView = convertView.findViewById(R.id.item_category);
        TextView dateTextView = convertView.findViewById(R.id.item_date);
        imageView = convertView.findViewById(R.id.item_image);

        String category = expense.getCategory();
        if (category.equals("Food")) {
            imageView.setImageResource(R.drawable.baseline_fastfood_24);
        } else if (category.equals("Grocery")) {
            imageView.setImageResource(R.drawable.baseline_local_grocery_store_24);
        } else if (category.equals("Rent")) {
            imageView.setImageResource(R.drawable.baseline_home_work_24);
        } else if (category.equals("MISC")) {
            imageView.setImageResource(R.drawable.baseline_miscellaneous_services_24);
        }



            String date_str = expense.getDate();
        Date formattedDate = null;
        try {
            formattedDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US).parse(date_str);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String converted_date = new SimpleDateFormat("MMM dd yyyy", Locale.US).format(formattedDate);
        System.out.println("con date:" + converted_date);

        nameTextView.setText(expense.getName());

        amountTextView.setText(String.format("$%.2f", expense.getAmount()));

        dateTextView.setText(converted_date);

        categoryTextView.setText(expense.getCategory());

        return convertView;
    }
}