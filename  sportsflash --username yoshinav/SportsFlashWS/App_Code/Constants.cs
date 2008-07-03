using System;
using System.Data;
using System.Configuration;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Web.UI.HtmlControls;

/// <summary>
/// Summary description for Constants
/// </summary>
public class Constants
{
	public Constants()
	{
		//
		// TODO: Add constructor logic here
		//
	}

    public static string connectionStringTrusted = "Data Source=localhost;Initial Catalog=SportsFlash;Integrated Security=SSPI;";

    public static string connectionString = "Data Source=localhost;Initial Catalog=SportsFlash;User Id=sportsflash;Password=k1mp@ct;";

    public static string getMLBPlayers = "EXEC SP_GetMLBPlayers";
}
