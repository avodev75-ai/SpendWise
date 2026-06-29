package com.spendwise.ui.main;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.spendwise.R;
import com.spendwise.data.model.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DashboardFragment extends Fragment {

    private TransactionViewModel viewModel;
    private TextView tvBalance, tvIncome, tvExpense;
    private PieChart pieChart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvBalance = view.findViewById(R.id.tv_balance);
        tvIncome = view.findViewById(R.id.tv_total_income);
        tvExpense = view.findViewById(R.id.tv_total_expense);
        pieChart = view.findViewById(R.id.pie_chart);

        viewModel = new ViewModelProvider(requireActivity()).get(TransactionViewModel.class);

        setupPieChart();

        viewModel.totalIncome.observe(getViewLifecycleOwner(), income -> {
            double i = income != null ? income : 0;
            tvIncome.setText(String.format("$%.2f", i));
            updateBalance();
        });

        viewModel.totalExpenses.observe(getViewLifecycleOwner(), expense -> {
            double e = expense != null ? expense : 0;
            tvExpense.setText(String.format("$%.2f", e));
            updateBalance();
        });

        viewModel.allTransactions.observe(getViewLifecycleOwner(), this::updateChart);
    }

    private void updateBalance() {
        double income = viewModel.totalIncome.getValue() != null ? viewModel.totalIncome.getValue() : 0;
        double expense = viewModel.totalExpenses.getValue() != null ? viewModel.totalExpenses.getValue() : 0;
        double balance = income - expense;
        tvBalance.setText(String.format("$%.2f", balance));
        tvBalance.setTextColor(balance >= 0 ? Color.parseColor("#4CAF50") : Color.parseColor("#F44336"));
    }

    private void setupPieChart() {
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.TRANSPARENT);
        pieChart.setHoleRadius(58f);
        pieChart.setDrawEntryLabels(false);
        pieChart.getDescription().setEnabled(false);
        pieChart.getLegend().setEnabled(true);
        pieChart.setUsePercentValues(true);
    }

    private void updateChart(List<Transaction> transactions) {
        if (transactions == null || transactions.isEmpty()) {
            pieChart.clear();
            return;
        }

        Map<String, Float> categoryTotals = new HashMap<>();
        for (Transaction t : transactions) {
            if ("expense".equals(t.getType())) {
                categoryTotals.merge(t.getCategory(), (float) t.getAmount(), Float::sum);
            }
        }

        List<PieEntry> entries = new ArrayList<>();
        for (Map.Entry<String, Float> entry : categoryTotals.entrySet()) {
            entries.add(new PieEntry(entry.getValue(), entry.getKey()));
        }

        if (entries.isEmpty()) { pieChart.clear(); return; }

        PieDataSet dataSet = new PieDataSet(entries, "Expenses by Category");
        dataSet.setColors(
                Color.parseColor("#FF6B6B"),
                Color.parseColor("#4ECDC4"),
                Color.parseColor("#45B7D1"),
                Color.parseColor("#96CEB4"),
                Color.parseColor("#FFEAA7"),
                Color.parseColor("#DDA0DD")
        );
        dataSet.setSliceSpace(3f);
        dataSet.setValueTextSize(12f);
        dataSet.setValueTextColor(Color.WHITE);

        pieChart.setData(new PieData(dataSet));
        pieChart.invalidate();
    }
}
