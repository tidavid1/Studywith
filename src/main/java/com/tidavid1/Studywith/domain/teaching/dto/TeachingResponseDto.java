package com.tidavid1.Studywith.domain.teaching.dto;

import com.tidavid1.Studywith.domain.teaching.entity.Language;
import com.tidavid1.Studywith.domain.teaching.entity.Teaching;
import com.tidavid1.Studywith.domain.user.dto.UserResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class TeachingResponseDto {
    private Long teachingId;
    private UserResponseDto teacher;
    private UserResponseDto student;
    private LocalDate startDate;
    private LocalDate endDate;
    private Language language;

    public TeachingResponseDto(Teaching teaching){
        this.teachingId = teaching.getTeachingId();
        this.teacher = new UserResponseDto(teaching.getTeacher());
        this.student = new UserResponseDto(teaching.getStudent());
        this.startDate = teaching.getStartDate();
        this.endDate = teaching.getEndDate();
        this.language = teaching.getLanguage();
    }
}
