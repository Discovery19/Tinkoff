package edu.project3;

import edu.project3.Report.Report;
import edu.project3.Report.ReportHandler;
import edu.project3.parsers.CmdParse;
import edu.project3.parsers.ParseHandler;

public final class NGINX {

    public NGINX() {
    }

    public void analyze(String[] args) {
        CmdParse cmdParse = new CmdParse();
        cmdParse.parse(args);

        Statistics statistics = new Statistics(cmdParse);
        ParseHandler handler = new ParseHandler(statistics);
        handler.parse();

        ReportHandler reportHandler = new ReportHandler();
        Report report = reportHandler.handler(statistics);
        report.report(statistics);
    }
}
