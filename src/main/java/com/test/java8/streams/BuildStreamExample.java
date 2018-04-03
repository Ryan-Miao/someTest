package com.test.java8.streams;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.Test;

/**
 * Created by Ryan Miao on 4/3/18.
 */
public class BuildStreamExample {

    @Test
    public void testListToStream() {
        List<Integer> list = Lists.newArrayList(1, 2, 3);
        Stream<Integer> stream = list.stream();
    }

    @Test
    public void testArrToStream() {
        String[] arr = new String[]{"a", "b", "c"};
        Stream<String> stream = Arrays.stream(arr);
    }

    @Test
    public void testMapStream() {
        Map<String, Object> map = Maps.newHashMap();
        Stream<Entry<String, Object>> stream = map.entrySet().stream();
    }

    @Test
    public void testValueToStream() {
        Stream<Integer> integerStream = Stream.of(1, 2, 3);
    }

    @Test
    public void testFileToStream() {
        long uniqueWords = 0;
        try (Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(l -> Arrays.stream(l.split(" ")))
                    .distinct()
                    .count();
        } catch (IOException e) {
            //
        }
    }

    @Test
    public void testFunToStream() {
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);

        System.out.println("================");
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(20)
                .map(t -> t[0])
                .forEach(System.out::println);

        System.out.println("================");
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
        System.out.println("================");

        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;

            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);
    }

}
