package controllers.ReportModule;

import services.ReportModule.ReportService;

import java.sql.SQLException;
import java.util.List;

public class ReportActions<ResponseStatus> {
    private ReportService reportService = new ReportService();
    public List<Object> getInventoryReports() throws SQLException{
        return reportService.getListOfInventoryReports();
    }
}
