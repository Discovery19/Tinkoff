package edu.project3;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[] args1 = "--path src/main/resources/project3/test".split(" ");
        NGINX nginx = NGINX.getInstance();
        System.out.println(Arrays.toString(args1));
        nginx.analyze(args1);
    }
}
