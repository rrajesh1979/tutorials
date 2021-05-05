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

}
