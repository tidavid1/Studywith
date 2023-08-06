package com.tidavid1.Studywith.domain.studylog.dto;

import com.tidavid1.Studywith.domain.studylog.entity.StudyLog;
import com.tidavid1.Studywith.domain.teaching.entity.Teaching;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudyLogRequestDto {
    private Long teachingId;
    private String studyNote;
    private LocalDate studyDate;

    public StudyLog toEntity(Teaching teaching){
        return StudyLog.builder()
                .teaching(teaching)
                .studyNote(studyNote)
                .studyDate(studyDate)
                .build();
    }
}
