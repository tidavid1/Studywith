package com.tidavid1.Studywith.domain.studylog.service;

import com.tidavid1.Studywith.domain.studylog.dto.StudyLogRequestDto;
import com.tidavid1.Studywith.domain.studylog.dto.StudyLogResponseDto;
import com.tidavid1.Studywith.domain.studylog.entity.StudyLog;
import com.tidavid1.Studywith.domain.studylog.exception.CStudyLogAlreadyExistException;
import com.tidavid1.Studywith.domain.studylog.exception.CStudyLogNotFoundException;
import com.tidavid1.Studywith.domain.studylog.repository.StudyLogRepository;
import com.tidavid1.Studywith.domain.teaching.entity.Teaching;
import com.tidavid1.Studywith.domain.teaching.exception.CTeachingNotFoundException;
import com.tidavid1.Studywith.domain.teaching.repository.TeachingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudyLogService {
    private final StudyLogRepository studyLogRepository;
    private final TeachingRepository teachingRepository;

    @Transactional
    public Long createStudyLog(StudyLogRequestDto studyLogRequestDto) {
        Teaching teaching = teachingRepository.findByTeachingId(studyLogRequestDto.getTeachingId()).orElseThrow(CTeachingNotFoundException::new);
        if (studyLogRepository.findByTeachingWithStudyDate(teaching, studyLogRequestDto.getStudyDate()).isPresent()) {
            throw new CStudyLogAlreadyExistException();
        }
        return studyLogRepository.save(studyLogRequestDto.toEntity(teaching)).getStudyLogId();
    }

    @Transactional
    public Long updateStudyNote(Long studyLogId, StudyLogRequestDto studyLogRequestDto) {
        StudyLog studyLog = studyLogRepository.findByStudyLogId(studyLogId).orElseThrow(CStudyLogNotFoundException::new);
        studyLog.updateStudyNote(studyLogRequestDto.getStudyNote());
        return studyLog.getStudyLogId();
    }

    @Transactional
    public List<StudyLogResponseDto> getStudyLogWithTeaching(Long teachingId) {
        Teaching teaching = teachingRepository.findByTeachingId(teachingId).orElseThrow(CTeachingNotFoundException::new);
        return studyLogRepository.findAllStudyLogWithTeaching(teaching).stream().map(StudyLogResponseDto::new).collect(Collectors.toList());
    }


    @Transactional
    public void removeStudyLog(StudyLogRequestDto studyLogRequestDto) {
        Teaching teaching = teachingRepository.findByTeachingId(studyLogRequestDto.getTeachingId()).orElseThrow(CTeachingNotFoundException::new);
        StudyLog studyLog = studyLogRepository.findByTeachingWithStudyDate(teaching, studyLogRequestDto.getStudyDate()).orElseThrow(CStudyLogNotFoundException::new);
        studyLogRepository.delete(studyLog);
    }
}
