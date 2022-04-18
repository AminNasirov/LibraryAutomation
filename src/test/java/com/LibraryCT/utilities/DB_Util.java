package com.LibraryCT.utilities;

import java.sql.*;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DB_Util {

    private static Connection conn;
    private  static ResultSet rs;
    private static Statement stmt;

    public static void createConnection(String url,String username, String password){

      //  String url ="jdbc:mysql://34.230.35.214:3306/library2";
        //String username ="library2_client";
       // String password = "6s2LQQTjBcGFfDhY";

        try {
             conn = DriverManager.getConnection(url,username,password);
            System.out.println("CONNECTION SUCCESSFUL");
        } catch (SQLException e) {
            System.out.println("connection has failed "+e.getMessage());

        }
    }

    public static ResultSet runQuery(String query){

        try {
             stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING RESULTSET "+ e.getMessage());
        }
        return rs;
    }


    public static void destroy(){

            try {
                if(rs!=null) {
                    rs.close();
                }

                if(stmt!=null) {
                    stmt.close();
                }

                if(conn!=null) {
                    conn.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

    }


    public static int getRowCount(){
        int rowCount =0;
        try {
            rs.last();
            rowCount=rs.getRow();
            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING ROW COUNT "+ e.getMessage());
        }

        return rowCount;

    }

    public static int getColumnCNT(){
        int colCount =0;

        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            colCount=rsmd.getColumnCount();
        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING THE COLUMNS "+ e.getMessage());
        }
        return colCount;

    }

    public static List<String>  getColumnNames(){

        List<String> colNameList = new ArrayList<>();

        try {
            ResultSetMetaData rsmd = rs.getMetaData();

            for (int i = 1; i <=rsmd.getColumnCount() ; i++) {

               colNameList.add(rsmd.getColumnLabel(i));

            }

        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING THE COLUMN NAMES "+ e.getMessage());
        }

        return colNameList;

    }


    public static List<String> getRowDataAsList(int rowNum){

        List<String> rowDataList = new ArrayList<>();

        try {
            rs.absolute(rowNum);
            for (int i = 1; i <=getColumnCNT() ; i++) {
                String cellValue = rs.getString(i);
                rowDataList.add(cellValue);
            }
            rs.beforeFirst();

        } catch (SQLException e) {
            System.out.println("ERROR WHILE getRowDataAsList "+ e.getMessage());
        }

        return rowDataList;
    }


    public static String getColumnDataAtRow(int rowNum, int columnIndex){

        String result = "";

        try {
            rs.absolute(rowNum);
            result=rs.getString(columnIndex);
            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println("ERROR WHILE getColumnDataRow "+ e.getMessage());
        }

        return result;
    }

    public static String getColumnDataAtRow(int rowNum, String columnName){

        String result ="";

        try {
            rs.absolute(rowNum);
            result= rs.getString(columnName);
            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println("ERROR WHILE getColumnDataRow "+ e.getMessage());
        }

        return result;

    }

    public static List<String> getColumnDataAsList(int columnIndex){

        List<String> result = new ArrayList<>();

        try {
            rs.beforeFirst();

            while (rs.next()){

                result.add(rs.getString(columnIndex));
            }

        } catch (SQLException e) {
            System.out.println("ERROR WHILE getColumnDataAsList "+ e.getMessage());
        }

        return result;
    }


    public static List<String> getColumnDataAsList(String columnName){

        List<String> result = new ArrayList<>();

        try {
            rs.beforeFirst();

            while (rs.next()){

                result.add(rs.getString(columnName));
            }

        } catch (SQLException e) {
            System.out.println("ERROR WHILE getColumnDataAsList "+ e.getMessage());
        }

        return result;
    }


    public static void displayAllData(){

        try {
            rs.beforeFirst();

            while (rs.next()){
                for (int i = 1; i <=getColumnCNT() ; i++) {

                    System.out.print(rs.getString(i)+"\t");

                }
                System.out.println();
            }

        } catch (SQLException e) {
            System.out.println("ERROR WHILE displayAllData "+ e.getMessage());
        }

    }


    public static Map<String,String> getRowMap(int row){

        Map<String,String> rowMap = new LinkedHashMap<>();

        try {
            rs.absolute(row);

            ResultSetMetaData rsmd = rs.getMetaData();

            for (int i = 1; i <=getColumnCNT() ; i++) {

                String colName = rsmd.getColumnLabel(i);
                String cellValue = rs.getString(i);
                rowMap.put(colName,cellValue);
            }
            rs.beforeFirst();

        } catch (SQLException e) {
            System.out.println("ERROR WHILE getRowMap "+ e.getMessage());
        }
        return rowMap;

    }

    public static List<Map<String,String>> getAllDataAsListOfMap(){


        List<Map<String,String>> rowMapList = new ArrayList<>();

        for (int i = 1; i <=getRowCount() ; i++) {
            rowMapList.add(getRowMap(i));
        }

        return rowMapList;
    }

    public static String getFirstData(){

        return getColumnDataAtRow(1,1);
    }








    public static void main(String[] args) throws SQLException {

 // createConnection();
  ResultSet myResult = runQuery("select * from books");
  rs.next();

        System.out.println(rs.getString(1));


        System.out.println(getRowCount());

        System.out.println(getColumnCNT());

        System.out.println(getColumnNames());

        System.out.println(getRowDataAsList(3));

        System.out.println(getColumnDataAtRow(3,"name"));

        System.out.println(getColumnDataAsList(1));
        System.out.println(getColumnDataAsList("author"));

       // displayAllData();

        System.out.println("getRowMap(1) = " + getRowMap(1));

        Map<String,String> thirdRowMap = getRowMap(3);

        System.out.println("get the name "+ thirdRowMap.get("id"));

       // System.out.println(getAllDataAsListOfMap());


        destroy();



    }
}
