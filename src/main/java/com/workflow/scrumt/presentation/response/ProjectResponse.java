package com.workflow.scrumt.presentation.response;

import java.time.LocalDateTime;

public record ProjectResponse(
        Long id,
        String name,
        String description,
        LocalDateTime createdAt
) {
}
