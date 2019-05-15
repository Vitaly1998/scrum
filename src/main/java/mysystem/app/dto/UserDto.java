package mysystem.app.dto;

public class UserDto {

    private String name;
    private String surname;
    private String role;
    private String username;
    private String password;

    public UserDto() {
    }

    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserDto(String name, String surname, String role, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getSurname() {
        return surname;
    }

    public String getRole() {
        return role;
    }

}