package com.spendwise.ui.main;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.spendwise.data.model.Transaction;
import com.spendwise.data.repository.TransactionRepository;

import java.util.List;

public class TransactionViewModel extends AndroidViewModel {

    private final TransactionRepository repository;

    public final LiveData<List<Transaction>> allTransactions;
    public final LiveData<Double> totalExpenses;
    public final LiveData<Double> totalIncome;

    public TransactionViewModel(Application application) {
        super(application);
        repository = new TransactionRepository(application);
        allTransactions = repository.allTransactions;
        totalExpenses = repository.totalExpenses;
        totalIncome = repository.totalIncome;
    }

    public void insert(Transaction t) { repository.insert(t); }
    public void update(Transaction t) { repository.update(t); }
    public void delete(Transaction t) { repository.delete(t); }

    public LiveData<List<Transaction>> getExpenses() { return repository.getExpenses(); }
    public LiveData<List<Transaction>> getIncomes() { return repository.getIncomes(); }
    public LiveData<List<Transaction>> getByCategory(String cat) { return repository.getByCategory(cat); }
}
