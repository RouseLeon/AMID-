public class Task {
    private String name;
    private boolean completed;
    private String priority;

    public Task(String name, boolean completed, String priority) {
        this.name = name;
        this.completed = completed;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getPriority() {
        return priority;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
