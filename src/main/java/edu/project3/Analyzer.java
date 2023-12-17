package edu.project3;

import edu.project3.Report.Report;
import edu.project3.Report.ReportHandler;
import edu.project3.parsers.AbstractParser;
import edu.project3.parsers.CmdArgs;
import edu.project3.parsers.ParseManager;

public final class Analyzer {

    public Analyzer() {
    }

    //CHECKSTYLE:OFF: checkstyle:UncommentedMain
    public static void main(String[] args) {
        //String[] args1 = "--path src/main/resources/project3/test --format markdown".split(" ");
        Analyzer analyzer = new Analyzer();
        analyzer.analyze(args);
    }

    public void analyze(String[] args) {
        CmdArgs cmdArgs = CmdArgs.parse(args);

        ParseManager manager = new ParseManager(cmdArgs.path(), cmdArgs.from(), cmdArgs.to());
        AbstractParser abstractParser = manager.getParser();

        LogRecord logRecord = abstractParser.parseResource();

        Statistics statistics =
            new Statistics(cmdArgs.path(), cmdArgs.from(), cmdArgs.to(), logRecord);
        statistics.makeStatistics();
        ReportHandler reportHandler = new ReportHandler(cmdArgs.format());
        Report report = reportHandler.createReport();
        String result = report.report(statistics);
        report.writeToFile(result);
    }
}
