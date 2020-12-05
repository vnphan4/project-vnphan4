package com.projectvnphan4.projectvnphan4.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "covidtrack")

public class CovidTrack {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "state_abbrev")
    private String stateAbbrev;
    @Column(name = "date")
    private String date;
    @Column(name = "total_case")
    private String totalCase;
    @Column(name = "total_deaths")
    private String totalDeaths;
    @Column(name = "new_cases")
    private String newCases;
    @Column(name = "userID")
    private String userID;

    public CovidTrack() {
    }
    public CovidTrack(String id, String stateAbbrev, String date,
                   String totalCase, String totalDeaths, String newCases,
                      String userID) {
        this.id = id;
        this.stateAbbrev = stateAbbrev;
        this.date = date;
        this.totalCase = totalCase;
        this.totalDeaths = totalDeaths;
        this.newCases = newCases;
        this.userID= userID;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStateAbbrev() {
        return stateAbbrev;
    }

    public void setStateAbbrev(String stateAbbrev) {
        this.stateAbbrev = stateAbbrev;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotalCase() {
        return totalCase;
    }

    public void setTotalCase(String totalCase) {
        this.totalCase = totalCase;
    }

    public String getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(String totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public String getNewCases() {
        return newCases;
    }

    public void setNewCases(String newCases) {
        this.newCases = newCases;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
