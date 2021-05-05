package com.graphqljava.tutorial.marsweather;

import com.google.common.collect.ImmutableMap;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class GraphQLDataFetchers {
    private static List<Map<String, String>> weatherData = Arrays.asList(
            ImmutableMap.of("id", "861",
                    "date", "29-Apr-2021",
                    "avgPressure", "744.4",
                    "roverImageId", "rover-861"),
            ImmutableMap.of("id", "862",
                    "date", "30-Apr-2021",
                    "avgPressure", "745.4",
                    "roverImageId", "rover-862")
    );

    private static List<Map<String, String>> roverPhoto = Arrays.asList(
            ImmutableMap.of("id", "rover-861",
                    "img_src", "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/fcam/FLB_486265257EDR_F0481570FHAZ00323M_.JPG",
                    "name", "Curiosity"),
            ImmutableMap.of("id", "rover-862",
                    "img_src", "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/fcam/FLB_486265257EDR_F0481570FHAZ00323M_.JPG",
                    "name", "Curiosity")
    );

    /*private static List<Map<String, String>> books = Arrays.asList(
            ImmutableMap.of("id", "book-1",
                    "name", "Harry Potter and the Philosopher's Stone",
                    "pageCount", "223",
                    "authorId", "author-1"),
            ImmutableMap.of("id", "book-2",
                    "name", "Moby Dick",
                    "pageCount", "635",
                    "authorId", "author-2"),
            ImmutableMap.of("id", "book-3",
                    "name", "Interview with the vampire",
                    "pageCount", "371",
                    "authorId", "author-3")
    );

    private static List<Map<String, String>> authors = Arrays.asList(
            ImmutableMap.of("id", "author-1",
                    "firstName", "Joanne",
                    "lastName", "Rowling"),
            ImmutableMap.of("id", "author-2",
                    "firstName", "Herman",
                    "lastName", "Melville"),
            ImmutableMap.of("id", "author-3",
                    "firstName", "Anne",
                    "lastName", "Rice")
    );*/

    public DataFetcher getWeatherBySol() {
        return dataFetchingEnvironment -> {
            String solId = dataFetchingEnvironment.getArgument("id");
            return weatherData
                    .stream()
                    .filter(weather -> weather.get("id").equals(solId))
                    .findFirst()
                    .orElse(null);
        };
    }

    public DataFetcher getRoverPhoto() {
        return dataFetchingEnvironment -> {
            Map<String, String> weather = dataFetchingEnvironment.getSource();
            String roverId = weather.get("roverImageId");
//            String roverId = dataFetchingEnvironment.getArgument("id");
            return roverPhoto
                    .stream()
                    .findFirst()
                    .filter(photo -> photo.get("id").equals(roverId))
                    .orElse(null);
        };
    }

    /*public DataFetcher getBookByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String bookId = dataFetchingEnvironment.getArgument("id");
            return books
                    .stream()
                    .filter(book -> book.get("id").equals(bookId))
                    .findFirst()
                    .orElse(null);
        };
    }

    public DataFetcher getAuthorDataFetcher() {
        return dataFetchingEnvironment -> {
            Map<String, String> book = dataFetchingEnvironment.getSource();
            String authorId = book.get("authorId");
            return authors
                    .stream()
                    .filter(author -> author.get("id").equals(authorId))
                    .findFirst()
                    .orElse(null);
        };
    }*/
}
