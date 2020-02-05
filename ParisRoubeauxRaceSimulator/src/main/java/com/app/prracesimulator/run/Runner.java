/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.cyclistsimulatorpb.run;

import com.app.cyclistsimulatorpb.util.DataExtractor;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Gabriel Huertas <gabriel970826@gmail.com>
 */
public class Runner {

    public static void main(String[] args) {
        
        
//        Files.read
//        try (Stream<Path> walk = Files.walk(Paths.get("./resources/maximal_power_output.csv"))) {
//
//		List<String> result = walk.filter(Files::isRegularFile)
//				.map(x -> x.toString()).collect(Collectors.toList());
//
//		result.forEach(System.out::println);
//
//	} catch (IOException e) {
//		e.printStackTrace();
//	}


        DataExtractor dataExtractor = new DataExtractor();
        dataExtractor.readFromCSV("./resources/maximal_power_output.csv");
                
//        DataExtractor dataExtractor = new DataExtractor();
//        dataExtractor.readFromExcelFile("maximal");
    }
}
