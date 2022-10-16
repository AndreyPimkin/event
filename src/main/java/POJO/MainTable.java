package POJO;

import java.util.Date;

public class MainTable {
    public MainTable() {

    }

    public String getEventColumn() {
        return eventColumn;
    }

    public void setEventColumn(String eventColumn) {
        this.eventColumn = eventColumn;
    }

    public Date getDataColumn() {
        return dataColumn;
    }

    public void setDataColumn(Date dataColumn) {
        this.dataColumn = dataColumn;
    }

    public Integer getDayColumn() {
        return dayColumn;
    }

    public void setDayColumn(Integer dayColumn) {
        this.dayColumn = dayColumn;
    }

    public String getCityColumn() {
        return cityColumn;
    }

    public void setCityColumn(String cityColumn) {
        this.cityColumn = cityColumn;
    }

    private String eventColumn;
    private Date dataColumn;
    private Integer dayColumn ;
    private String cityColumn ;

    public MainTable(String eventColumn, Date dataColumn, Integer dayColumn, String cityColumn) {
        this.eventColumn = eventColumn;
        this.dataColumn = dataColumn;
        this.dayColumn = dayColumn;
        this.cityColumn = cityColumn;
    }
}
