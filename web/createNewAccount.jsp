<%-- 
    Document   : createNewAccount
    Created on : Oct 4, 2019, 7:36:37 AM
    Author     : nguye
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Account</title>
        <style>
/*            .wapper {
                background-color: blue;
                width: 500px;
                height: 100px;
                line-height: 20px;
            }
            .wapper input {
                float : right;
            }*/
        </style>
    </head>
    <body>
        <h1>Create New Account</h1>
        <form action="DispatchController" method="POST">
            <div class="wapper">
                <c:set var="errors" value="${requestScope.CREATEERRORS}"/>
                Email* <input type="text" name="txtEmail" value="${param.txtEmail}" /> (6 - 20 chars)<br/> 
                <c:if test="${not empty errors.emailFormatErr}">
                    <font color="red" >
                    ${errors.emailFormatErr}
                    </font><br/>
                </c:if>

                Password* <input type="password" name="txtPassword" value="${param.txtPassword}" /> (6 - 30 chars)<br/>
                <c:if test="${not empty errors.passwordLengthErr}">
                    <font color="red" >
                    ${errors.passwordLengthErr}
                    </font><br/>
                </c:if>

                Confirm* <input type="password" name="txtComfirm" value="${param.txtComfirm}" /><br/>
                <c:if test="${not empty errors.confirmNotMatchPassword}">
                    <font color="red" >
                    ${errors.confirmNotMatchPassword}
                    </font><br/>
                </c:if>

                Full name* <input type="text" name="txtFullname" value="${param.txtFullname}" /> (2 - 50 chars)<br/>
                <c:if test="${not empty errors.fullNameLengthErr}">
                    <font color="red" >
                    ${errors.fullNameLengthErr}
                    </font><br/>
                </c:if>
                <input type="submit" value="Create New Account" name="btAction" />
                <input type="reset" value="Reset" />
            </div>
        </form><br/>
        <c:if test="${not empty errors.emailIsExisted}">
            <font color="red" >
            ${errors.emailIsExisted}
            </font><br/>
        </c:if>
        <a href="DispatchController?btAction=LoginPage">Back</a>
    </body>
</html>
