package com.tidavid1.Studywith.domain.studylog.controller;

import com.tidavid1.Studywith.domain.studylog.dto.StudyLogRequestDto;
import com.tidavid1.Studywith.domain.studylog.dto.StudyLogResponseDto;
import com.tidavid1.Studywith.domain.studylog.entity.StudyLog;
import com.tidavid1.Studywith.domain.studylog.service.StudyLogService;
import com.tidavid1.Studywith.global.common.response.model.CommonResult;
import com.tidavid1.Studywith.global.common.response.model.ListResult;
import com.tidavid1.Studywith.global.common.response.model.SingleResult;
import com.tidavid1.Studywith.global.common.response.service.ResponseFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "StudyLogController")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class StudyLogController {
    private final StudyLogService studyLogService;

    @Operation(summary = "Study Log 추가", description = "Study Log를 추가합니다.")
    @PostMapping("/studyLog")
    public SingleResult<Long> addStudyLog(
            @Parameter(description = "StudyLog Request Dto", required = true)
            @RequestBody StudyLogRequestDto studyLogRequestDto){
        return ResponseFactory.getSingleResult(studyLogService.createStudyLog(studyLogRequestDto));
    }

    @Operation(summary = "Study Note Update")
    @PutMapping("/studyLog/studyNote")
    public SingleResult<Long> updateStudyNote(
            @Parameter(description = "studyLogId", required = true) @RequestParam Long studyLogId,
            @Parameter(description = "studyNote", required = true) @RequestParam String studyNote){
        StudyLogRequestDto studyLogRequestDto = StudyLogRequestDto.builder().studyNote(studyNote).build();
        return ResponseFactory.getSingleResult(studyLogService.updateStudyNote(studyLogId, studyLogRequestDto));
    }

    @Operation(summary = "Delete Study Log")
    @DeleteMapping("/studyLog")
    public CommonResult deleteStudyLog(
            @Parameter(description = "studyLogId", required = true)
            @RequestBody StudyLogRequestDto studyLogRequestDto){
        studyLogService.removeStudyLog(studyLogRequestDto);
        return ResponseFactory.getSuccessResult();
    }

    @GetMapping("/studyLog")
    public ListResult<StudyLogResponseDto> getAllStudyLogWithTeaching(
            @Parameter(description = "teachingId")
            @RequestParam Long teachingId){
        return ResponseFactory.getListResult(studyLogService.getStudyLogWithTeaching(teachingId));
    }
}
