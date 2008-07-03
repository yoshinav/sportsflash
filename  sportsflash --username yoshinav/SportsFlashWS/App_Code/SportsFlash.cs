using System;
using System.Web;
using System.Web.Services;
using System.Web.Services.Protocols;
using System.Xml;
using System.Data;
using System.Data.SqlClient;

[WebService(Namespace="http://navcs893.org/",
Description="Web Service to support SportsFlash")]
[WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
public class SportsFlash : System.Web.Services.WebService
{
    public SportsFlash () {

        //Uncomment the following line if using designed components 
        //InitializeComponent(); 
    }

    [WebMethod]
    public string HelloWorld() {
        return "Hello World";
    }

    [WebMethod]
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

    [WebMethod]
    public XmlDocument GetMLBPlayers()
    {

        SqlConnection conn = new SqlConnection(DBHelper.connectionString);
        SqlDataAdapter a = new SqlDataAdapter
       (DBHelper.getMLBPlayers, conn);
        DataSet s = new DataSet();
        a.Fill(s);
        XmlDataDocument datasetXML = new XmlDataDocument(s);

        return (XmlDocument)datasetXML; 

    }

    [WebMethod]
    public XmlDocument GetMLBPlayersByPosition(string position)
    {
        SqlConnection conn = new SqlConnection(DBHelper.connectionString);
        SqlDataAdapter a = new SqlDataAdapter
       (DBHelper.GetMLBPlayersByPosition(position), conn);
        DataSet s = new DataSet();
        a.Fill(s);
        XmlDataDocument datasetXML = new XmlDataDocument(s);

        return (XmlDocument)datasetXML; 

    }
}
