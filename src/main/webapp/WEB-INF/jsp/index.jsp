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

<%--<a href="/chart">OPEN CHART PAGE</a>--%>
<%--<form method="post" action="/saveDate">--%>
<%--    <p>Pick a Date:  <input type="text" id="datepicker" name = "chosenDate"></p>--%>
<%--    <div>--%>
<%--        <h2>Chosen Date</h2> <h3><${chosenDate}></h3>--%>
<%--    </div>--%>
<%--</form>--%>
<hr>
 <h1> Select a State </h1>
    <form method="get" action="/get">
        <p>Pick a Date:  <input type="text" id="datepicker" name = "chosenDate" ></p>

        <select name="StateAbbrev">
            <option value="AK">Alaska</option>
            <option value="AL">Alabama</option>
            <option value="AR">Arkansas </option>
            <option value="AZ">Arizona </option>
            <option value="CA">California </option>
            <option value="CO">Colorado </option>
        </select>
        <input type="submit" value="Submit">
    </form>
    <hr/>
    <div>
       <h2>State Abbreviation</h2> <h3><%=request.getParameter("stateAbbrev")%></h3>
       <h2>Date</h2> <h3><%=request.getParameter("date")%></h3>
       <h2>Total Cases</h2> <h3><%=request.getParameter("totalCase")%></h3>
       <h2>Total Deaths</h2> <h3><%=request.getParameter("totalDeaths")%></h3>
        <h2>New Cases</h2> <h3><%=request.getParameter("newCases")%></h3>
    </div>
<br>
<form method="post" action="/save">
    <input type="hidden" name="id" value="">
    <input type="hidden" name="stateAbbrev" value= "<%=request.getParameter("stateAbbrev")%>">
    <input type="hidden" name="date" value="<%=request.getParameter("date")%>">
    <input type="hidden" name="totalCase" value="<%=request.getParameter("totalCase")%>">
    <input type="hidden" name="totalDeaths" value="<%=request.getParameter("totalDeaths")%>">
    <input type="hidden" name="newCases" value="<%=request.getParameter("newCases")%>">
    <input type="hidden" name="userID" value="fill in later????">
    <input type="submit" value="Save Result">
</form>
<br>
<a href="/viewAll"><input type="submit" value="Show Covid Records"></a>

</body>
</html>
