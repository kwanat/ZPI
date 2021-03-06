package com.example.kwanat.mma.DB.Tables;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Kamil on 10.12.2018.
 */
@DatabaseTable(tableName = "Vacation")
public class Vacation {
    public static final String VACATION_ID = "Id_vacation";
    public static final String VACATION_ID_EMPLOYEE = "Id_employee";
    public static final String VACATION_START = "Start_date";
    public static final String VACATION_END = "End_date";
    public static final String VACATION_STATUS = "Status";

    @DatabaseField(generatedId = true, columnName = VACATION_ID)
    private int id;

    @DatabaseField(columnName = VACATION_ID_EMPLOYEE)
    private int id_employee;

    @DatabaseField(columnName = VACATION_START)
    private String start;

    @DatabaseField(columnName = VACATION_END)
    private String end;

    @DatabaseField(columnName = VACATION_STATUS)
    private String status;

    public Vacation()
    {}

    public Vacation(int id, String start, String stop, String status)
    {
        this.id_employee=id;
        this.start=start;
        this.end=stop;
        this.status=status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_employee() {
        return id_employee;
    }

    public void setId_employee(int id_employee) {
        this.id_employee = id_employee;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
