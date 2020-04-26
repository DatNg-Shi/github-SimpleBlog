<%-- 
    Document   : viewArticle
    Created on : Jan 13, 2020, 5:32:32 PM
    Author     : nguye
--%>

<%@page import="DatNT.comment.CommentDTO" %>
<%@page import="DatNT.articles.ArticlesDTO" %>
<%@page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Article</title>
        <style>
            .wapper {
                line-height: 40px;
                font-size: 20px;
            }
        </style>
    </head>
    <body>
        <div style="border: solid black 1px">
            <font color="black">
            <c:if test="${not empty sessionScope.EMAIL}">
                <h3>Welcome, ${sessionScope.NAME} ; ${sessionScope.EMAIL}</h3>
                <a href="DispatchController?btAction=Logout">Logout</a><br/>
            </c:if>
            <c:if test="${empty sessionScope.EMAIL}">
                <h3>Welcome, Quest.</h3>
                <a href="DispatchController?btAction=LoginPage">Login</a><br/>
                <a href="DispatchController?btAction=Sign Up">Sign Up</a><br/>
            </c:if>
            </font> 
        </div><br/>
        <c:set var= "role" value="${requestScope.USERROLE}"/>
        <form action="DispatchController">
            <div class="wapper">
                <c:set var="viewResult" value="${requestScope.VIEWRESULT}" />
                <c:set var="listC" value="${requestScope.LISTCOMMENTRESULT}"/>
                <c:if test="${not empty viewResult}">
                    <c:set var="article" value="${viewResult}"/>    
                    <h2>Article Information</h2>
                    <b>Tittle: </b> ${article.tittle}<br/>
                    <input type="hidden" name="pk" value="${article.tittle}" />
                    <b>Short Description: </b> ${article.shortDescription}<br/>
                    <b>Content: </b> ${article.content}<br/>
                    <b>Author: </b> ${article.author}<br/>
                    <b>Posting date : </b> ${article.dateOfPost}<br/>
                    <c:if test="${not empty listC}">
                        <b>Comment: </b> <br/>
                        <c:forEach var="dto" items="${listC}">
                            <c:if test="${dto.emailUser == 'You'}">
                                <div style="width: 500px">
                                    <d style="color: blue; text-align: right"> ${dto.emailUser}: ${dto.comment}</d><br/>
                                </div>
                            </c:if>
                            <c:if test="${dto.emailUser != 'You'}">
                                <div style="width: 500px">
                                    <d style="color: gray; text-align: right"> ${dto.emailUser}: ${dto.comment}</d><br/>
                                </div>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </c:if>
                <c:if test="${empty role}">
                    <input type="hidden" name="txtLogin" value="Login" />
                    <input style="width: 20%" type="text" name="txtComment" value="" required=""/> 
                    <input type="hidden" name="pk" value="${article.tittle}" />
                    <input type="hidden" name="txtStatusAr" value="${article.status}" />
                    <input type="submit" value="Comment" name="btAction" />
                </c:if>
                <c:if test="${not empty role}">
                    <c:if test="${article.status == 'Delete'}">
                        <input style="margin-left: 50px; width: 7%;" type="submit" value="Restore" name="btAction" />
                    </c:if>
                    <c:if test="${article.status != 'Delete'}">
                        <input style="margin-left: 50px; width: 7%;" type="submit" value="Approval" name="btAction" />
                        &ensp;
                        <input style="margin-left: 50px; width: 7%;" type="submit" value="Delete" name="btAction" />
                    </c:if>
                </c:if>
            </div>
        </form><br/>


        <c:if test="${not empty role}">
            <a href="DispatchController?btAction=showAllAdminPage">Back to Page</a>
        </c:if>
        <c:if test="${empty role}">
            <a href="DispatchController?btAction=showAll">Back to Page</a><br/>
        </c:if>
    </body>
</html>
