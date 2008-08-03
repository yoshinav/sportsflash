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
/// Summary description for DBHelper
/// </summary>
public class DBHelper
{
    public static string connectionStringTrusted = "Data Source=localhost;Initial Catalog=SportsFlash;Integrated Security=SSPI;";

    public static string connectionString = "Data Source=localhost;Initial Catalog=SportsFlash;User Id=sportsflash;Password=k1mp@ct;";

    public static string getMLBPlayers = "EXEC SP_GetMLBPlayers";

	public DBHelper()
	{
		//
		// TODO: Add constructor logic here
		//
	}

    public static string GetMLBPlayersByPosition(string position)
    {
        return "EXEC SP_GetMLBPlayersByPosition '" + position + "'";
        //return "EXEC SP_GetMLBPlayersByPosition '1b'";

    }

    public static string CreateMLBLeague(string name, string description)
    {
        return "EXEC SP_CreateMLBLeague '" + name + "','" + description + "'";
    }

    public static string CreateMLBTeam(int leagueid, string name, string description)
    {
        return "EXEC SP_CreateMLBTeam '" + leagueid + "','" + name + "','" + description + "'";
    }

    public static string DeleteMLBLeague(int leagueid)
    {
        return "EXEC SP_DeleteMLBLeague '" + leagueid + "'";
    }

    public static string DeleteMLBTeam(int teamid)
    {
        return "EXEC SP_DeleteMLBLTeam '" + teamid + "'";
    }

    public static string GetMLBLeague(int id)
    {
        return "EXEC SP_GetMLBLeague '" + id + "'";
    }

    public static string GetMLBTeam(int id)
    {
        return "EXEC SP_GetMLBTeam '" + id + "'";
    }

    public static string UpdateMLBLeague(int id, string name, string description)
    {
        return "EXEC SP_UpdateMLBLeague '" + id + "','" + name + "','" + description + "'";
    }

    public static string UpdateMLBTeam(int id, string position, int playerid)
    {
        return "EXEC SP_UpdateMLBTeam '" + id + "','" + position + "','" + playerid + "'";
    }

    public static string ApostrophieReplace(string value)
    {
        try
        {
            if (value != null)
            {
                return value.Replace("'", "''");

            }
            else
            {
                return null;
            }
        }
        catch
        {
            return null;
        }

    }
        
}
