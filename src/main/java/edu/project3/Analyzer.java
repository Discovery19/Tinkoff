package edu.project3;

import edu.project3.Report.Report;
import edu.project3.Report.ReportHandler;
import edu.project3.parsers.AbstractParser;
import edu.project3.parsers.CmdArgs;
import edu.project3.parsers.ParseHandler;

public final class Analyzer {

    public Analyzer() {
    }

    //CHECKSTYLE:OFF: checkstyle:UncommentedMain
    public static void main(String[] args) {
        Analyzer nginx = new Analyzer();
        nginx.analyze(args);
    }

    public void analyze(String[] args) {
        CmdArgs cmdParser = CmdArgs.parse(args);

        ParseHandler handler = new ParseHandler(cmdParser.path(), cmdParser.from(), cmdParser.to());
        AbstractParser abstractParser = handler.parse();

        Statistics statistics =
            new Statistics(cmdParser.path(), cmdParser.from(), cmdParser.to(), abstractParser.getLogRecord());
        statistics.makeStatistics();
        ReportHandler reportHandler = new ReportHandler(cmdParser.format());
        Report report = reportHandler.handler();
        report.report(statistics);
    }
}
