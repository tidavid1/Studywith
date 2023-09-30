package com.tidavid1.Studywith.domain.studylog.entity;

import com.tidavid1.Studywith.domain.teaching.entity.Teaching;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "studylog")
@Entity
public class StudyLog {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long studyLogId;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Teaching.class)
    @JoinColumn(referencedColumnName = "teachingId")
    private Teaching teaching;

    @Column(nullable = false)
    private String studyNote;

    @Column(nullable = false)
    private LocalDate studyDate;

    public void updateStudyNote(String studyNote) {
        this.studyNote = studyNote;
    }
}
