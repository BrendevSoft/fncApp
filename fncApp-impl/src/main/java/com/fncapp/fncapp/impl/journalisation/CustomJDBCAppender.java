/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.impl.journalisation;

/**
 *
 * @author PaulAbram
 */
import com.fncapp.fncapp.api.api.utils.Convertiseur;
import com.fncapp.fncapp.api.api.utils.IpAdressUtils;
import com.fncapp.fncapp.impl.shiro.EntityRealm;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

public class CustomJDBCAppender extends AppenderSkeleton {

    private String user;
    private String password;
    private String driver;
    private String URL;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    @Override
    public void close() {
    }

    @Override
    public boolean requiresLayout() {
        return true;
    }

    private PreparedStatement pst;
    private ResultSet rst;
    private Statement st;
    int countLogs;
    private String sql = "insert into logs (id,log_action,log_date,log_date_heure,log_remote_ip,log_remote_mac,utilisateur) values(?,?,?,?,?,?,?)";

    @Override
    protected void append(LoggingEvent event) {
        try {

            if (!event.getLoggerName().equals("org.apache.shiro.realm.AuthorizingRealm")) {
                Class.forName(driver);
                Connection conn = DriverManager.getConnection(URL, user, password);
                st = conn.createStatement();
                rst = st.executeQuery("select count(*) as log from logs");
                countLogs = 1;

                while (rst.next()) {
                    countLogs = rst.getInt("log") + 1;
                }
                rst.close();

                pst = conn.prepareStatement(sql);

                pst.setLong(1, Long.parseLong(String.valueOf(countLogs)));

                pst.setString(2, event.getMessage().toString());

                pst.setDate(3, new java.sql.Date(System.currentTimeMillis()));

                pst.setString(4, Convertiseur.getHeure(Calendar.getInstance().getTime()));

                pst.setString(5, IpAdressUtils.getIpAdresse());

                pst.setString(6, IpAdressUtils.getMacAddress());

                pst.setLong(7, EntityRealm.getUserId());

                /*     pst.setLong(1, Long.parseLong(String.valueOf(countLogs)));

                 pst.setDate(2, new java.sql.Date(System.currentTimeMillis()));

                 pst.setString(3, Convertiseur.getHeure(Calendar.getInstance().getTime()));

                 pst.setString(4, event.getLevel().toString());

                 if (!event.getLoggerName().equals("org.apache.shiro.realm.AuthorizingRealm")) {
                 pst.setString(5, event.getLoggerName());
                 }

                 pst.setString(6, event.getMessage().toString());

                 pst.setString(7, EntityRealm.getUser().getNom() + " " + EntityRealm.getUser().getPrenom());

                 //pst.setString(8, InetAddress.getLocalHost().getHostName());    
                 */
                pst.executeUpdate();
                pst.close();
                conn.close();
            }

        } catch (Exception e) {
           // System.out.println("Test log4j echoue");
            //e.printStackTrace();
        }
    }
}
