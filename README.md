# 🎓 Student Record Management System (CLI) - Java

A simple **command-line Student Record Management System** written in plain Java.  
It uses a `Student` class and an `ArrayList` to store student records and provides full CRUD operations via a text-based menu.

---

## ✨ Features
- ➕ Add student (ID, name, marks)  
- 📋 View all students  
- ✏️ Update student by ID  
- ❌ Delete student by ID  
- 🔍 Search student by ID or name  
- 📊 Sort students by ID or marks  
- 🚪 Exit the program  

---

## 📂 Project Structure
```
├── Student.java         # Model class
├── StudentManager.java  # Main program with menu & CRUD logic
└── .gitignore           # Ignore build/IDE files
```

---

## ⚙️ How to Compile & Run
1. Open a terminal in the project folder.  
2. Compile the Java files:
   ```bash
   javac Student.java StudentManager.java
   ```
3. Run the program:
   ```bash
   java StudentManager
   ```

---

## 🛠️ Requirements
- Java JDK 8 or higher  
- A terminal/command prompt  

---

## 🚀 Example Usage
```text
===== Student Management System =====
1. Add Student
2. View Students
3. Update Student
4. Delete Student
5. Search Student
6. Sort Students
7. Exit
Enter your choice:
```

---

## 📌 Notes
- Student IDs must be unique.  
- Sorting can be done either by **ID** or by **marks**.  
- Data is stored only in memory (no file/DB persistence).  

---

## 📜 License
This project is free to use for learning and educational purposes.  
