package com.tidavid1.Studywith.domain.teaching.dto;

import com.tidavid1.Studywith.domain.teaching.entity.Language;
import com.tidavid1.Studywith.domain.teaching.entity.Teaching;
import com.tidavid1.Studywith.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeachingRequestDto {
    private Long teacherId;
    private Long studentId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Language language;

    public Teaching toEntity(User teacher, User student, String instanceId) {
        return Teaching.builder()
                .teacher(teacher)
                .student(student)
                .instanceId(instanceId)
                .startDate(startDate)
                .endDate(endDate)
                .language(language)
                .build();
    }
}
