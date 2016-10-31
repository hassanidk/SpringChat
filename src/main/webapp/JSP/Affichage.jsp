<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
       
   		<c:forEach items="${modele}" var="entry">
   		<c:if test="${entry.key eq salon}">
   		<c:forEach items="${entry.value}" var="item" varStatus="loop">
   		
        <div class="divMessage">
            <span class="pseudoMessage"> 
             ${item.pseudo}
            </span>
            <span class="separationMessage">
                -
            </span>
            <span class="contenuMessage"> 
            ${item.message}   
            </span>
        </div>
        </c:forEach>
        </c:if>
</c:forEach>