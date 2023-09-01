package com.tidavid1.Studywith.global.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AWSProviderTest {
    @Autowired
    AWSProvider awsProvider;

    String instanceId;

    @DisplayName("Test Create EC2 Instance")
    @Test
    void testCreateEC2Instance(){
        instanceId = awsProvider.createEC2Instance();
        System.out.println(instanceId);
    }

    @DisplayName("Test Stop EC2 Instance")
    @Test
    void testStopEC2Instance(){
        awsProvider.stopEC2Instance(instanceId);
    }

    @DisplayName("Test Start EC2 Instance")
    @Test
    void testStartEC2Instance(){
        System.out.println(awsProvider.startEC2Instance(instanceId));
    }

    @DisplayName("Test Get EC2 Instance Information")
    @Test
    void testGetEC2InstanceInformation(){
        System.out.println(awsProvider.getEC2InstanceInformation(instanceId));
    }

    @DisplayName("Test Terminate EC2 Instance")
    @Test
    void testTerminateEc2Instance(){
        awsProvider.terminateEC2Instance(instanceId);
    }
}