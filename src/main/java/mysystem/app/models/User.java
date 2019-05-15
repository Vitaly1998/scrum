package mysystem.app.models;


import mysystem.app.dto.UserDto;


public class User {

    private String id;
    private String name;
    private String surname;
    private Role role;
    private String login;
    private String password;

    public User() {

    }


    public User(String login) {

    }

    public User(String name, String surname, Role role, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.login = login;
        this.password = password;
    }

    public User(String id, String name, String surname, Role role, String login, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.login = login;
        this.password = password;
    }


    public User(UserDto userDto) {
        if (userDto.getRole() != null && userDto.getName() != null && userDto.getSurname() != null) {
            this.name = userDto.getName();
            this.surname = userDto.getSurname();
            this.role = Role.valueOf(userDto.getRole().toUpperCase());
        } else {
            this.name = null;
            this.surname = null;
            this.role = null;
        }
        this.login = userDto.getUsername();
        this.password = userDto.getPassword();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User that = (User) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (role != null ? !role.name().equals(that.role.name()) : that.role != null) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name='" + name + '\'' + ", surname='" + surname + '\'' + ", login='" + login + '\'' + ", role='" + role.name() + '\'' + '}';
    }

}

