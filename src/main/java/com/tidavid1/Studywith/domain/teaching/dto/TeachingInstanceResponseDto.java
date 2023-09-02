package com.tidavid1.Studywith.domain.teaching.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TeachingInstanceResponseDto {
    private final Long teachingId;
    private final String instanceId;
    private final String publicIpAddress;
}
