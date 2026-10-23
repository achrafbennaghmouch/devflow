package com.devflow.service;

import com.devflow.dto.project.ProjectRequest;
import com.devflow.dto.project.ProjectResponse;
import com.devflow.entity.Project;
import com.devflow.entity.ProjectStatus;
import com.devflow.exception.ResourceNotFoundException;
import com.devflow.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectResponse create(ProjectRequest request) {
        Project project = new Project();
        project.setName(request.name().trim());
        project.setDescription(request.description());
        project.setStatus(request.status() == null ? ProjectStatus.PLANNED : request.status());
        return toResponse(projectRepository.save(project));
    }

    @Transactional(readOnly = true)
    public List<ProjectResponse> findAll(String name, ProjectStatus status) {
        List<Project> projects;
        if (name != null && !name.isBlank()) {
            projects = projectRepository.findByNameContainingIgnoreCase(name.trim());
        } else if (status != null) {
            projects = projectRepository.findByStatus(status);
        } else {
            projects = projectRepository.findAll();
        }
        if (name != null && !name.isBlank() && status != null) {
            projects = projects.stream().filter(p -> p.getStatus() == status).toList();
        }
        return projects.stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public ProjectResponse findById(Long id) {
        return toResponse(getProject(id));
    }

    public ProjectResponse update(Long id, ProjectRequest request) {
        Project project = getProject(id);
        project.setName(request.name().trim());
        project.setDescription(request.description());
        if (request.status() != null) {
            project.setStatus(request.status());
        }
        return toResponse(projectRepository.save(project));
    }

    public void delete(Long id) {
        Project project = getProject(id);
        projectRepository.delete(project);
    }

    private Project getProject(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Projet introuvable avec l'identifiant " + id));
    }

    private ProjectResponse toResponse(Project project) {
        return new ProjectResponse(
                project.getId(), project.getName(), project.getDescription(),
                project.getStatus(), project.getCreatedAt(), project.getUpdatedAt());
    }
}
