package server;


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

    public ResultSet getUserLoginAndPassword(User user){
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.NAME_TABLE + " Where "+
                Const.EMAIL + " =? AND " + Const.PASSWORD + " =?";

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

    public ResultSet  getRoles(User user){
        ResultSet resSet = null;
        String select = "SELECT " + Const.ROLES + " FROM " + Const.NAME_TABLE + " WHERE "+
                Const.EMAIL + " =?" + " AND " + Const.PASSWORD + " =?";

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

    public ResultSet  getName(User user){
        ResultSet resSet = null;
        String select = "SELECT Firstname FROM " + Const.NAME_TABLE + " WHERE "+
                Const.EMAIL + " =?" + " AND " + Const.PASSWORD + " =?";

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
