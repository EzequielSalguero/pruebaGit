<%-- 
    Document   : index
    Created on : 18-dic-2018, 9:56:25
    Author     : Ezequiel
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Consulta BD</h1>
        <c:if test="${!empty param.sentencia}">
            
            <jsp:include page="ConsultaPelis"/>
            
            <h2>Resultados</h2>
            
            <c:if test="${!empty requestScope.nRegModif}">
                <c:out value="${nRegModif}"/>
            </c:if>
            
            <c:forEach var="reg" items="${requestScope.listaReg.rowsByIndex}">
                <c:forEach var="campo" items="${reg}">
                    <c:out value="${campo}"/> 
                </c:forEach><br>
            </c:forEach>
            
        </c:if>
        
        <form action="#">
            <input type="text" name="sentencia" size="25">
            <input type="submit" value="Ejecutar">
        </form>
    </body>
</html>
