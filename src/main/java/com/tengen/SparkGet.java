package com.tengen;

import spark.Spark;

public class SparkGet {

    public static void main(String[] args) {
        Spark.get("/", (request,response) -> "Hello World");

        Spark.get("/test", (request,response) -> "This is a test page\n");

        Spark.get("/echo/:things", (request,response) -> request.params().get(":things"));
    }

}
