package com.moho.commons.model;

import com.moho.commons.poi.annotation.Excel;

import java.time.LocalDateTime;
import java.util.Date;

public class ExcelModel {

    @Excel(title = "ID")
    private String id;

    @Excel(title = "INT1")
    private Integer int1;

    @Excel(title = "DATETIME")
    private LocalDateTime dateTime;

    @Excel(title = "DATE")
    private Date date;

    @Excel(title = "STATUS", convert = "{'1':'N', '2':'Y'}")
    private String status;

    private String str;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getInt1() {
        return int1;
    }

    public void setInt1(Integer int1) {
        this.int1 = int1;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
