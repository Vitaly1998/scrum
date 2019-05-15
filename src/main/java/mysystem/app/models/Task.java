package mysystem.app.models;

public class Task {

    private String id;
    private String name;
    private String purpose;
    private State state;
    private Priority priority;
    private String user_id;
    private String author_id;
    private String project_id;


    public Task() {

    }

    public Task(String id) {
        this.id = id;
    }

    public Task(String id, String name, String purpose, State state, Priority priority, String user_id, String author_id, String project_id) {
        this.id = id;
        this.name = name;
        this.purpose = purpose;
        this.state = state;
        this.priority = priority;
        this.user_id = user_id;
        this.author_id = author_id;
        this.project_id = project_id;
    }

    public String getId() { return id; }

    public String getName() { return name; }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public void setId(String id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task that = (Task) o;

        if (id != that.id) return false;
        if (purpose != null ? !purpose.equals(that.purpose) : that.purpose != null) return false;
        if (state != null ? !state.name().equals(that.state.name()) : that.state != null) return false;
        if (priority != null ? !priority.name().equals(that.priority.name()) : that.priority != null) return false;
        if (user_id != null ? !user_id.equals(that.user_id) : that.user_id != null) return false;
        if (author_id != null ? !author_id.equals(that.author_id) : that.author_id != null) return false;
        if (project_id != null ? !project_id.equals(that.project_id) : that.project_id != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (purpose != null ? purpose.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (priority != null ? priority.hashCode() : 0);
        result = 31 * result + (user_id != null ? user_id.hashCode() : 0);
        result = 31 * result + (author_id != null ? author_id.hashCode() : 0);
        result = 31 * result + (project_id != null ? project_id.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Task{" + "name='" + name + '\'' + ", purpose='" + purpose + '\'' + ", state='" + state.name() + '\'' + ", priority='" + priority.name() + +'\'' + ", user_id='" + user_id + +'\'' + ", author_id='" + author_id + '\'' + ", project_id='" + project_id + '\'' + '}';
    }

}
