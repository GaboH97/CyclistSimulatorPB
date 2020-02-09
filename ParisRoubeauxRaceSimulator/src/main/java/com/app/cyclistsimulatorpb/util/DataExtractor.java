package com.app.cyclistsimulatorpb.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Gabriel Huertas <gabriel970826@gmail.com>
 */
public class DataExtractor {

    public DataExtractor() {
    }

    /**
     *
     * @param filePath
     * @param fileName
     * @return Las lineas del archivo que se está leyendo
     */
    public static List<String> readFromFile(String filePath) {
        try (Stream<String> lines = Files.lines(Paths.get(filePath)).skip(1)) {
            return lines.collect(Collectors.toList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * 
     * @param filePath
     * @return Una matriz con los datos leídos del CSV
     */
    public static List<List<Double>> readFromCSV(String filePath) {
        List<String> lines = readFromFile(filePath);
        List<List<Double>> matrix = new ArrayList<>(1);
        
        lines.forEach(l -> {
            matrix.add(Stream.of(l.split(";")).mapToDouble(Double::valueOf).boxed().collect(Collectors.toList()));
        });
        
        return matrix;
    }
}
