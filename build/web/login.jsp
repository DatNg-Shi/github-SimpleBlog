<%-- 
    Document   : login
    Created on : Jan 12, 2020, 5:35:26 PM
    Author     : nguye
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <h1>Login Page</h1>
        <form action="DispatchController" method="POST">
            <c:set var="error" value="${requestScope.LOGINERROR}"/>
            Email <input type="text" name="txtEmail" value="${param.txtEmail}" /><br/>
            <c:if test="${not empty error.emailNotCorrect}">
                <font color="red">
                ${error.emailNotCorrect}
                </font><br/>
            </c:if>
            
            Password <input type="password" name="txtPassword" value="" /><br/>
            <input type="submit" value="Login" name="btAction" />
            <input type="reset" value="Reset" />
        </form><br/>
        <c:if test="${not empty error.accountNotFound}">
            <font color="red">
            ${error.accountNotFound}
            </font><br/>
        </c:if>
        
        Don't have an account? 
        <a href="createNewAccount.html"> Click here</a><br/>
        <a href="DispatchController?btAction=showAll">View some article</a><br/>
        
</html>
