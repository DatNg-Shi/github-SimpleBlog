<%-- 
    Document   : search
    Created on : Sep 25, 2019, 8:04:15 AM
    Author     : nguye
--%>

<%@page import="DatNT.userInfo.UserInfoDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
        <style>

        </style>
    </head>
    <body> 
        <font color="red"> 
        Welcome, ${sessionScope.NAME}
        </font>
        <h1>Search Page</h1>
        <form action="DispatchController">
            <input type="submit" value="Logout" name="btAction" /><br/>
            Search Value <input type="text" name="txtSearchValue" 
                                value="${param.txtSearchValue}" />
            <input type="submit" value="Search" name="btAction" />   
        </form><br/>

        <c:set var="searchValue" value="${param.txtSearchValue}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCHRESULT}" />
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Last name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="DispatchController">
                            <tr>
                                <td> ${counter.count}.</td>
                                <td> 
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" value="${dto.username}" />
                                </td>
                                <td> 
                                    <input type="password" name="txtPassword" value="${dto.password}" />
                                </td>
                                <td> ${dto.lastName}</td>
                                <td> 
                                    ${dto.role}
                                    <input type="checkbox" name="chkAdmin" value="ON" 
                                           <c:if test="${dto.role}">
                                               checked="checked"
                                           </c:if>
                                               />
                                </td>
                                <td>
                                    <c:url var="urlRewriting" value="DispatchController" >
                                        <c:param name="btAction" value="delete"/>
                                        <c:param name="pk" value="${dto.username}"/>
                                        <c:param name="lastSearchValue" value="${searchValue}"/>
                                    </c:url>
                                    <a href="${urlRewriting}">Delete</a>
                                </td>
                                <td> 
                                    <input type="submit" value="Update" name="btAction" /> 
                                    <input type="hidden" name="lastSearchValue" value="${searchValue}" />
                                </td>
                            </tr>
                        </form>
                        </c:forEach>
                    </tbody>
                </table>

            </c:if>
            <c:if test="${empty result}">
                <h2>No record is matched</h2>
            </c:if>

        </c:if>  
    </body> 
</html>
