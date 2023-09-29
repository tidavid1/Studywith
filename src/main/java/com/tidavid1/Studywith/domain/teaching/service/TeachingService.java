package com.tidavid1.Studywith.domain.teaching.service;

import com.tidavid1.Studywith.domain.teaching.dto.TeachingRequestDto;
import com.tidavid1.Studywith.domain.teaching.dto.TeachingResponseDto;
import com.tidavid1.Studywith.domain.teaching.entity.Teaching;
import com.tidavid1.Studywith.domain.teaching.exception.CTeachingAlreadyExistException;
import com.tidavid1.Studywith.domain.teaching.exception.CTeachingEndDateEarlierThenStartDateException;
import com.tidavid1.Studywith.domain.teaching.exception.CTeachingNotFoundException;
import com.tidavid1.Studywith.domain.teaching.repository.TeachingRepository;
import com.tidavid1.Studywith.domain.user.entity.Role;
import com.tidavid1.Studywith.domain.user.entity.User;
import com.tidavid1.Studywith.domain.user.exception.CUserNotFoundException;
import com.tidavid1.Studywith.domain.user.repository.UserRepository;
import com.tidavid1.Studywith.domain.usertoken.config.TokenProvider;
import com.tidavid1.Studywith.domain.usertoken.exception.CAccessDeniedException;
import com.tidavid1.Studywith.global.config.AWSProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeachingService {
    private final TeachingRepository teachingRepository;
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final AWSProvider awsProvider;


    @Transactional
    public Long createClass(TeachingRequestDto teachingRequestDto){
        User teacher = userRepository.findByUserIdWithRole(teachingRequestDto.getTeacherId(), Role.ROLE_Teacher).orElseThrow(CUserNotFoundException::new);
        User student = userRepository.findByUserIdWithRole(teachingRequestDto.getStudentId(), Role.ROLE_Student).orElseThrow(CUserNotFoundException::new);
        if (teachingRepository.findByStudentId(teacher, student).isPresent()){
            throw new CTeachingAlreadyExistException();
        }
        String instanceId = awsProvider.createEC2Instance();
        awsProvider.stopEC2Instance(instanceId);
        return teachingRepository.save(teachingRequestDto.toEntity(teacher, student, instanceId)).getTeachingId();
    }

    @Transactional
    public Long updateEndDate(Long teachingId, TeachingRequestDto teachingRequestDto){
        Teaching teaching = teachingRepository.findByTeachingId(teachingId).orElseThrow(CTeachingNotFoundException::new);
        if (teachingRequestDto.getEndDate().isBefore(teaching.getStartDate())){
            throw new CTeachingEndDateEarlierThenStartDateException();
        }
        teaching.updateEndDate(teachingRequestDto.getEndDate());
        return teaching.getTeachingId();
    }


    @Transactional
    public List<TeachingResponseDto> findAllTeaching(){
        return teachingRepository.findAll().stream().map(TeachingResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public List<TeachingResponseDto> findAllTeachingByTeacher(String accessToken, Long teacherId){
        User user = (User) tokenProvider.getUserByToken(accessToken);
        User teacher = switch (user.getRole()){
            case ROLE_Teacher -> userRepository.findByUserId(user.getUserId()).orElseThrow(CUserNotFoundException::new);
            case ROLE_ADMIN -> userRepository.findByUserId(teacherId).orElseThrow(CUserNotFoundException::new);
            default -> throw new CAccessDeniedException();
        };
        return teachingRepository.findByTeacherId(teacher).stream().map(TeachingResponseDto::new).collect(Collectors.toList());
    }
}
