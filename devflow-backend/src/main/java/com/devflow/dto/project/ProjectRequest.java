package com.devflow.dto.project;

import com.devflow.entity.ProjectStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProjectRequest(
        @NotBlank(message = "Le nom du projet est obligatoire")
        @Size(max = 100, message = "Le nom ne doit pas dépasser 100 caractères")
        String name,

        @Size(max = 1000, message = "La description ne doit pas dépasser 1000 caractères")
        String description,

        ProjectStatus status
) {}
