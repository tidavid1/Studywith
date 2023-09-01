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

@RequiredArgsConstructor
@Configuration
public class AWSProvider {
    @Value("${aws.key.access}")
    private String accessKeyId;

    @Value("${aws.key.secret}")
    private String secretAccessKey;

    @Value("${aws.ec2.keypair}")
    private String keyPairName;

    @Value("${aws.ec2.securityGroupId}")
    private String securityGroupId;

    @Value("${aws.ec2.ami}")
    private String ami;

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
                .instanceType(InstanceType.T2_MICRO)
                .imageId(ami)
                .keyName(keyPairName)
                .securityGroupIds(securityGroupId)
                .maxCount(1)
                .minCount(1)
                .build();

        RunInstancesResponse runInstancesResponse = ec2Client.runInstances(runInstancesRequest);
        return runInstancesResponse.instances().get(0).instanceId();
    }

    public String startEC2Instance(String instanceId){
        ec2Client.startInstances(
                StartInstancesRequest.builder()
                        .instanceIds(instanceId)
                        .build()
        );
        return getEC2InstanceInformation(instanceId);
    }

    public String getEC2InstanceInformation(String instanceId){
        boolean ipAddrExist = false;
        String ipAddr = null;
        while(!ipAddrExist){
            DescribeInstancesResponse describeInstancesResponse = ec2Client.describeInstances(
                    DescribeInstancesRequest.builder()
                            .instanceIds(instanceId)
                            .build()
            );
            Instance instance = describeInstancesResponse.reservations().get(0).instances().get(0);
            if (instance.publicIpAddress() != null){
                ipAddrExist = true;
                ipAddr = instance.publicIpAddress();
            }else{
                try{
                    Thread.sleep(3000);
                } catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        }
        return ipAddr;
    }

    public void stopEC2Instance(String instanceId){
        ec2Client.stopInstances(
                StopInstancesRequest.builder()
                        .instanceIds(instanceId)
                        .build()
        );
    }

    public void terminateEC2Instance(String instanceId){
        ec2Client.terminateInstances(
                TerminateInstancesRequest.builder()
                        .instanceIds(instanceId)
                        .build()
        );
    }


}
