package com.projectvnphan4.projectvnphan4.Controllers;

import com.projectvnphan4.projectvnphan4.Models.CovidTrack;
import com.projectvnphan4.projectvnphan4.Models.CovidTrackRepo;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.util.*;

public class CanvasjsChartData {


    static Map<Object,Object> map = null;
    static List<List<Map<Object,Object>>> list = new ArrayList<List<Map<Object,Object>>>();
    static List<Map<Object,Object>> dataPoints = new ArrayList<Map<Object,Object>>();

    static {
        final String hostname = "patientdb.ccfh9bpxbab1.us-east-1.rds.amazonaws.com";
        final String dbname = "testdb";
        final String port = "3306";
        final String username = "admin";
        final String password = "admin3368";
        final String AWS_URL = "jdbc:mysql://" + hostname + ":" + port + "/" + dbname
                + "?user=" + username + "&password=" + password;
        try {
            try {
                Connection conn = DriverManager.getConnection(AWS_URL);
                Statement stmt = conn.createStatement();
                System.out.println("CONNECTION ESTABLISHED");
                String sqlStatement = "SELECT * FROM covidtrack WHERE date = "
                        + "'" + MainController.rememberDate() + "'";

                ResultSet result = stmt.executeQuery(sqlStatement);
                ArrayList<CovidTrack> trackList = new ArrayList<>();
                while (result.next()) {
                    CovidTrack eachTrack = new CovidTrack(result.getString("id"),
                            result.getString("state_abbrev"),
                            result.getString("date"),
                            result.getString("total_case"),
                            result.getString("total_deaths"),
                            result.getString("new_cases"),
                            result.getString("userid"));
                    trackList.add(eachTrack);
                    map = new HashMap<Object, Object>();
                    map.put("label", eachTrack.getStateAbbrev());
                    map.put("y", eachTrack.getTotalCase());
                    dataPoints.add(map);
                }
                list.add(dataPoints);
            } catch (Exception ex) {
                System.out.println("ERROR: " + ex.getMessage());
            }
        } catch (Exception ex) {

        }
    }

    public static List<List<Map<Object, Object>>> getCanvasjsDataList() {
        return list;
    }

}
