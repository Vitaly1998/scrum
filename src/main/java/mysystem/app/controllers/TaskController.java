package mysystem.app.controllers;

import mysystem.app.dto.TaskDto;
import mysystem.app.models.Priority;
import mysystem.app.models.State;
import mysystem.app.models.Task;
import mysystem.app.models.User;
import mysystem.app.sevices.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TaskController {

    private static final long serialVersionUID = 1L;

    @Autowired
    private TaskService taskService;

    @GetMapping(value = "/getTask/{login}")
    public List<TaskDto> getTasks(@PathVariable("login") String login) {
       List<Task> tasks = taskService.getTasksForUser(login);
       List<TaskDto> taskDtoList = new ArrayList<>();
       if (tasks!=null) {
           for (Task task : tasks) {
               if (task.getState().equals(State.INWORK)) {
                   taskDtoList.add(new TaskDto(task.getId(), task.getName(), task.getPurpose(), task.getState().toString(), task.getUser_id(), task.getPriority().toString(), task.getAuthor_id(), task.getProject_id()));
               }
           }
       }
       return taskDtoList;
    }

    @GetMapping(value = "/getFreeTask")
    public List<TaskDto> getFreeTask() {
        List<Task> tasks = taskService.getAllTask();
        List<TaskDto> taskDtoList = new ArrayList<>();
        if (tasks!=null) {
            for (Task task : tasks) {
                if (task.getState().equals(State.OPEN) && (task.getUser_id() == null || task.getUser_id().equals(""))) {
                    taskDtoList.add(new TaskDto(task.getId(), task.getName(), task.getPurpose(), task.getState().toString(), task.getUser_id(), task.getPriority().toString(), task.getAuthor_id(), task.getProject_id()));
                }
            }
        }
        return taskDtoList;
    }


    @GetMapping(value = "/getFinishedTask/{login}")
    public List<TaskDto> getFinishedTask(@PathVariable("login") String login) {
        List<Task> tasks = taskService.getTasksForUser(login);
        List<TaskDto> taskDtoList = new ArrayList<>();
        if (tasks!=null) {
            for (Task task : tasks) {
                if (task.getState().equals(State.DONE)) {
                    taskDtoList.add(new TaskDto(task.getId(), task.getName(), task.getPurpose(), task.getState().toString(), task.getUser_id(), task.getPriority().toString(), task.getAuthor_id(), task.getProject_id()));
                }
            }
        }
        return taskDtoList;
    }

    @PostMapping(value = "/setDoneTask")
    public HttpStatus setTaskDone(@RequestBody TaskDto taskDto) {
        return taskService.setDoneTask(new Task(taskDto.getId())) ? HttpStatus.OK: HttpStatus.BAD_REQUEST;
    }

    @PostMapping(value = "/selectTask/{login}")
    public HttpStatus selectTaskMyself(@PathVariable("login") String login, @RequestBody TaskDto taskDto) {
        return taskService.selectTaskMyself(new Task(taskDto.getId()), login) ? HttpStatus.OK: HttpStatus.BAD_REQUEST;
    }

    @PostMapping(value = "/newTask/{login}")
    public HttpStatus newTask(@PathVariable("login") String login, @RequestBody TaskDto taskDto) {
        System.out.println(taskDto.getName() + taskDto.getPriority() + taskDto.getProject() + taskDto.getPurpose() + taskDto.getUser_new() + login);
        User user = taskService.getUser(login);
        String new_user = null;
        State state;
        User us = taskService.getUserInSurname(taskDto.getUser_new());
        if (us!=null)  {
            state = State.INWORK;
            new_user = us.getId();
        } else
            state = State.OPEN;

        String project_id = taskService.getProjectId(taskDto.getProject());
        return taskService.newTask(new Task(UUID.randomUUID().toString(),
                taskDto.getName(),
                taskDto.getPurpose(),
                state,
                Priority.valueOf(taskDto.getPriority().toUpperCase()),
                new_user,
                user.getId(),
                project_id))? HttpStatus.OK: HttpStatus.BAD_REQUEST;

    }

}
