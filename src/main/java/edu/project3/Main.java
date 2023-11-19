package edu.project3;

public class Main {
    private Main() {

    }

    public static void main(String[] args) {
        NGINX nginx = new NGINX();
        nginx.analyze(args);
    }
}
