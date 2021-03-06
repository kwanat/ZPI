package com.example.kwanat.mma.DB;

import android.util.Log;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

/**
 * Created by Kamil on 10.12.2018.
 */

public class Connection {
    private static final String dbHost="80.211.116.121";
    private static final String dbPort="3306";
    private static final String dbName="zpi";
    private static final String dbUser="zpiuser2";
    private static final String dbPassword= "zpiuser";

    private static final String dbUrl = "jdbc:mysql://" + dbHost +":"+ dbPort +"/" + dbName + "?user=" + dbUser + "&password=" + dbPassword + "&character_set_client=UTF-8&character_set_database=UTF-8&character_set_results=UTF8&character_set_server=UTF-8&character_set_system=UTF-8";

    private static ConnectionSource source;
    private  static Connection instance;

    private Connection()
    {
        Log.i("Connection","Connecting to db");
        try{
            source=new JdbcConnectionSource(dbUrl);
        }catch (SQLException e){
            Log.e("Connection",e.getMessage());
        }
    }

    public static Connection getInstance(){
        if(instance==null){
            instance=new Connection();
        }
        return instance;
    }

    public static ConnectionSource getConnectionSource(){
        return source;
    }




}
