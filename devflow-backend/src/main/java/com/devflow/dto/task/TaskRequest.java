package com.devflow.dto.task;

import com.devflow.entity.TaskPriority;
import com.devflow.entity.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record TaskRequest(
        @NotBlank(message = "Le titre de la tâche est obligatoire")
        @Size(max = 150, message = "Le titre ne doit pas dépasser 150 caractères")
        String title,

        @Size(max = 2000, message = "La description ne doit pas dépasser 2000 caractères")
        String description,

        TaskStatus status,
        TaskPriority priority,
        LocalDate dueDate,

        @NotNull(message = "L'identifiant du projet est obligatoire")
        Long projectId
) {}
