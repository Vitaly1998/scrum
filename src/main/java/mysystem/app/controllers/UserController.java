package mysystem.app.controllers;


import mysystem.app.dto.UserDto;
import mysystem.app.models.User;
import mysystem.app.sevices.TaskService;
import mysystem.app.sevices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public HttpStatus loginUser(@RequestBody UserDto userDto) {
        return userService.checkUser(new User(userDto)) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
    }


    @PostMapping(value = "/registrate")
    public HttpStatus regUser(@RequestBody UserDto userDto) {
        return userService.createUser(new User(userDto)) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
    }
}
