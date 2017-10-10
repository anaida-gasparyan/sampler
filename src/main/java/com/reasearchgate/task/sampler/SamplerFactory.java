package com.reasearchgate.task.sampler;

/**
 * @author anaida.gasparyan
 */
public class SamplerFactory {

    public enum SamplerType {
        RESERVOIR,
        OTHER
    }

    public static Sampler getSampler(SamplerType type, int sampleSize) {
        switch (type) {
            case RESERVOIR:
                return new ReservoirSampler(sampleSize);
            default:
                throw new UnsupportedOperationException();
        }
    }
}
