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
    }
}
