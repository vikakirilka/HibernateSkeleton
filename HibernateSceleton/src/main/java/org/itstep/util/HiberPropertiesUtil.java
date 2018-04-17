package org.itstep.util;

import java.io.File;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HiberPropertiesUtil {

 private final SessionFactory sessionFactory;

 public HiberPropertiesUtil() throws HibernateException {
  sessionFactory = configureSessionFactory();
 }

 /**
  * Create SessionFactory
  * 
  * @return {@link SessionFactory}
  * @throws HibernateException
  */
 private SessionFactory configureSessionFactory() throws HibernateException {

  String userDir = System.getProperty("user.dir");

  Configuration configuration = new Configuration().configure();
  Properties properties = new Properties();
  
  properties.setProperty("connection.driver_class", "org.postgresql.Driver");
  properties.setProperty("connection.url", "jdbc:postgresql://localhost:5432/hiber_sceleton");
  properties.setProperty("connection.user", "postgres");
  properties.setProperty("connection.password", "sherlock1058");
  properties.setProperty("connection.pool_size", "1");
  
  properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
  properties.setProperty("hibernate.hbm2ddl.auto", "update");
  
  configuration.setProperties(properties);
  configuration.setProperty("packagesToScan", "org.itstep.model");
  
  return configuration.configure().buildSessionFactory();
  
  
 }

 /**
  * Get SessionFactory
  * 
  * @return {@link SessionFactory}
  */
 public SessionFactory getSessionFactory() {
  return sessionFactory;
 }
}