package com.devflow.dto.project;

import com.devflow.entity.ProjectStatus;

import java.time.LocalDateTime;

public record ProjectResponse(
        Long id,
        String name,
        String description,
        ProjectStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
