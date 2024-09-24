package dat;

import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.javalin.config.JavalinConfig;

public class Main {

    private static Map<Integer, String> dogs = new HashMap<>();


    public static void main(String[] args) {
        dogs.put(1, "Golden Retriever");
        dogs.put(2, "German Shepherd");
        dogs.put(3, "Bulldog");
        dogs.put(4, "Rottweiler");
        dogs.put(5, "Pitbull");
        dogs.put(6, "Mastiff");


        var app = Javalin.create((config) -> {
            config.router.contextPath = "/api/dog";
            config.bundledPlugins.enableRouteOverview("/routes");
        });

        app.start(8080);

        app.get("/", ctx -> ctx.result("Hello World"));
        app.get("/api/dog", ctx -> ctx.json(dogs, String.class));

        app.post("/dogs", ctx -> {
            String dog = ctx.body();
            dogs.put(dog);
            ctx.status(201);
            ctx.result(dog);
        });


    }
}