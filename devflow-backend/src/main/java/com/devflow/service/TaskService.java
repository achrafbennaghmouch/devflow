package com.devflow.service;

import com.devflow.dto.task.TaskRequest;
import com.devflow.dto.task.TaskResponse;
import com.devflow.entity.Project;
import com.devflow.entity.Task;
import com.devflow.entity.TaskPriority;
import com.devflow.entity.TaskStatus;
import com.devflow.exception.ResourceNotFoundException;
import com.devflow.repository.ProjectRepository;
import com.devflow.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    public TaskResponse create(TaskRequest request) {
        Project project = getProject(request.projectId());
        Task task = new Task();
        applyRequest(task, request, project);
        return toResponse(taskRepository.save(task));
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> findAll(Long projectId, TaskStatus status) {
        List<Task> tasks;
        if (projectId != null && status != null) {
            tasks = taskRepository.findByProjectIdAndStatus(projectId, status);
        } else if (projectId != null) {
            tasks = taskRepository.findByProjectId(projectId);
        } else if (status != null) {
            tasks = taskRepository.findByStatus(status);
        } else {
            tasks = taskRepository.findAll();
        }
        return tasks.stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public TaskResponse findById(Long id) {
        return toResponse(getTask(id));
    }

    public TaskResponse update(Long id, TaskRequest request) {
        Task task = getTask(id);
        Project project = getProject(request.projectId());
        applyRequest(task, request, project);
        return toResponse(taskRepository.save(task));
    }

    public void delete(Long id) {
        taskRepository.delete(getTask(id));
    }

    private void applyRequest(Task task, TaskRequest request, Project project) {
        task.setTitle(request.title().trim());
        task.setDescription(request.description());
        task.setStatus(request.status() == null ? TaskStatus.TODO : request.status());
        task.setPriority(request.priority() == null ? TaskPriority.MEDIUM : request.priority());
        task.setDueDate(request.dueDate());
        task.setProject(project);
    }

    private Task getTask(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Tâche introuvable avec l'identifiant " + id));
    }

    private Project getProject(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Projet introuvable avec l'identifiant " + id));
    }

    private TaskResponse toResponse(Task task) {
        return new TaskResponse(
                task.getId(), task.getTitle(), task.getDescription(),
                task.getStatus(), task.getPriority(), task.getDueDate(),
                task.getProject().getId(), task.getProject().getName(),
                task.getCreatedAt(), task.getUpdatedAt());
    }
}
