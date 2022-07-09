package controllers;

import models.ReportModel;
import models.ResponseBody;
import services.ReportService;
import utils.ParserObj;

import java.sql.SQLException;
import java.util.List;

public class ReportController {
    static ResponseBody responseBody = new ResponseBody();
    static ParserObj parse = new ParserObj();

    public static List<Object> getReports() throws SQLException {
        ReportModel result = parse.parseData(ReportService.getReportInformation(), ReportModel.class);
        return ReportService.getReportInformation();
    }
}
