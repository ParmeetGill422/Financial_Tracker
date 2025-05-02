<h1>Finance Tracker CLI (Java Capstone)</h1>

![Screenshot 2025-05-02 023111](https://github.com/user-attachments/assets/c4b0e1e1-35f0-4b75-baac-4fcb452ce97a)



A clean and simple command-line Java application designed to help users track income and expenses efficiently. It allows users to record deposits and payments, view a complete financial ledger, and generate various financial reports, all through an intuitive terminal interface.
## ✅ Features

📌 CLI Menu with clear options  
💰 Add **Deposits** (income)  
🧾 Add **Payments** (expenses)  
📖 View full **Ledger**  
🗂 Filter by:
- Deposits only
- Payments only  

📊 Generate **Reports**:
- 📅 Month-To-Date
- ⏳ Previous Month
- 📆 Year-To-Date
- 🕰 Previous Year
- 🔍 Search by Vendor
- 🧠 **Custom Search** (Challenge Feature): date range, vendor, and description filters

## 📸 Screenshots

### 🏠 Home Menu
<details>
<summary>Click to expand</summary>

![home screen](https://github.com/user-attachments/assets/86adae9d-deec-4aa8-a28c-103173e12580)

</details>

---

### 📂 Ledger Menu
<details>
<summary>Click to expand</summary>

![ledger](https://github.com/user-attachments/assets/3ff10064-905b-461e-aabf-5e9b682be13a)

</details>

---

### 📊 Reports Menu
<details>
<summary>Click to expand</summary>

![report](https://github.com/user-attachments/assets/f0ec2b56-75f7-4252-abb0-2711f4cc14b7)

</details>



<h1>Interesting Code</h1>
This method sorts a list of transactions in descending order. It compares each pair of adjacent transactions and swaps them if the earlier one comes after the later one, ensuring the most recent transactions appear first. A copy of the original list is used to avoid modifying the original data.

```java
private static List<Transaction> sortDescending(List<Transaction> list) {
    List<Transaction> copy = new ArrayList<>(list);
    for (int i = 0; i < copy.size() - 1; i++) {
        for (int j = 0; j < copy.size() - i - 1; j++) {
            if (copy.get(j).getDate().isBefore(copy.get(j + 1).getDate())) {
                Transaction temp = copy.get(j);
                copy.set(j, copy.get(j + 1));
                copy.set(j + 1, temp);
            }
        }
```
