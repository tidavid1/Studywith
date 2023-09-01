package com.tidavid1.Studywith.domain.teaching.dto;

import com.tidavid1.Studywith.domain.teaching.entity.Language;
import com.tidavid1.Studywith.domain.teaching.entity.Teaching;
import com.tidavid1.Studywith.domain.user.dto.UserResponseDto;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class TeachingResponseDto {
    private final Long teachingId;
    private final UserResponseDto teacher;
    private final UserResponseDto student;
    private final String instanceId;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Language language;

    public TeachingResponseDto(Teaching teaching){
        this.teachingId = teaching.getTeachingId();
        this.teacher = new UserResponseDto(teaching.getTeacher());
        this.student = new UserResponseDto(teaching.getStudent());
        this.instanceId = teaching.getInstanceId();
        this.startDate = teaching.getStartDate();
        this.endDate = teaching.getEndDate();
        this.language = teaching.getLanguage();
    }
}
