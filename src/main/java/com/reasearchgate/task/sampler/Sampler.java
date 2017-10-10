package com.reasearchgate.task.sampler;

import java.util.List;
import java.util.function.ObjIntConsumer;

/**
 * @author anaida.gasparyan
 */
@FunctionalInterface
interface Sampler extends ObjIntConsumer<List<Integer>> {

}
