# 💰 SpendWise — Personal Expense Tracker

[![Build APK](https://github.com/YOUR_USERNAME/SpendWise/actions/workflows/build.yml/badge.svg)](https://github.com/YOUR_USERNAME/SpendWise/actions/workflows/build.yml)
![Android](https://img.shields.io/badge/Android-API%2024%2B-green)
![Java](https://img.shields.io/badge/Language-Java-orange)
![License](https://img.shields.io/badge/License-MIT-blue)

A clean, intuitive personal finance app for Android that helps you track your income and expenses with beautiful charts and category breakdowns.

---

## ✨ Features

- 📊 **Dashboard** with real-time balance, income, and expense summary
- 🍩 **Pie Chart** breakdown of spending by category (powered by MPAndroidChart)
- 📋 **Transaction History** with date, category, and amount
- ➕ **Add Transactions** — income or expense, with category and notes
- 💾 **Offline Storage** using Room (SQLite) — no internet required
- 🎨 **Material Design 3** UI with smooth navigation

---

## 📱 Screenshots

> *(Add screenshots here after building)*

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Java |
| UI | Material Design 3, XML Layouts |
| Architecture | MVVM (ViewModel + LiveData) |
| Database | Room (SQLite) |
| Charts | MPAndroidChart |
| Build | Gradle + GitHub Actions |

---

## 🚀 Getting Started

### Prerequisites
- Android Studio Hedgehog or later
- JDK 17
- Android SDK API 24+

### Build & Run
```bash
git clone https://github.com/YOUR_USERNAME/SpendWise.git
cd SpendWise
./gradlew assembleDebug
```

Or open in Android Studio and click **Run**.

---

## 📂 Project Structure

```
app/
├── data/
│   ├── db/          # Room database, DAO
│   ├── model/       # Transaction entity
│   └── repository/  # Data repository
└── ui/
    ├── main/        # MainActivity, DashboardFragment, ViewModel
    ├── history/     # HistoryFragment, RecyclerView adapter
    └── add/         # AddTransactionActivity
```

---

## 📄 License

MIT License — feel free to use and modify.

---

*Built with ❤️ as part of my Android development portfolio.*
