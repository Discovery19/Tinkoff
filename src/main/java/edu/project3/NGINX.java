package edu.project3;

import edu.project3.parsers.AbstractParser;
import edu.project3.parsers.CmdParse;
import edu.project3.parsers.ParseHandler;
import java.util.Map;

public final class NGINX {

    private static NGINX INSTANCE;
    private NGINX() {
    }

    public static NGINX getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new NGINX ();
        }

        return INSTANCE;
    }
    public void analyze(String[] args){
        System.out.println("Start analyzing NGINX");
        new CmdParse();
        CmdParse.parse(args);
        System.out.println(CmdParse.getPath());
        ParseHandler.checkPath();
        System.out.println(AbstractParser.getAdress_map().containsKey("93.180.71.3"));
    }

    // getters and setters
}
