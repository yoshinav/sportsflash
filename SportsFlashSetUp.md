# Installation Steps to Setup SportsFlash #

SportsFlash, the mobile application for Sports Fantasy Leagues comprises of three parts, a database, a web service and the Google Android application.  We will discuss the steps to deploy all three components.


# Steps: #

1)  Database:  The database is MS SQL 2005.  To restore the database, do the following:

  * From SVN: Data -> db, restore the database using file "SportsFlash.bak".
  * From SVN: Data -> sql, execute each script (.sql) file against your SportsFlash database.
  * Create a database user who can login and access your SportsFlash database.  You will need to reference this user in the web service that connects and runs against this database.

2)  Web Service:  This REST Web Service is written in .Net version 3.5, using Windows Communication Foundation (WCF).  To deploy the web service, do the following:

  * From SVN:  SportsFlashWS, contains the web service code.  Create a virtual directory using IIS for this web service.  Ensure that your system has .Net 3.5 installed.
  * The DBHelper.cs file (in the App\_Code folder of SportsFlashWS), contains the connection string variables to your SportsFlash database (i.e. connectionString).  Update this with your database user credentials.

3)  Google Android:  This is the application that will communicate with the Web Service to interact with the database hosting the sports fantasy leagues.  For configuration, do the following:

  * com.google.android.sportsflash.Configuration.java:  This file has all the configuration information, including the variable "urlServer" which defines the IP address of the SportsFlash web service which you need to update to reflect your server where the web service is deployed.