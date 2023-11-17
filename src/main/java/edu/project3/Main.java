package edu.project3;

import edu.project3.Render.AdocReport;
import edu.project3.Render.MarkDownReport;
import edu.project3.Render.Report;
import edu.project3.Render.ReportHandler;

public class Main {
    public static void main(String[] args) {
        String[] args1 = "--path src/main/resources/project3 --from 2023-08-31 ".split(" ");
        String[] args2 = "--path https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs".split(" ");
        NGINX nginx = NGINX.getInstance();
        nginx.analyze(args1);
        new ReportHandler();
        nginx = NGINX.getInstance();
        nginx.analyze(args2);
        new ReportHandler();



    }
}
