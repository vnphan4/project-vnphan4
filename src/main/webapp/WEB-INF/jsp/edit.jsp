<%--suppress XmlPathReference --%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%--<%@ page isELIgnored="false" %>--%>

<!DOCTYPE html>
<html>
<head>
    <title>EDIT Covid Record</title>
</head>
<body>

<hr/>
<form method="post" action="/save">
    <input type="hidden" name="id" value="${selectedCovidTrack.getId()}">
    <br>State Abbreviation: <br>
    <input type="text" name="stateAbbrev" value="${selectedCovidTrack.getStateAbbrev()}">
    <br>Record Date: <br>
    <input type="text" name="date" value="${selectedCovidTrack.getDate()}">
    <br>Total Case: <br>
    <input type="text" name="totalCase" value="${selectedCovidTrack.getTotalCase()}">
    <br>Total Deaths: <br>
    <input type="text" name="totalDeaths" value="${selectedCovidTrack.getTotalDeaths()}">
    <br>New Cases: <br>
    <input type="text" name="newCases" value="${selectedCovidTrack.getNewCases()}">
    <input type="hidden" name="userID" value="${selectedCovidTrack.getUserID()}">
<%--    <br>Event Host: <br>--%>

<%--    <input list="selectedEmployee" name="eventHost">--%>
<%--    <datalist id="selectedEmployee">--%>

<%--        <c:forEach var = "employee" items = "${selectedEmployee}">--%>
<%--            <tr>--%>
<%--                    &lt;%&ndash;                I try to hide the ID but not sure it works&ndash;%&gt;--%>
<%--                <option type="hidden" value =  ${employee.getId()}>${employee.getName()}</option>--%>
<%--            </tr>--%>
<%--        </c:forEach>--%>

<%--    </datalist>--%>

    <br><br>
    <input type="submit" value="Submit">
    <input type="reset">
</form>


</body>
</html>
