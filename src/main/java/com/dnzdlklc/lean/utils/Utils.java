package com.dnzdlklc.lean.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by denizdalkilic on 24/07/2020 @ 15:56.
 */
public class Utils {

    public static List<String> getValidTokens() throws IOException, URISyntaxException {
        Path path = Paths.get(Objects.requireNonNull(Utils.class.getClassLoader().getResource("whitelist.txt")).toURI());
        Stream<String> lines = Files.lines(path);
        List<String> data = lines.collect(Collectors.toList());
        lines.close();

        return data;
    }
}
