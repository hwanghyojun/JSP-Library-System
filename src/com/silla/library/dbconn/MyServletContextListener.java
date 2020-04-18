package com.silla.library.dbconn;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import com.silla.library.dbconn.DBConnectionInfo;

public class MyServletContextListener implements javax.servlet.ServletContextListener {

   @Override
   public void contextInitialized(ServletContextEvent sce) {
      // TODO Auto-generated method stub
      javax.servlet.ServletContextListener.super.contextInitialized(sce);
      
      ServletContext context = sce.getServletContext();
      
      DBConnectionInfo connInfo = new DBConnectionInfo();
      connInfo.setDsLink(context.getInitParameter("ds-resource-link"));
      connInfo.setDbbook(context.getInitParameter("db-book"));
      connInfo.setDbadmin(context.getInitParameter("db-admin"));
      connInfo.setDbmember(context.getInitParameter("db-member"));
      connInfo.setDbrentlog(context.getInitParameter("db-rentlog"));
      connInfo.setDbroom(context.getInitParameter("db-room"));
      connInfo.setDbseat(context.getInitParameter("db-seat"));
    

      
      context.setAttribute("connection", connInfo);
       
   }

}