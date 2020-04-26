<%-- 
    Document   : adminPage
    Created on : Jan 18, 2020, 1:44:35 AM
    Author     : nguye
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
    </head>
    <body>
        <div style="border: solid black 1px">
            <font color="black">
            <c:if test="${not empty sessionScope.EMAIL}">
                <h3>Welcome, ${sessionScope.NAME} ; ${sessionScope.EMAIL}</h3>
                <a href="DispatchController?btAction=Logout">Logout</a><br/>
            </c:if>
            </font> 
        </div><br/>
        <div style="border: solid black 1px;">
            <form action="DispatchController">
                Article name(tittle): <input type="text" name="txtSearchTittle" value="${param.txtSearchTittle}" />
                Content: <input type="text" name="txtSearchArticle" value="${param.txtSearchArticle}" />
                <select name="txtStatusAr">
                    <option value="New">New</option>
                    <option value="Active">Active</option>
                    <option value="Delete">Delete</option>
                </select><br/>
                <input type="submit" value="Search" name="btAction" />
            </form><br/>
        </div>
        <div style="border: solid black 1px; height: auto">
            <c:set var="statusValue" value="${param.txtStatusAr}"/>
            <c:if test="${empty statusValue}">
                <c:set var="statusValue" value="New"/>
            </c:if>
            <a href="DispatchController?btAction=showAllAdminPage">Show All</a>
            <c:set var="searchArticle" value="${param.txtSearchArticle}"/>
            <c:set var="show" value="${requestScope.SHOWARTICLE}"/>
            <c:if test="${not empty searchArticle or not empty show}">
                <c:set var="result" value="${requestScope.SEARCHRESULT}"/>
                <c:if test="${not empty result}">
                    <c:set var="page" value="${requestScope.PAGE}"/>
                    <h3>Status: ${statusValue} - Page ${page}</h3>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Tittle</th>
                                <th>Author </th>
                                <th>Content </th>
                                <th>Date </th>
                                <th>Status </th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dto" items="${result}" varStatus="counter">
                            <form action="DispatchController">

                                <tr>
                                    <td> ${counter.count}</td>
                                    <td> 
                                        <c:url var="urlRewriting" value="DispatchController">
                                            <c:param name="btAction" value="tittle" />
                                            <c:param name="pk" value="${dto.tittle}"/>
                                            <c:param name="txtStatusAr" value="${dto.status}"/>
                                        </c:url>
                                        <a href="${urlRewriting}" style="text-decoration: none; color: black" ><span class="tittleLink">${dto.tittle}</span></a>
                                        <input type="hidden" name="pk" value="${dto.tittle}" />
                                    </td>
                                    <td> ${dto.author} </td>
                                    <td> ${dto.content} </td>
                                    <td> ${dto.dateOfPost} </td>
                                    <td> ${dto.status} </td>
                                    <c:if test="${dto.status == 'New'}">
                                        <td>
                                            <input type="submit" value="Approval" name="btAction" />
                                        </td>
                                        <td>
                                            <input type="submit" value="Delete" name="btAction" />
                                        </td>
                                    </c:if>
                                    <c:if test="${dto.status == 'Active'}">
                                        <td>
                                            <input type="submit" value="Delete" name="btAction" />
                                        </td>
                                    </c:if>
                                    <c:if test="${dto.status == 'Delete'}">
                                        <td>
                                            <input type="submit" value="Restore" name="btAction" />
                                        </td>
                                    </c:if>
                                </tr>
                            </form>
                        </c:forEach>
                        </tbody>
                    </table>
                    <c:url var="previous" value="DispatchController?btAction=changePage&page=${page}&option=previous&txtSearchArticle=${searchArticle}&txtStatusAr=${statusValue}&txtSearchTittle=${param.txtSearchTittle}"/>
                    <c:url var="next" value="DispatchController?btAction=changePage&page=${page}&option=next&txtSearchArticle=${searchArticle}&txtStatusAr=${statusValue}&txtSearchTittle=${param.txtSearchTittle}"/>
                    <a href="${previous}">Previous page</a> <d>----------</d> <a href="${next}">Next Page</a>
                    </c:if>
                    <c:if test="${empty result}">
                    <h2>No record is matched</h2>
                </c:if>
            </c:if>
        </div>
    </body>
</html>
