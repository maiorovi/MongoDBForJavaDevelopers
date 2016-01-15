package com.tengen;

import spark.Spark;

public class HelloWorldSparkStyle {

    public static void main(String[] args) {
        Spark.get("/", (request, response) -> "Hello World From Spark!");
    }
}
