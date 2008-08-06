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
/// </summary>
namespace com.google.android.sportsflash
{

    [ServiceContract]
    [XmlSerializerFormat]
    public interface ISportsFlash
    {
        [OperationContract, WebGet]
        XmlDocument GetXmlDocument();
        [OperationContract, WebGet]
        XmlDocument GetMLBPlayers();
        [OperationContract, WebGet]
        XmlDocument GetMLBPlayersByPosition(string position);
        [OperationContract, WebGet]
        XmlDocument CreateMLBLeague(string name, string description);
        [OperationContract, WebGet]
        XmlDocument UpdateMLBLeague(int id, string name, string description);
        [OperationContract, WebGet]
        XmlDocument DeleteMLBLeague(int id);
        [OperationContract, WebGet]
        XmlDocument GetMLBLeague(int id);
        [OperationContract, WebGet]
        XmlDocument CreateMLBTeam(string leagueId, string name, string description, string firstbase, string secondbase, string thirdbase, string shortstop, string catcher, string pitcher, string rightfield, string centerfield, string leftfield, string dhitter);
        //[OperationContract, WebGet]
        //XmlDocument CreateMLBTeam2(string leagueId, string name, string description);
        [OperationContract, WebGet]
        XmlDocument UpdateMLBTeam(int id, string position, int playerId);
        [OperationContract, WebGet]
        XmlDocument DeleteMLBTeam(int id);
        [OperationContract, WebGet]
        XmlDocument GetMLBTeam(int id);

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
    }
}
