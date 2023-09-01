package com.tidavid1.Studywith.domain.teaching.entity;

import com.tidavid1.Studywith.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "teaching")
@Entity
public class Teaching {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teachingId;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(referencedColumnName = "userId")
    private User teacher;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(referencedColumnName = "userId")
    private User student;

    @Column(nullable = false)
    private String instanceId;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Language language;

    public void updateEndDate(LocalDate endDate){
        this.endDate = endDate;
    }
}
