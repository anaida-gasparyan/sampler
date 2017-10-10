package com.reasearchgate.task.sampler;

import lombok.NonNull;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

/**
 * @author anaida.gasparyan
 */
public class ReservoirSampler implements Sampler {

    private final int sampleSize;
    private final Random rand = new SecureRandom();
    private long count = 0;

    private final int sampleSizePlusOne;

    ReservoirSampler(@NonNull Integer sampleSize) {
        this.sampleSize = sampleSize;
        this.sampleSizePlusOne = sampleSize + 1;
    }

    @Override
    public void accept(List<Integer> characters, int character) {
        if (characters.size() < sampleSize) {
            characters.add(character); // take next
        } else {
            int randIndex = (int) (rand.nextDouble() * (sampleSizePlusOne + (count++)));
            if (randIndex < sampleSize) {
                characters.set(randIndex, character); // replace previous result
            }
        }
    }
}
