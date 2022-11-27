package server;


import POJO.ForEvent;
import POJO.Org;
import com.example.event.Authorization;
import com.example.event.Controller;

import java.sql.*;


public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?verifyServerCertificate=false" +
                "&useSSL=false" +
                "&requireSSL=false" +
                "&useLegacyDatetimeCode=false" +
                "&amp" +
                "&serverTimezone=UTC";

        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public ResultSet getEventTable() {
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

    public ResultSet getModerator(User user) {
        ResultSet resSet = null;

        String select = "SELECT * FROM moderator WHERE moderator_id = ? AND password =?";

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

    public ResultSet getModeratorEvent() {
        ResultSet resSet = null;

        String select = "SELECT * FROM moderator";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select); // Выполняем запрос
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public ResultSet getCityEvent() {
        ResultSet resSet = null;

        String select = "SELECT * FROM city";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select); // Выполняем запрос
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public ResultSet getOrganizator(User user) {
        ResultSet resSet = null;

        String select = "SELECT * FROM organizator WHERE organizator_id = ? AND password =?";

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

    public ResultSet getOrganizatorFull(User user) {
        ResultSet resSet = null;

        String select = "SELECT organizator.*, country.name, country.code FROM organizator " +
                "INNER JOIN country ON organizator.country_id = country.country_id " +
                "WHERE organizator_id = ? AND password =?";

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


    public ResultSet getJury(User user) {
        ResultSet resSet = null;

        String select = "SELECT * FROM jury WHERE jury_id = ? AND password =?";

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

    public ResultSet getMember(User user) {
        ResultSet resSet = null;

        String select = "SELECT * FROM member WHERE member_id = ? AND password =?";

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

    public ResultSet getMemberEvent() {
        ResultSet resSet = null;

        String select = "SELECT * FROM member";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select); // Выполняем запрос

            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }



    public ResultSet getName(User user) {
        ResultSet resSet = null;
        String select = "SELECT full_name FROM " + Authorization.role + " WHERE " + Authorization.roleID + " =?" + " AND password =?";

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


    public ResultSet getImage(User user) {
        ResultSet resSet = null;
        String select = "SELECT image FROM " + Authorization.role + " WHERE " + Authorization.roleID + " =?" + " AND password =?";

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

    public ResultSet getID() {
        ResultSet resSet = null;
        String select = "SELECT event_id FROM events WHERE event='" + String.valueOf(Controller.val) + "'";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public void changePassword(Org org) {
        String select = "UPDATE organizator SET " +
                "password = ? WHERE organizator_id = " + Authorization.ID;

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, org.getPasswordOneText());
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void changePerson(Org org) {
        String select = "UPDATE organizator SET " +
                "full_name = ?," +
                "male = ?," +
                "mail =?," +
                "birthday = ?," +
                "phone = ?," +
                "country_id = (SELECT country_id FROM country WHERE name = ?)" +
                "WHERE organizator_id = " + Authorization.ID + " AND password = "+ Authorization.passwordUser;

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, org.getFullNameText());
            prSt.setString(2, org.getMaleText());
            prSt.setString(3, org.getEmailText());
            prSt.setString(4, org.getBirthdayText());
            prSt.setString(5, org.getNumberText());
            prSt.setString(6, org.getCountryText());
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void addAct(ForEvent forEvent) {
        String select = "INSERT INTO act(number, name, date_from, days, activity, day, time_from, moderator, jury_1, " +
                "jury_2, jury_3, jury_4, jury_5, winner) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, forEvent.getNumber());
            prSt.setString(2, forEvent.getName());
            prSt.setString(3, forEvent.getDateFrom());
            prSt.setString(4, forEvent.getDaysEvent());
            prSt.setString(5, forEvent.getActivity());
            prSt.setString(6, forEvent.getDayActivity());
            prSt.setString(7, forEvent.getTimeFrom());
            prSt.setString(8, forEvent.getModerator());
            prSt.setString(9, forEvent.getJury1());
            prSt.setString(10, forEvent.getJury2());
            prSt.setString(11, forEvent.getJury3());
            prSt.setString(12, forEvent.getJury4());
            prSt.setString(13, forEvent.getJury5());
            prSt.setString(14, forEvent.getWinner());
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addEvent(ForEvent forEvent) {
        String select = "INSERT INTO events(event, date, days, city_id) VALUES (?, ?, ?, ?) ";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, forEvent.getName());
            prSt.setString(2, forEvent.getDateFrom());
            prSt.setString(3, forEvent.getDaysEvent());
            prSt.setString(4, forEvent.getCityID());
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getEvent(ForEvent forEvent) {
        ResultSet resSet = null;
        String select = "SELECT * FROM events WHERE event = ?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, forEvent.getName());
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public ResultSet getJuryEvent() {
        ResultSet resSet = null;
        String select = "SELECT * FROM jury";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }



}







