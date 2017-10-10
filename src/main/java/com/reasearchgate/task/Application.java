package com.reasearchgate.task;

import com.reasearchgate.task.sampler.SamplerFactory;
import com.reasearchgate.task.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author anaida.gasparyan
 */
public class Application {

    public static void main(String[] args) {

        getSampleSize(args).ifPresent(size -> {
            long start = System.currentTimeMillis();

            List<Integer> sampleChars1 = Util.intStream(System.in)
                    .collect(
                            ArrayList::new, //supplier
                            SamplerFactory.getSampler(SamplerFactory.SamplerType.RESERVOIR, size), //accumulator
                            List::addAll  // combiner
                    );

            List<Character> sampleChars = sampleChars1.stream()
                    .map(ch -> (char) ch.intValue())
                    .collect(Collectors.toList());

            System.out.println("\n------------- SAMPLE ---------------");
            System.out.println(sampleChars);
            System.out.println("------------------------------------\n");

            System.err.println("Time: " + (System.currentTimeMillis() - start) / 100 + "ms");
        });
    }

    private static Optional<Integer> getSampleSize(String[] args) {
        if (args.length != 1) {
            System.out.println("Please enter the sample size");
            return Optional.empty();
        }

        try {
            return Optional.of(Integer.parseInt(args[0]));
        } catch (NumberFormatException e) {
            System.out.println("Please enter the sample size as integer");
            return Optional.empty();
        }
    }
}
