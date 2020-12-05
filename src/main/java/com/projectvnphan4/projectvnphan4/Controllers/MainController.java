package com.projectvnphan4.projectvnphan4.Controllers;

import com.projectvnphan4.projectvnphan4.Models.CovidTrack;
import com.projectvnphan4.projectvnphan4.Models.CovidTrackRepo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class MainController {

    private static ArrayList<CovidTrack> chartlist = new ArrayList<>();
    private static String rememberDate;
    @Autowired
    private Environment env;

    @Autowired
    CovidTrackRepo covidTrackRepo;

    @RequestMapping( value = "/chart", method = RequestMethod.GET)
    public ModelAndView chart(@RequestParam ("chosenDate") String chosenDate) {
        // Data on chart uses the first chosen date and cannot be changed to another date because it is static
        rememberDate = chosenDate;
        ModelAndView mv = new ModelAndView("chart");
        CanvasjsChartData chartDataObject = new CanvasjsChartData();
        List<List<Map<Object, Object>>> canvasjsDataList = chartDataObject.getCanvasjsDataList();
        mv.addObject("dataPointsList", canvasjsDataList);
        mv.addObject("chosenDate", chosenDate);
        return mv;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ModelAndView get(@RequestParam ("StateAbbrev") String stateAbb,
                            @RequestParam ("chosenDate") String chosenDate) throws ParseException {
        String url = "https://api.covidtracking.com/v1/states/" + stateAbb + "/" + changeDateFormat(chosenDate) + ".json" ;

        ModelAndView mv = new ModelAndView("redirect:/");
        String stateCovid = getAPIData(url);
        try {
            JSONObject json = new JSONObject(stateCovid);
            mv.addObject("stateAbbrev", json.getString("state"));
            mv.addObject("date", json.get("date").toString());
            mv.addObject("totalCase", json.get("positive").toString());
            mv.addObject("totalDeaths", json.get("deathConfirmed").toString());
            mv.addObject("newCases", json.get("positiveIncrease").toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return mv;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(@RequestParam("id") String id,
                             @RequestParam("stateAbbrev") String stateAbbrev,
                             @RequestParam("date") String date,
                             @RequestParam("totalCase") String totalCase,
                             @RequestParam("totalDeaths")String totalDeaths,
                             @RequestParam("newCases")String newCases,
                             @RequestParam("userID")String userID){

        ModelAndView mv = new ModelAndView("redirect:/");
        // Create record object to save in database
        CovidTrack RecordToSave ;

        // Add information to record object
        if(!id.isEmpty())
        {
            Optional<CovidTrack> users = covidTrackRepo.findById(id);
            RecordToSave = users.get();
        }
        else
        {
            RecordToSave = new CovidTrack();
            RecordToSave.setId(UUID.randomUUID().toString());
        }
        //RecordToSave.setId(UUID.randomUUID().toString());
        RecordToSave.setStateAbbrev(stateAbbrev);
        RecordToSave.setDate(date);

        // Allows totalCases to be null
        if (!totalCase.isEmpty())
        {RecordToSave.setTotalCase(totalCase);}
        else {RecordToSave.setTotalCase(null);}

        // Allows totalDeaths to be null
        if (!totalDeaths.isEmpty())
        {RecordToSave.setTotalDeaths(totalDeaths);}
        else {RecordToSave.setTotalDeaths(null);}

        // Allows new Cases to be null
        if (!newCases.isEmpty())
        {RecordToSave.setNewCases(newCases);}
        else {RecordToSave.setNewCases(null);}

        RecordToSave.setUserID(userID);

        // Save record to Repository
        covidTrackRepo.save(RecordToSave);
        chartlist.add(RecordToSave);
        // Add all records to mv
        mv.addObject("covidtracklist", covidTrackRepo.findAll());
        return mv;
    }
    @RequestMapping( value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") String id){
        ModelAndView mv = new ModelAndView("view");
        covidTrackRepo.deleteById(id);

        return mv;
    }

    @RequestMapping("/viewAll")
    public ModelAndView viewAll() {
        ModelAndView mv = new ModelAndView("view");
        mv.addObject("covidtracklist", covidTrackRepo.findAll());

        return mv;
    }

    @RequestMapping( value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") String id){
        ModelAndView mv = new ModelAndView("edit");
        Optional<CovidTrack> covidTrackRecord = covidTrackRepo.findById(id);
        CovidTrack foundCovidTrack = covidTrackRecord.get();
        mv.addObject("selectedCovidTrack", foundCovidTrack);
        return mv;
    }
    private String getAPIData(String url) {
        try {
            URL urlForGetRequest = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
                in.close();
                return response.toString();
            } else {
                return "Unexpected HTTP response";
            }
        } catch (Exception e) {
            return "Exception: " + e.getMessage();
        }
    }

    // Change format from Date picker to match with format of API date
    private String changeDateFormat (String oldDate) throws ParseException {
        // Source: https://stackoverflow.com/questions/3469507/how-can-i-change-the-date-format-in-java
        final String OLD_FORMAT = "MM/dd/yyyy";
        final String NEW_FORMAT = "yyyyMMdd";
        String newDateString;
        SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
        Date d = sdf.parse(oldDate);
        sdf.applyPattern(NEW_FORMAT);
        newDateString = sdf.format(d);
        return newDateString;
    }
    public static String changeDateFormat2 (String oldDate) throws ParseException {
        // Source: https://stackoverflow.com/questions/3469507/how-can-i-change-the-date-format-in-java
        final String OLD_FORMAT = "MM/dd/yyyy";
        final String NEW_FORMAT = "yyyy-MM-dd";
        String newDateString;
        SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
        Date d = sdf.parse(oldDate);
        sdf.applyPattern(NEW_FORMAT);
        newDateString = sdf.format(d);
        return newDateString;
    }
public static String rememberDate() throws ParseException {
        String chosenDate = changeDateFormat2(rememberDate);
        return chosenDate;
}

}
