package com.example.kwanat.mma.DB.WriteDao;


import android.util.Log;

import com.example.kwanat.mma.DB.Connection;
import com.example.kwanat.mma.DB.Tables.Employee;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

/**
 * Created by Kamil on 10.12.2018.
 */
public class EmployeeWriteDao {


    private static final String TAG = "EmployeeWriteDao";
    private static Dao<Employee, Integer> employeeDao;
    private static EmployeeWriteDao instance;

    public EmployeeWriteDao() {
        try {
            employeeDao = DaoManager.createDao(Connection.getInstance().getConnectionSource(),Employee.class);
        }
        catch(java.sql.SQLException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public boolean save(Employee item) {
        if(item == null) {
            return false;
        }
        try {
            employeeDao.create(item);
        }
        catch(java.sql.SQLException e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
        return true;
    }

    public void update(Employee item) {
        if(item == null) {
            return;
        }
        try {
            employeeDao.update(item);
        } catch (java.sql.SQLException e) {
            Log.e(TAG, e.getMessage());
        }
    }


    public void delete(Employee item) {
        if(item == null) {
            return;
        }
        try {
            employeeDao.delete(item);
        } catch (java.sql.SQLException e) {
            Log.e(TAG, e.getMessage());
        }
    }
}

