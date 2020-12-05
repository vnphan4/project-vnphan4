<%--suppress XmlPathReference --%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <title>Covid Tracking by State</title>
    <style><%@include file="../css/style.css"%></style>
    <%--     Source for Date picker: https://www.topjavatutorial.com/jquery/jquery-datepicker/--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
    <script>
        $(function() {
            $("#datepicker").datepicker({
                maxDate:0,
                <%--    Source for default Date: https://stackoverflow.com/questions/15423467/bootstrap-datepicker-today-as-default/15423504--%>
            }).datepicker("setDate", "-1");
        });
    </script>
</head>
<body>
<br><br>
<a href="/"><input type="submit" value="Back to Home"></a>
<br><br>
<hr>
<h1>View Total Case by State in Chart</h1>
<form method="get" action="/chart">
    <p>Pick a Date: <input type="text" id="datepicker" name = "chosenDate" ></p>
    <input type="submit" value="Submit">
</form>
<br><br>
<hr>
<h1>View and Manage Data</h1>
<form method="get" action="/viewAll">
    <input type="submit" value="Show Covid Records">
    <table align="center" cellpadding="5">
        <tr>
            <th>State Abbreviation</th>
            <th>Date</th>
            <th>Total Cases</th>
            <th>Total Deaths</th>
            <th>New Cases</th>

        </tr>
        <c:forEach var = "record" items = "${covidtracklist}">
            <tr>
                <td>${record.getStateAbbrev()}</td>
                <td>${record.getDate()}</td>
                <td>${record.getTotalCase()}</td>
                <td>${record.getTotalDeaths()}</td>
                <td>${record.getNewCases()}</td>
                <td><a href="/edit/${record.getId()}">Edit</a></td>
                <td><a href="/delete/${record.getId()}"><img src="../../img/delete.jpg" alt="delete_image" ></a><td>
            </tr>
        </c:forEach>

    </table>
</form>
</body>
</html>
