package mysystem.app.dto;

public class TaskDto {

    private String id;
    private String name;
    private String purpose;
    private String state;
    private String user_new;
    private String priority;
    private String author;
    private String project;

    public TaskDto() {

    }

    public TaskDto(String name, String purpose, String state, String user_new, String priority, String author, String project) {
        this.name = name;
        this.purpose = purpose;
        this.user_new = user_new;
        this.priority = priority;
        this.project = project;
    }

    public TaskDto(String id, String name, String purpose, String state, String user_new, String priority, String author, String project) {
        this.id = id;
        this.name = name;
        this.purpose = purpose;
        this.state = state;
        this.user_new = user_new;
        this.priority = priority;
        this.author = author;
        this.project = project;
    }

    public String getUser_new() { return user_new; }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPurpose() {
        return purpose;
    }

    public String getAuthor() {
        return author;
    }

    public String getPriority() {
        return priority;
    }

    public String getProject() {
        return project;
    }

    public String getState() {
        return state;
    }
}
