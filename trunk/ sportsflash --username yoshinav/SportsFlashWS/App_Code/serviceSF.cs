using System;
using System.Web;
using System.Web.Services;
using System.Web.Services.Protocols;
using System.Xml;
using System.Data;
using System.Data.SqlClient;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.ServiceModel.Configuration;
using System.ServiceModel.Description;
using System.ServiceModel.Channels;

/// <summary>
/// Author Navdeep Alam - CS 893
/// July 2008
/// XMLDocument methods from Stored Procedures
/// XML Data Centric REST Web Service APIs for Mobile Sports Application - SportsFlash
/// </summary>
namespace com.google.android.sportsflash
{

    [ServiceContract]
    [XmlSerializerFormat]
    public interface ISportsFlash
    {
        [OperationContract, WebGet]
        XmlDocument GetXmlDocument();

  
        // -----------------------------------------
        //CRUD API for MLB Fantasy League
        // -----------------------------------------

        //Create MLB Fantasy League Team
        [OperationContract, WebGet]
        XmlDocument CreateMLBLeague(string name, string description);

        //Update MLB Fantasy League 
        [OperationContract, WebGet]
        XmlDocument UpdateMLBLeague(int id, string name, string description);

        //Delete MLB Fantasy League 
        [OperationContract, WebGet]
        XmlDocument DeleteMLBLeague(int id);

        //Get MLB Fantasy League
        [OperationContract, WebGet]
        XmlDocument GetMLBLeague(int id);


        // -----------------------------------------
        //CRUD API for MLB Fantasy League Teams
        // -----------------------------------------

        //Create MLB Fantasy League Team
        [OperationContract, WebGet]
        XmlDocument CreateMLBTeam(string leagueId, string name, string description, string firstbase, string secondbase, string thirdbase, string shortstop, string catcher, string pitcher, string rightfield, string centerfield, string leftfield, string dhitter);
        //[OperationContract, WebGet]
        //XmlDocument CreateMLBTeam2(string leagueId, string name, string description);

        //Update MLB Fantasy League Team
        [OperationContract, WebGet]
        XmlDocument UpdateMLBTeam(int id, string position, int playerId);

        //Delete MLB Fantasy League Team
        [OperationContract, WebGet]
        XmlDocument DeleteMLBTeam(int id);

        //Get MLB Fantasy League Team
        [OperationContract, WebGet]
        XmlDocument GetMLBTeam(int id);

        //Get MLB Teams, specified by it's unique identifier in the database
        [OperationContract, WebGet]
        XmlDocument GetMLBTeamsByIdentifier(string id);


        // -----------------------------------------
        //CRUD API for MLB Fantasy League Team Players
        // -----------------------------------------

        // Get MLB Players
        [OperationContract, WebGet]
        XmlDocument GetMLBPlayers();

        //Get MLB Players, specified by position
        [OperationContract, WebGet]
        XmlDocument GetMLBPlayersByPosition(string position);

        //Get MLB Player, specified by their unique identifier in the database
        [OperationContract, WebGet]
        XmlDocument GetMLBPlayerByIdentifier(string id);

        //Get MLB Players associated with a SportsFlash Fantasy Team
        [OperationContract, WebGet]
        XmlDocument GetMLBTeamsPlayers(string teamid, string playerid);

        //Update MLB Fantasy League Team Player Statistics
        [OperationContract, WebGet]
        XmlDocument UpdateMLBPlayer(string id, string hr, string ba, string rbi, string wins, string saves, string era);

 
        // -----------------------------------------
        //CRUD API for MLB Fantasy League Message Boards
        // -----------------------------------------

        //Create Message Post
        [OperationContract, WebGet]
        XmlDocument CreateMLBMessage(string title, string message);

        //Get All Message Posts
        [OperationContract, WebGet]
        XmlDocument GetMLBMessages();
    }

    [ServiceBehavior(IncludeExceptionDetailInFaults = true)] 
    public class SportsFlashService : ISportsFlash
    {
        static SqlConnection conn = new SqlConnection(DBHelper.connectionString);

        public XmlDocument GetXmlDocument()
        {
            // Create an XmlDocument object.
            XmlDocument xmlDocumentObject = new XmlDocument();
            xmlDocumentObject.LoadXml("<book genre=\"novel\" publicationdate=\"1997\" " +
            "      ISBN=\"1-861001-57-5\">" +
            "  <title>Pride And Prejudice</title>" +
            "  <author>" +
            "    <first-name>Jane</first-name>" +
            "    <last-name>Austen</last-name>" +
            "  </author>" +
            "  <price>24.95</price>" +
            "</book>");

            // Return the created XmlDocument object.
            return (xmlDocumentObject);
        }

        // Get MLB Players
        public XmlDocument GetMLBPlayers()
        {
            try
            {
                using (SqlDataAdapter a = new SqlDataAdapter(DBHelper.getMLBPlayers, conn))
                {
                    DataSet s = new DataSet();
                    a.Fill(s);
                    //XmlDataDocument datasetXML = new XmlDataDocument(s);
                    XmlDocument datasetXML = new XmlDocument();
                    datasetXML.LoadXml(s.GetXml());

                    return datasetXML;
                }

            }
            catch
            {
                return null;
            }

        }

        //Get MLB Teams, specified by it's unique identifier in the database
        public XmlDocument GetMLBTeamsByIdentifier(string id)
        {
            try
            {
                using (SqlDataAdapter a = new SqlDataAdapter(DBHelper.GetMLBTeamsByID(id), conn))
                {
                    DataSet s = new DataSet();
                    a.Fill(s);
                    //XmlDataDocument datasetXML = new XmlDataDocument(s);
                    XmlDocument datasetXML = new XmlDocument();
                    datasetXML.LoadXml(s.GetXml());

                    return datasetXML;
                }

            }
            catch
            {
                return null;
            }

        }

        //Get MLB Players associated with a SportsFlash Fantasy Team
        public XmlDocument GetMLBTeamsPlayers(string teamid, string playerid)
        {
            try
            {
                using (SqlDataAdapter a = new SqlDataAdapter(DBHelper.GetMLBTeamsPlayers(teamid, playerid), conn))
                {
                    DataSet s = new DataSet();
                    a.Fill(s);
                    //XmlDataDocument datasetXML = new XmlDataDocument(s);
                    XmlDocument datasetXML = new XmlDocument();
                    datasetXML.LoadXml(s.GetXml());

                    return datasetXML;
                }

            }
            catch
            {
                return null;
            }

        }

        //Get MLB Players, specified by position
        public XmlDocument GetMLBPlayersByPosition(string position)
        {
            try
            {
                using (SqlDataAdapter a = new SqlDataAdapter(DBHelper.GetMLBPlayersByPosition(position), conn))
                {
                    DataSet s = new DataSet();
                    a.Fill(s);
                    //XmlDataDocument datasetXML = new XmlDataDocument(s);
                    XmlDocument datasetXML = new XmlDocument();
                    datasetXML.LoadXml(s.GetXml());
                    return datasetXML;
                }
                
            }
            catch
            {
                return null;
            }
        }

        //Get MLB Player, specified by their unique identifier in the database
        public XmlDocument GetMLBPlayerByIdentifier(string id)
        {
            try
            {
                using (SqlDataAdapter a = new SqlDataAdapter(DBHelper.GetMLBPlayerByID(id), conn))
                {
                    DataSet s = new DataSet();
                    a.Fill(s);
                    //XmlDataDocument datasetXML = new XmlDataDocument(s);
                    XmlDocument datasetXML = new XmlDocument();
                    datasetXML.LoadXml(s.GetXml());
                    return datasetXML;
                    //return a.ToString();
                }

            }
            catch
            {
                return null;
            }
        }

        //Create MLB Fantasy League Team
        public XmlDocument CreateMLBLeague(string name, string description)
        {
            try
            {

                using (SqlDataAdapter a = new SqlDataAdapter(DBHelper.CreateMLBLeague(name, description), conn))
                {
                    DataSet s = new DataSet();
                    a.Fill(s);
                    //XmlDataDocument datasetXML = new XmlDataDocument(s);
                    XmlDocument datasetXML = new XmlDocument();
                    datasetXML.LoadXml(s.GetXml());
                    return datasetXML;
                }
                
            }
            catch
            {
                return null;
            }
        }

        //Update MLB Fantasy League Team
        public XmlDocument UpdateMLBLeague(int id, string name, string description)
        {
            try
            {
                using (SqlDataAdapter a = new SqlDataAdapter(DBHelper.UpdateMLBLeague(id, name, description), conn))
                {
                    DataSet s = new DataSet();
                    a.Fill(s);
                    //XmlDataDocument datasetXML = new XmlDataDocument(s);
                    XmlDocument datasetXML = new XmlDocument();
                    datasetXML.LoadXml(s.GetXml());
                    return datasetXML;
                }
                
            }
            catch
            {
                return null;
            }

        }

        //Delete MLB Fantasy League
        public XmlDocument DeleteMLBLeague(int id)
        {
            try
            {

                using (SqlDataAdapter a = new SqlDataAdapter(DBHelper.DeleteMLBLeague(id), conn))
                {
                    DataSet s = new DataSet();
                    a.Fill(s);
                    //XmlDataDocument datasetXML = new XmlDataDocument(s);
                    XmlDocument datasetXML = new XmlDocument();
                    datasetXML.LoadXml(s.GetXml());
                    return datasetXML;
                }
                
            }
            catch
            {
                return null;
            }
        }

        //Get MLB Fantasy League
        public XmlDocument GetMLBLeague(int id)
        {
            try
            {

                using (SqlDataAdapter a = new SqlDataAdapter(DBHelper.GetMLBLeague(id), conn))
                {
                    DataSet s = new DataSet();
                    a.Fill(s);
                    //XmlDataDocument datasetXML = new XmlDataDocument(s);
                    XmlDocument datasetXML = new XmlDocument();
                    datasetXML.LoadXml(s.GetXml());
                    return datasetXML;
                }
                
            }
            catch
            {
                return null;
            }
        }

        //Create MLB Fantasy League Team
        public XmlDocument CreateMLBTeam(string leagueId, string name, string description, string firstbase, string secondbase, string thirdbase, string shortstop, string catcher, string pitcher, string rightfield, string centerfield, string leftfield, string dhitter)
        {
            try
            {
                using (SqlDataAdapter a = new SqlDataAdapter(DBHelper.CreateMLBTeam(leagueId, name, description, firstbase, secondbase, thirdbase, shortstop, catcher, pitcher, rightfield, centerfield, leftfield, dhitter), conn))
                {
                    DataSet s = new DataSet();
                    a.Fill(s);
                    //XmlDataDocument datasetXML = new XmlDataDocument(s);
                    XmlDocument datasetXML = new XmlDocument();
                    datasetXML.LoadXml(s.GetXml());
                    return datasetXML;
                  
                    //return null;
                }
                
            }
            catch
            {
                return null;
            }
        }

        /*
         * // Debugging Example
        public XmlDocument CreateMLBTeam2(string leagueId, string name, string description)
        {
            try
            {
                            
                using (SqlConnection conn = new SqlConnection(DBHelper.connectionString))
                {

                    //return DBHelper.CreateMLBTeam("45", "des", "test", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70");
                    //using (SqlDataAdapter a = new SqlDataAdapter(DBHelper.CreateMLBTeam(Convert.ToInt32(leagueId), name, description, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70), conn))
                    using (SqlDataAdapter a = new SqlDataAdapter(DBHelper.CreateMLBTeam("45", name, description, "61", "62", "63", "64", "65", "66", "67", "68", "69", "70"), conn))
                    {
                        DataSet s = new DataSet();
                        a.Fill(s);
                        XmlDocument datasetXML = new XmlDocument();
                        datasetXML.LoadXml(s.GetXml());
                        //return s.GetXml();
                        return datasetXML;
                    }
                }
                
            }
            catch (Exception e)
            {
                XmlDocument errorXML = new XmlDocument();
                errorXML.LoadXml(e.ToString());
                return errorXML;
                //return null;
                //return e.ToString();
            }
        }
        */

        //Update MLB Fantasy League Team
        public XmlDocument UpdateMLBTeam(int id, string position, int playerId)
        {
            try
            {

                using (SqlDataAdapter a = new SqlDataAdapter(DBHelper.UpdateMLBTeam(id, position, playerId), conn))
                {
                    DataSet s = new DataSet();
                    a.Fill(s);
                    //XmlDataDocument datasetXML = new XmlDataDocument(s);
                    XmlDocument datasetXML = new XmlDocument();
                    datasetXML.LoadXml(s.GetXml());
                    return datasetXML;
                }
                
            }
            catch
            {
                return null;
            }
        }

        //Update MLB Fantasy League Team Players
        public XmlDocument UpdateMLBPlayer(string id, string hr, string ba, string rbi, string wins, string saves, string era)
        {
            try
            {

                using (SqlDataAdapter a = new SqlDataAdapter(DBHelper.UpdateMLBPlayer(id, hr, ba, rbi, wins, saves, era), conn))
                {
                    DataSet s = new DataSet();
                    a.Fill(s);
                    //XmlDataDocument datasetXML = new XmlDataDocument(s);
                    XmlDocument datasetXML = new XmlDocument();
                    datasetXML.LoadXml(s.GetXml());
                    return datasetXML;
                }

            }
            catch
            {
                return null;
            }
        }
        
        //Delete MLB Fantasy League Team
        public XmlDocument DeleteMLBTeam(int id)
        {
            try
            {

                using (SqlDataAdapter a = new SqlDataAdapter(DBHelper.DeleteMLBTeam(id), conn))
                {
                    DataSet s = new DataSet();
                    a.Fill(s);
                    //XmlDataDocument datasetXML = new XmlDataDocument(s);
                    XmlDocument datasetXML = new XmlDocument();
                    datasetXML.LoadXml(s.GetXml());
                    return datasetXML;
                }
                
            }
            catch
            {
                return null;
            }
        }

        //Get MLB Fantasy Team
        public XmlDocument GetMLBTeam(int id)
        {
            try
            {

                using (SqlDataAdapter a = new SqlDataAdapter(DBHelper.GetMLBTeam(id), conn))
                {
                    DataSet s = new DataSet();
                    a.Fill(s);
                    //XmlDataDocument datasetXML = new XmlDataDocument(s);
                    XmlDocument datasetXML = new XmlDocument();
                    datasetXML.LoadXml(s.GetXml());
                    return datasetXML;
                }
                
            }
            catch
            {
                return null;
            }
        }

        //Get Message Board Posts
        public XmlDocument GetMLBMessages()
        {
            try
            {
                using (SqlDataAdapter a = new SqlDataAdapter(DBHelper.GetMLBMessages(), conn))
                {
                    DataSet s = new DataSet();
                    a.Fill(s);
                    //XmlDataDocument datasetXML = new XmlDataDocument(s);
                    XmlDocument datasetXML = new XmlDocument();
                    datasetXML.LoadXml(s.GetXml());

                    return datasetXML;
                }

            }
            catch
            {
                return null;
            }

        }

        //Create Message Boards Posts
        public XmlDocument CreateMLBMessage(string title, string message)
        {
            try
            {

                using (SqlDataAdapter a = new SqlDataAdapter(DBHelper.CreateMLBMessage(title, message), conn))
                {
                    DataSet s = new DataSet();
                    a.Fill(s);
                    //XmlDataDocument datasetXML = new XmlDataDocument(s);
                    XmlDocument datasetXML = new XmlDocument();
                    datasetXML.LoadXml(s.GetXml());
                    return datasetXML;
                }

            }
            catch
            {
                return null;
            }
        }
    }
}
