package com.spendwise.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.spendwise.data.db.AppDatabase;
import com.spendwise.data.db.TransactionDao;
import com.spendwise.data.model.Transaction;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TransactionRepository {

    private final TransactionDao dao;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public final LiveData<List<Transaction>> allTransactions;
    public final LiveData<Double> totalExpenses;
    public final LiveData<Double> totalIncome;

    public TransactionRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        dao = db.transactionDao();
        allTransactions = dao.getAllTransactions();
        totalExpenses = dao.getTotalExpenses();
        totalIncome = dao.getTotalIncome();
    }

    public void insert(Transaction t) {
        executor.execute(() -> dao.insert(t));
    }

    public void update(Transaction t) {
        executor.execute(() -> dao.update(t));
    }

    public void delete(Transaction t) {
        executor.execute(() -> dao.delete(t));
    }

    public LiveData<List<Transaction>> getByCategory(String category) {
        return dao.getByCategory(category);
    }

    public LiveData<List<Transaction>> getExpenses() {
        return dao.getExpenses();
    }

    public LiveData<List<Transaction>> getIncomes() {
        return dao.getIncomes();
    }
}
