package com.tidavid1.Studywith.domain.teaching.service;

import com.tidavid1.Studywith.domain.teaching.dto.TeachingInstanceResponseDto;
import com.tidavid1.Studywith.domain.teaching.entity.Teaching;
import com.tidavid1.Studywith.domain.teaching.exception.CTeachingNotFoundException;
import com.tidavid1.Studywith.domain.teaching.repository.TeachingRepository;
import com.tidavid1.Studywith.domain.user.entity.Role;
import com.tidavid1.Studywith.domain.user.entity.User;
import com.tidavid1.Studywith.domain.usertoken.config.TokenProvider;
import com.tidavid1.Studywith.domain.usertoken.exception.CAccessDeniedException;
import com.tidavid1.Studywith.domain.usertoken.exception.CAccessTokenInvalidException;
import com.tidavid1.Studywith.global.config.AWSProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TeachingInstanceService {
    private final TeachingRepository teachingRepository;
    private final TokenProvider tokenProvider;
    private final AWSProvider awsProvider;



    @Transactional
    public TeachingInstanceResponseDto startTeachingInstance(Long teachingId){
        Teaching teaching = teachingRepository.findByTeachingId(teachingId).orElseThrow(CTeachingNotFoundException::new);
        String publicIpAddress = awsProvider.startEC2Instance(teaching.getInstanceId());
        return new TeachingInstanceResponseDto(teachingId, teaching.getInstanceId(), publicIpAddress);
    }

    @Transactional
    public void stopTeachingInstance(Long teachingId){
        Teaching teaching = teachingRepository.findByTeachingId(teachingId).orElseThrow(CTeachingNotFoundException::new);
        awsProvider.stopEC2Instance(teaching.getInstanceId());
    }

    @Transactional
    public void terminateTeachingInstance(Long teachingId){
        Teaching teaching = teachingRepository.findByTeachingId(teachingId).orElseThrow(CTeachingNotFoundException::new);
        awsProvider.terminateEC2Instance(teaching.getInstanceId());
        teaching.terminateInstanceId();
    }
}
