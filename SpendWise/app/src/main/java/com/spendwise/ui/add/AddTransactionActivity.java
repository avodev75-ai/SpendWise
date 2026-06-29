package com.spendwise.ui.add;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.spendwise.R;
import com.spendwise.data.model.Transaction;
import com.spendwise.ui.main.TransactionViewModel;

import java.util.Objects;

public class AddTransactionActivity extends AppCompatActivity {

    private TransactionViewModel viewModel;
    private TextInputEditText etTitle, etAmount, etNote;
    private AutoCompleteTextView acCategory;
    private MaterialButtonToggleGroup toggleType;
    private String selectedType = "expense";

    private final String[] CATEGORIES = {"Food", "Transport", "Bills", "Shopping", "Health", "Entertainment", "Salary", "Other"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        viewModel = new ViewModelProvider(this).get(TransactionViewModel.class);

        etTitle = findViewById(R.id.et_title);
        etAmount = findViewById(R.id.et_amount);
        etNote = findViewById(R.id.et_note);
        acCategory = findViewById(R.id.ac_category);
        toggleType = findViewById(R.id.toggle_type);

        // Category dropdown
        ArrayAdapter<String> catAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, CATEGORIES);
        acCategory.setAdapter(catAdapter);

        // Type toggle
        toggleType.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            if (isChecked) {
                selectedType = checkedId == R.id.btn_expense ? "expense" : "income";
            }
        });
        toggleType.check(R.id.btn_expense);

        // Toolbar back
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Add Transaction");
        }

        findViewById(R.id.btn_save).setOnClickListener(v -> saveTransaction());
    }

    private void saveTransaction() {
        String title = Objects.requireNonNull(etTitle.getText()).toString().trim();
        String amountStr = Objects.requireNonNull(etAmount.getText()).toString().trim();
        String category = acCategory.getText().toString().trim();
        String note = Objects.requireNonNull(etNote.getText()).toString().trim();

        if (title.isEmpty() || amountStr.isEmpty() || category.isEmpty()) {
            Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(amountStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid amount", Toast.LENGTH_SHORT).show();
            return;
        }

        Transaction t = new Transaction(title, amount, category, selectedType, System.currentTimeMillis(), note);
        viewModel.insert(t);

        Toast.makeText(this, "Transaction saved!", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
