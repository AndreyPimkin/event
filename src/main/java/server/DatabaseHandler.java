package server;


import com.example.event.Authorization;

import java.sql.*;


public class DatabaseHandler extends Configs {
    Connection dbConnection;
    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?verifyServerCertificate=false"+
                "&useSSL=false"+
                "&requireSSL=false"+
                "&useLegacyDatetimeCode=false"+
                "&amp"+
                "&serverTimezone=UTC";

        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }
    public ResultSet getEventTable(){
        ResultSet resSet = null;
        String select = "SELECT events.event, events.date, events.days, city.name " +
                "FROM events INNER JOIN city ON events.city_id = city.city_id";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public ResultSet getModerator(User user){
        ResultSet resSet = null;

        String select = "SELECT FROM moderaotr WHERE modereator_id = ? AND password =?"  ;

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select); // Выполняем запрос

            prSt.setString(1, user.getEmail());
            prSt.setString(2, user.getPassword());
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public ResultSet getOrganizator(User user){
        ResultSet resSet = null;

        String select = "SELECT FROM organizator WHERE organizator_id = ? AND password =?"  ;

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select); // Выполняем запрос

            prSt.setString(1, user.getEmail());
            prSt.setString(2, user.getPassword());
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }
    public ResultSet getJury(User user){
        ResultSet resSet = null;

        String select = "SELECT FROM jury WHERE jury_id = ? AND password =?"  ;

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select); // Выполняем запрос

            prSt.setString(1, user.getEmail());
            prSt.setString(2, user.getPassword());
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }
    public ResultSet getMember(User user){
        ResultSet resSet = null;

        String select = "SELECT FROM member WHERE member_id = ? AND password =?"  ;

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select); // Выполняем запрос

            prSt.setString(1, user.getEmail());
            prSt.setString(2, user.getPassword());
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }




    public ResultSet  getName(User user){
        ResultSet resSet = null;
        String select = "SELECT Firstname FROM " + Authorization.role + " WHERE " +  Authorization.roleID + " =?" + " AND passsword =?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getEmail());
            prSt.setString(2, user.getPassword());
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;

    }





}
