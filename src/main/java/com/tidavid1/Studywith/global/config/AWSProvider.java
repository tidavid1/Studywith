package com.tidavid1.Studywith.global.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.*;

import java.util.Collections;

@RequiredArgsConstructor
@Configuration
public class AWSProvider {
    @Value("${aws.key.access}")
    private String accessKeyId;

    @Value("${aws.key.secret}")
    private String secretAccessKey;

    private Ec2Client ec2Client;

    @PostConstruct
    private void init(){
        AwsCredentialsProvider awsCredentialsProvider = StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKeyId, secretAccessKey));
        ec2Client = Ec2Client.builder()
                .region(Region.AP_NORTHEAST_1)
                .credentialsProvider(awsCredentialsProvider)
                .build();
    }


    public String createEC2Instance(){
        RunInstancesRequest runInstancesRequest = RunInstancesRequest.builder()
                // TODO: need Image, instanceType, etc...
                .build();

        RunInstancesResponse runInstancesResponse = ec2Client.runInstances(runInstancesRequest);
        return runInstancesResponse.instances().get(0).instanceId();
    }

    public void startEC2Instance(String instanceId){
        StartInstancesResponse startInstancesResponse = ec2Client.startInstances(
                StartInstancesRequest.builder()
                        .instanceIds(instanceId)
                        .build()
        );
    }

    public void getEC2InstanceInformation(String instanceId){
        DescribeInstancesResponse describeInstancesResponse = ec2Client.describeInstances(
                DescribeInstancesRequest.builder()
                        .instanceIds(instanceId)
                        .build()
        );
    }

    public void stopEC2Instance(String instanceId){
        StopInstancesResponse stopInstancesResponse = ec2Client.stopInstances(
                StopInstancesRequest.builder()
                        .instanceIds(instanceId)
                        .build()
        );
    }


}
