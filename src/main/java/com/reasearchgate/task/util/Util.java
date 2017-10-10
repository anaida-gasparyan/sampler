package com.reasearchgate.task.util;

import java.io.*;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

/**
 * @author anaida.gasparyan
 */
public class Util {

    public static IntStream intStream(InputStream is) {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        Spliterator.OfInt sp = new Spliterators.AbstractIntSpliterator(1000L,
                Spliterator.ORDERED | Spliterator.IMMUTABLE) {
            public boolean tryAdvance(IntConsumer action) {
                try {
                    int ch = reader.read();
                    if (ch == -1) return false;
                    action.accept(ch);
                    return true;
                } catch (IOException ex) {
                    throw new UncheckedIOException(ex);
                }
            }
        };
        return StreamSupport.intStream(sp, false)
                .onClose(() -> {
                    try {
                        reader.close();
                    } catch (IOException ex) {
                        throw new UncheckedIOException(ex);
                    }
                });
    }
}
