import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StudentManager {
    private List<Student> students = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        StudentManager app = new StudentManager();
        app.run();
    }

    private void run() {
        while (true) {
            printMenu();
            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewAll();
                case 3 -> updateStudent();
                case 4 -> deleteStudent();
                case 5 -> searchStudent();
                case 6 -> sortMenu();
                case 7 -> { System.out.println("Exiting... Goodbye!"); sc.close(); return; }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== Student Record Management ===");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Update Student by ID");
        System.out.println("4. Delete Student by ID");
        System.out.println("5. Search Student (ID or name)"); 
        System.out.println("6. Sort Students");
        System.out.println("7. Exit");
    }

    private void addStudent() {
        int id = readInt("Enter ID (integer): ");
        if (findById(id) != null) {
            System.out.println("A student with this ID already exists. Use another ID.");
            return;
        }
        String name = readString("Enter name: ");
        double marks = readDouble("Enter marks: ");
        students.add(new Student(id, name, marks));
        System.out.println("Student added successfully.");
    }

    private void viewAll() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("\n-- Student List --");
        students.forEach(s -> System.out.println(s.toString()));
    }

    private void updateStudent() {
        int id = readInt("Enter student ID to update: ");
        Student s = findById(id);
        if (s == null) {
            System.out.println("Student not found."); return;
        }
        System.out.println("Found: " + s);
        String name = readString("Enter new name (leave blank to keep): ");
        String marksStr = readString("Enter new marks (leave blank to keep): ");
        if (!name.isBlank()) s.setName(name);
        if (!marksStr.isBlank()) {
            try {
                double m = Double.parseDouble(marksStr);
                s.setMarks(m);
            } catch (NumberFormatException e) {
                System.out.println("Invalid marks input. Keeping previous value.");
            }
        }
        System.out.println("Student updated: " + s);
    }

    private void deleteStudent() {
        int id = readInt("Enter student ID to delete: ");
        Student s = findById(id);
        if (s == null) {
            System.out.println("Student not found."); return;
        }
        students.remove(s);
        System.out.println("Student removed successfully.");
    }

    private void searchStudent() {
        String q = readString("Enter ID or name to search: ");
        try {
            int id = Integer.parseInt(q);
            Student s = findById(id);
            if (s != null) { System.out.println("Found: " + s); return; }
        } catch (NumberFormatException ignored) {}

        List<Student> matches = students.stream()
                .filter(st -> st.getName().toLowerCase().contains(q.toLowerCase()))
                .collect(Collectors.toList());
        if (matches.isEmpty()) {
            System.out.println("No matching students found.");
        } else {
            System.out.println("Matches:");
            matches.forEach(System.out::println);
        }
    }

    private void sortMenu() {
        System.out.println("Sort by: 1) ID  2) Marks");
        int c = readInt("Choice: ");
        if (c == 1) students.sort(Comparator.comparingInt(Student::getId));
        else if (c == 2) students.sort(Comparator.comparingDouble(Student::getMarks).reversed());
        else { System.out.println("Invalid sort choice."); return; }
        System.out.println("Sorted list:");
        viewAll();
    }

    private Student findById(int id) {
        return students.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
    }

    // ---- Input helpers ----
    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine();
            try { return Integer.parseInt(line.trim()); }
            catch (NumberFormatException e) { System.out.println("Invalid integer. Try again."); }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine();
            try { return Double.parseDouble(line.trim()); }
            catch (NumberFormatException e) { System.out.println("Invalid number. Try again."); }
        }
    }

    private String readString(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }
}
