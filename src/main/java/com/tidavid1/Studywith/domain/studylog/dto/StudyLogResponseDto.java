package com.tidavid1.Studywith.domain.studylog.dto;

import com.tidavid1.Studywith.domain.studylog.entity.StudyLog;
import com.tidavid1.Studywith.domain.teaching.dto.TeachingResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class StudyLogResponseDto {
    private Long studyLogId;
    private TeachingResponseDto teaching;
    private String studyNote;
    private LocalDate studyDate;

    public StudyLogResponseDto(StudyLog studyLog) {
        this.studyLogId = studyLog.getStudyLogId();
        this.teaching = new TeachingResponseDto(studyLog.getTeaching());
        this.studyNote = studyLog.getStudyNote();
        this.studyDate = studyLog.getStudyDate();
    }
}
