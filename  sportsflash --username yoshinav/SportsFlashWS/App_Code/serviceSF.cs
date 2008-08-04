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
        XmlDocument CreateMLBTeam(int leagueId, string name, string description);
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

            SqlConnection conn = new SqlConnection(DBHelper.connectionString);
            SqlDataAdapter a = new SqlDataAdapter
           (DBHelper.getMLBPlayers, conn);
            DataSet s = new DataSet();
            a.Fill(s);
            //XmlDataDocument datasetXML = new XmlDataDocument(s);
            XmlDocument datasetXML = new XmlDocument();
            datasetXML.LoadXml(s.GetXml());
            return datasetXML;
        }

        public XmlDocument GetMLBPlayersByPosition(string position)
        {
            SqlConnection conn = new SqlConnection(DBHelper.connectionString);
            SqlDataAdapter a = new SqlDataAdapter
           (DBHelper.GetMLBPlayersByPosition(position), conn);
            DataSet s = new DataSet();
            a.Fill(s);
            //XmlDataDocument datasetXML = new XmlDataDocument(s);
            XmlDocument datasetXML = new XmlDocument();
            datasetXML.LoadXml(s.GetXml());
            return datasetXML;
        }

        public XmlDocument CreateMLBLeague(string name, string description)
        {
            SqlConnection conn = new SqlConnection(DBHelper.connectionString);
            SqlDataAdapter a = new SqlDataAdapter
           (DBHelper.CreateMLBLeague(name,description), conn);
            DataSet s = new DataSet();
            a.Fill(s);
            //XmlDataDocument datasetXML = new XmlDataDocument(s);
            XmlDocument datasetXML = new XmlDocument();
            datasetXML.LoadXml(s.GetXml());
            return datasetXML;           
        }

        public XmlDocument UpdateMLBLeague(int id, string name, string description)
        {
            SqlConnection conn = new SqlConnection(DBHelper.connectionString);
            SqlDataAdapter a = new SqlDataAdapter
           (DBHelper.UpdateMLBLeague(id,name, description), conn);
            DataSet s = new DataSet();
            a.Fill(s);
            //XmlDataDocument datasetXML = new XmlDataDocument(s);
            XmlDocument datasetXML = new XmlDocument();
            datasetXML.LoadXml(s.GetXml());
            return datasetXML;
        }

        public XmlDocument DeleteMLBLeague(int id)
        {
            SqlConnection conn = new SqlConnection(DBHelper.connectionString);
            SqlDataAdapter a = new SqlDataAdapter
           (DBHelper.DeleteMLBLeague(id), conn);
            DataSet s = new DataSet();
            a.Fill(s);
            //XmlDataDocument datasetXML = new XmlDataDocument(s);
            XmlDocument datasetXML = new XmlDocument();
            datasetXML.LoadXml(s.GetXml());
            return datasetXML;        
        }

        public XmlDocument GetMLBLeague(int id)
        {
            SqlConnection conn = new SqlConnection(DBHelper.connectionString);
            SqlDataAdapter a = new SqlDataAdapter
           (DBHelper.GetMLBLeague(id), conn);
            DataSet s = new DataSet();
            a.Fill(s);
            //XmlDataDocument datasetXML = new XmlDataDocument(s);
            XmlDocument datasetXML = new XmlDocument();
            datasetXML.LoadXml(s.GetXml());
            return datasetXML;        
        }

        public XmlDocument CreateMLBTeam(int leagueId, string name, string description, int firstbase, int secondbase, int thirdbase, int shortstop, int catcher, int pitcher, int rightfield, int centerfield, int leftfield, int dhitter)
        {
            SqlConnection conn = new SqlConnection(DBHelper.connectionString);
            SqlDataAdapter a = new SqlDataAdapter
           (DBHelper.CreateMLBTeam(leagueId, name, description, firstbase, secondbase, thirdbase, shortstop, catcher, pitcher, rightfield, centerfield, leftfield, dhitter), conn);
            DataSet s = new DataSet();
            a.Fill(s);
            //XmlDataDocument datasetXML = new XmlDataDocument(s);
            XmlDocument datasetXML = new XmlDocument();
            datasetXML.LoadXml(s.GetXml());
            return datasetXML;        
        }

        public XmlDocument UpdateMLBTeam(int id, string position, int playerId)
        {
            SqlConnection conn = new SqlConnection(DBHelper.connectionString);
            SqlDataAdapter a = new SqlDataAdapter
           (DBHelper.UpdateMLBTeam(id, position, playerId), conn);
            DataSet s = new DataSet();
            a.Fill(s);
            //XmlDataDocument datasetXML = new XmlDataDocument(s);
            XmlDocument datasetXML = new XmlDocument();
            datasetXML.LoadXml(s.GetXml());
            return datasetXML;        
        }

        public XmlDocument DeleteMLBTeam(int id)
        {
            SqlConnection conn = new SqlConnection(DBHelper.connectionString);
            SqlDataAdapter a = new SqlDataAdapter
           (DBHelper.DeleteMLBTeam(id), conn);
            DataSet s = new DataSet();
            a.Fill(s);
            //XmlDataDocument datasetXML = new XmlDataDocument(s);
            XmlDocument datasetXML = new XmlDocument();
            datasetXML.LoadXml(s.GetXml());
            return datasetXML;        
        }

        public XmlDocument GetMLBTeam(int id)
        {
            SqlConnection conn = new SqlConnection(DBHelper.connectionString);
            SqlDataAdapter a = new SqlDataAdapter
           (DBHelper.GetMLBTeam(id), conn);
            DataSet s = new DataSet();
            a.Fill(s);
            //XmlDataDocument datasetXML = new XmlDataDocument(s);
            XmlDocument datasetXML = new XmlDocument();
            datasetXML.LoadXml(s.GetXml());
            return datasetXML;        
        }
    }
}
