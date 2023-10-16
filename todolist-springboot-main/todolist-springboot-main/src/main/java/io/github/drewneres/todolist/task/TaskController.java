package io.github.drewneres.todolist.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.drewneres.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  @Autowired
  private ITaskRepository taskRepository;

  @PostMapping("/")
  public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request) {
    taskModel.setIdUser((UUID) request.getAttribute("idUser"));

    var currentDate = LocalDateTime.now();
    if (currentDate.isAfter(taskModel.getStartAt())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de início deve ser maior do que a data atual");
    } else if (currentDate.isAfter(taskModel.getEndAt())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de término deve ser maior do que a data atual");
    } else if (taskModel.getStartAt().isAfter(taskModel.getEndAt())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("A data de início deve ser menor do que a data de término");
    }

    var task = this.taskRepository.save(taskModel);
    return ResponseEntity.status(201).body(task);
  }

  @GetMapping("/")
  public List<TaskModel> list(HttpServletRequest request) {
    return this.taskRepository.findByIdUser((UUID) request.getAttribute("idUser"));
  }

  @PutMapping("/{id}")
  public ResponseEntity update(@RequestBody TaskModel taskModel, @PathVariable UUID id, HttpServletRequest request) {
    var task = this.taskRepository.findById(id).orElse(null);

    if(task==null){
      return ResponseEntity.status(404).body("Tarefa não encontrada");
    }

    if (!task.getIdUser().equals(request.getAttribute("idUser"))) {
      return ResponseEntity.status(401).body("Usuário sem permissão para alterar a tarefa");
    }

    Utils.copyNonNullProperties(taskModel, task);

    return ResponseEntity.status(204).body(this.taskRepository.save(task));
  }
}
