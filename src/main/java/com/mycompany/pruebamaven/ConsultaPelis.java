package com.mycompany.pruebamaven;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;

/**
 *
 * @author Ezequiel
 */
@WebServlet(urlPatterns = {"/ConsultaPelis"})
public class ConsultaPelis extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
             Connection conexion = conectar();
             
             Statement stmt = conexion.createStatement();
             String sqlStr = request.getParameter("sentencia");
             
             boolean resultado = stmt.execute(sqlStr);
             
             if(resultado){
                 ResultSet rs = stmt.getResultSet();
                 
                 Result r = ResultSupport.toResult(rs);
                 
                 request.setAttribute("listaReg", r);
             }else{
                 request.setAttribute("nRegModif", stmt.getUpdateCount());
             }
             
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaPelis.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaPelis.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
     public Connection conectar() {

            //Paso 1: Cargar el driver JDBC.
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();

                // Paso 2: Conectarse a la Base de Datos utilizando la clase Connection
                String userName = "root";
                String password = "root";

                //URL de la base de datos(equipo, puerto, base de datos)
                String url = "jdbc:mysql://localhost/peliculas";

                //String url="jdbc:mysql://192.168.9.247/peliculas";
                Connection c = DriverManager.getConnection(url, userName, password);
                return c;
            } catch (Exception e) {
                return null;
            }

        }

}
