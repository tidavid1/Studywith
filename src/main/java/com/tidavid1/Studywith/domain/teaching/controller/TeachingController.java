package com.tidavid1.Studywith.domain.teaching.controller;

import com.tidavid1.Studywith.domain.teaching.dto.TeachingRequestDto;
import com.tidavid1.Studywith.domain.teaching.dto.TeachingResponseDto;
import com.tidavid1.Studywith.domain.teaching.service.TeachingService;
import com.tidavid1.Studywith.global.common.response.model.ListResult;
import com.tidavid1.Studywith.global.common.response.model.SingleResult;
import com.tidavid1.Studywith.global.common.response.service.ResponseFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Tag(name = "TeachingController")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/teaching")
public class TeachingController {
    private final TeachingService teachingService;

    @Operation(summary = "수업 전체 검색", description = "수업 전체를 조회합니다.")
    @GetMapping("/find")
    public ListResult<TeachingResponseDto> findAllTeaching() {
        return ResponseFactory.getListResult(teachingService.findAllTeaching());
    }

    @Operation(summary = "선생님 수업 검색", description = "특정 선생님이 진행하는 수업 리스트를 조회합니다.")
    @GetMapping("/teacherId/{teacherId}")
    public ListResult<TeachingResponseDto> findAllTeachingByTeacher(
            @Parameter(description = "teacherId", required = true) @PathVariable Long teacherId) {
        return ResponseFactory.getListResult(teachingService.findAllTeachingByTeacher(teacherId));
    }

    @Operation(summary = "수업 추가", description = "수업 추가")
    @PostMapping("")
    public SingleResult<Long> addTeaching(
            @Parameter(description = "Teaching Request Dto", required = true)
            @RequestBody TeachingRequestDto teachingRequestDto) {
        return ResponseFactory.getSingleResult(teachingService.createClass(teachingRequestDto));
    }


    @Operation(summary = "수업 종료일 지정", description = "수업 종료일 지정")
    @PutMapping("/endDate")
    public SingleResult<Long> updateEndDate(
            @Parameter(description = "teachingId", required = true) @RequestParam Long teachingId,
            @Parameter(description = "endDate", required = true) @RequestParam String endDateStr) {
        TeachingRequestDto teachingRequestDto = TeachingRequestDto.builder().endDate(LocalDate.parse(endDateStr)).build();
        return ResponseFactory.getSingleResult(teachingService.updateEndDate(teachingId, teachingRequestDto));
    }
}
