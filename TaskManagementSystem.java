import java.util.ArrayList;
import java.util.Scanner;

class Task {
    private int id;
    private String title;
    private String description;
    private String priority;
    private String dueDate;
    private boolean completed;

    public Task(int id, String title, String description,
                String priority, String dueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.completed = false;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void markCompleted() {
        completed = true;
    }

    public void display() {
        System.out.println("Task ID: " + id);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Priority: " + priority);
        System.out.println("Due Date: " + dueDate);
        System.out.println("Status: "
                + (completed ? "Completed" : "Pending"));
    }
}

public class TaskManagementSystem {

    static ArrayList<Task> tasks = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static int nextId = 1;

    static Task findTask(int id) {
        for (Task t : tasks) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    // Add Task
    static void addTask() {
        System.out.print("Enter task title: ");
        String title = sc.nextLine().trim();

        if (title.isEmpty()) {
            System.out.println("Task title cannot be empty.");
            return;
        }

        System.out.print("Enter description: ");
        String description = sc.nextLine().trim();

        System.out.print("Enter priority (High/Medium/Low): ");
        String priority = sc.nextLine().trim();

        if (!priority.equalsIgnoreCase("High")
                && !priority.equalsIgnoreCase("Medium")
                && !priority.equalsIgnoreCase("Low")) {
            System.out.println("Invalid priority.");
            return;
        }

        System.out.print("Enter due date (DD-MM-YYYY): ");
        String dueDate = sc.nextLine().trim();

        if (dueDate.isEmpty()) {
            System.out.println("Due date cannot be empty.");
            return;
        }

        tasks.add(new Task(nextId++, title, description,
                priority, dueDate));

        System.out.println("Task added successfully!");
    }

    // View Tasks
    static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }

        for (Task t : tasks) {
            System.out.println("--------------------");
            t.display();
        }
    }

    // Search Task
    static void searchTask() {
        System.out.print("Enter task ID to search: ");

        try {
            int id = Integer.parseInt(sc.nextLine().trim());
            Task t = findTask(id);

            if (t != null) {
                t.display();
            } else {
                System.out.println("Task not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid task ID.");
        }
    }

    // Update Task
    static void updateTask() {
        System.out.print("Enter task ID to update: ");

        try {
            int id = Integer.parseInt(sc.nextLine().trim());
            Task t = findTask(id);

            if (t == null) {
                System.out.println("Task not found.");
                return;
            }

            System.out.print("Enter new title: ");
            String title = sc.nextLine().trim();

            System.out.print("Enter new description: ");
            String description = sc.nextLine().trim();

            System.out.print("Enter new priority (High/Medium/Low): ");
            String priority = sc.nextLine().trim();

            if (title.isEmpty() || description.isEmpty()) {
                System.out.println("Details cannot be empty.");
                return;
            }

            if (!priority.equalsIgnoreCase("High")
                    && !priority.equalsIgnoreCase("Medium")
                    && !priority.equalsIgnoreCase("Low")) {
                System.out.println("Invalid priority.");
                return;
            }

            System.out.print("Enter new due date: ");
            String dueDate = sc.nextLine().trim();

            if (dueDate.isEmpty()) {
                System.out.println("Due date cannot be empty.");
                return;
            }

            t.setTitle(title);
            t.setDescription(description);
            t.setPriority(priority);
            t.setDueDate(dueDate);

            System.out.println("Task updated successfully!");

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid task ID.");
        }
    }

    // Delete Task
    static void deleteTask() {
        System.out.print("Enter task ID to delete: ");

        try {
            int id = Integer.parseInt(sc.nextLine().trim());
            Task t = findTask(id);

            if (t != null) {
                tasks.remove(t);
                System.out.println("Task deleted successfully!");
            } else {
                System.out.println("Task not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid task ID.");
        }
    }

    // Complete Task
    static void completeTask() {
        System.out.print("Enter task ID to complete: ");

        try {
            int id = Integer.parseInt(sc.nextLine().trim());
            Task t = findTask(id);

            if (t != null) {
                t.markCompleted();
                System.out.println("Task completed successfully!");
            } else {
                System.out.println("Task not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid task ID.");
        }
    }

    // Main Method
    public static void main(String[] args) {
        while (true) {
            System.out.println("\\nTO-DO LIST AND TASK MANAGEMENT SYSTEM");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Search Task");
            System.out.println("4. Update Task");
            System.out.println("5. Delete Task");
            System.out.println("6. Complete Task");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    addTask();
                    break;

                case "2":
                    viewTasks();
                    break;

                case "3":
                    searchTask();
                    break;

                case "4":
                    updateTask();
                    break;

                case "5":
                    deleteTask();
                    break;

                case "6":
                    completeTask();
                    break;

                case "7":
                    System.out.println("Exiting program...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
