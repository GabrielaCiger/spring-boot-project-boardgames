package org.example.springboot_firstproject.heartbeat;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomHeartbeat implements HeartbeatSensor {

    private Random random = new Random();

    @Override
    public int get() {
        return random.nextInt(120);
    }
}
