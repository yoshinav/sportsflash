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
/// Author Navdeep Alam - CS 893
/// July 2008
/// Stored Procedures and DB Helper Class
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

    public static string GetMLBPlayerByID(string id)
    {
        return "EXEC SP_GetMLBPlayerByID " + id;
        //return "EXEC SP_GetMLBPlayersByPosition '1b'";

    }

    public static string GetMLBTeamsByID(string id)
    {
        return "EXEC SP_GetMLBTeamsByID " + id;
    }

    public static string GetMLBTeamsPlayers(string teamid, string playerid)
    {
        return "EXEC SP_GetMLBTeamsPlayers " + teamid + "," + playerid;
    }

    public static string CreateMLBLeague(string name, string description)
    {
        return "EXEC SP_CreateMLBLeague '" + name + "','" + description + "'";
    }

    public static string CreateMLBTeam(string leagueid, string name, string description, string firstbase, string secondbase, string thirdbase, string shortstop, string catcher, string pitcher, string rightfield, string centerfield, string leftfield, string dhitter)
    {
        return "EXEC SP_CreateMLBTeam " + leagueid + ",N'" + name + "',N'" + description + "'," + firstbase + "," + secondbase + "," + thirdbase + "," + shortstop + "," + catcher + "," + pitcher + "," + rightfield + "," + centerfield + "," + leftfield + "," + dhitter;
    }

    public static string UpdateMLBPlayer(string id, string hr, string ba, string rbi, string wins, string saves, string era)
    {
        return "EXEC SP_UpdateMLBPlayer " + id + "," + hr + "," + ba + "," + rbi + "," + wins + "," + saves + "," + era;
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
