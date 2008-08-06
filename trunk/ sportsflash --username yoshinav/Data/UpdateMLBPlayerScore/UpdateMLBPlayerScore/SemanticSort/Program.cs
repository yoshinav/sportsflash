using System;
using System.Collections.Generic;
using System.Text;
using System.Data.SqlClient;
using System.Collections;
using System.Data;
using Microsoft.ApplicationBlocks.Data;

/*
 * Author: Navdeep Alam
 * CS 893
 * Update MLBFantasyTeams with their scores
 * Aug 7, 2008
 * 
 */
 
namespace UpdateMLBPlayerScore
{
    class Program
    {

        static void Main(string[] args)
        {
            int firstbaseID = 0;
            int secondbaseID = 0;
            int thirdbaseID = 0;
            int shortstopID = 0;
            int catcherID = 0;
            int pitcherID = 0;
            int rightfieldID = 0;
            int centerfieldID = 0;
            int leftfieldID = 0;
            int dhitterID = 0;

            int homeruns = 0;
            int battingaverage = 0;
            int rbi = 0;
            int saves = 0;
            int wins = 0;
            int era = 0;
            int cummulativeScore = 0;

            //Business Rules Engine
            ExpertBusinessRulesEngine ExpertBREngine = new ExpertBusinessRulesEngine();

            try
            {
                SqlConnection thisConnection = new SqlConnection(@"Network Library=DBMSSOCN;Data Source=localhost,1433;database=SportsFlash;User id=sportsflash;Password=k1mp@ct;MultipleActiveResultSets=True;");
                thisConnection.Open();

                SqlDataReader selectSearchResultsReader = SqlHelper.ExecuteReader(thisConnection, CommandType.StoredProcedure, "[dbo].[SP_ScoreUpdate_SelectTeams]");
                SqlParameter[] playerID = new SqlParameter[1];

                while (selectSearchResultsReader.Read())
                {
                    cummulativeScore = 0;
                    //FirstBase
                    SqlCommand selectSearchResultsReaderScore = new SqlCommand("[dbo].[SP_ScoreUpdate_GetScorePerPlayer]", thisConnection);
                    selectSearchResultsReaderScore.CommandType = CommandType.StoredProcedure;
                    selectSearchResultsReaderScore.Parameters.Add("@playerID", SqlDbType.Int);
                    selectSearchResultsReaderScore.Parameters["@playerID"].Value = selectSearchResultsReader["1bid"];
                    selectSearchResultsReaderScore.Parameters.Add("@return_value", SqlDbType.Int);
                    selectSearchResultsReaderScore.Parameters["@return_value"].Direction = ParameterDirection.Output;
                    selectSearchResultsReaderScore.ExecuteNonQuery();
                    cummulativeScore += (int)selectSearchResultsReaderScore.Parameters["@return_value"].Value;
                    
                    //SecondBase
                    selectSearchResultsReaderScore.Parameters["@playerID"].Value = selectSearchResultsReader["2bid"];
                    selectSearchResultsReaderScore.ExecuteNonQuery();
                    cummulativeScore += (int)selectSearchResultsReaderScore.Parameters["@return_value"].Value;

                    //ThirdBase
                    selectSearchResultsReaderScore.Parameters["@playerID"].Value = selectSearchResultsReader["3bid"];
                    selectSearchResultsReaderScore.ExecuteNonQuery();
                    cummulativeScore += (int)selectSearchResultsReaderScore.Parameters["@return_value"].Value;

                    //ShortStop
                    selectSearchResultsReaderScore.Parameters["@playerID"].Value = selectSearchResultsReader["ssid"];
                    selectSearchResultsReaderScore.ExecuteNonQuery();
                    cummulativeScore += (int)selectSearchResultsReaderScore.Parameters["@return_value"].Value;

                    //pitcher
                    selectSearchResultsReaderScore.Parameters["@playerID"].Value = selectSearchResultsReader["pid"];
                    selectSearchResultsReaderScore.ExecuteNonQuery();
                    cummulativeScore += (int)selectSearchResultsReaderScore.Parameters["@return_value"].Value;

                    //catcher
                    selectSearchResultsReaderScore.Parameters["@playerID"].Value = selectSearchResultsReader["cid"];
                    selectSearchResultsReaderScore.ExecuteNonQuery();
                    cummulativeScore += (int)selectSearchResultsReaderScore.Parameters["@return_value"].Value;

                    //leftfield
                    selectSearchResultsReaderScore.Parameters["@playerID"].Value = selectSearchResultsReader["lfid"];
                    selectSearchResultsReaderScore.ExecuteNonQuery();
                    cummulativeScore += (int)selectSearchResultsReaderScore.Parameters["@return_value"].Value;

                    //rightfield
                    selectSearchResultsReaderScore.Parameters["@playerID"].Value = selectSearchResultsReader["ssid"];
                    selectSearchResultsReaderScore.ExecuteNonQuery();
                    cummulativeScore += (int)selectSearchResultsReaderScore.Parameters["@return_value"].Value;

                    //centerfield
                    selectSearchResultsReaderScore.Parameters["@playerID"].Value = selectSearchResultsReader["ssid"];
                    selectSearchResultsReaderScore.ExecuteNonQuery();
                    cummulativeScore += (int)selectSearchResultsReaderScore.Parameters["@return_value"].Value;

                    //dhitte
                    selectSearchResultsReaderScore.Parameters["@playerID"].Value = selectSearchResultsReader["dhid"];
                    selectSearchResultsReaderScore.ExecuteNonQuery();
                    cummulativeScore += (int)selectSearchResultsReaderScore.Parameters["@return_value"].Value;

                    //Update Team Score
                    
                    SqlCommand selectSearchResultsReaderTeamUpdateScore = new SqlCommand("[dbo].[SP_ScoreUpdate_UpdateTeamScore]", thisConnection);
                    selectSearchResultsReaderTeamUpdateScore.CommandType = CommandType.StoredProcedure;
                    selectSearchResultsReaderTeamUpdateScore.Parameters.Add("@teamID", SqlDbType.Int);
                    selectSearchResultsReaderTeamUpdateScore.Parameters["@teamID"].Value = selectSearchResultsReader["teamid"];
                    selectSearchResultsReaderTeamUpdateScore.Parameters.Add("@score", SqlDbType.Int);
                    selectSearchResultsReaderTeamUpdateScore.Parameters["@score"].Value = cummulativeScore;
                    selectSearchResultsReaderTeamUpdateScore.ExecuteNonQuery();
                   // System.Console.WriteLine("Score:" + cummulativeScore);
                    selectSearchResultsReaderScore = null;
                    selectSearchResultsReaderTeamUpdateScore = null;

                }



                selectSearchResultsReader.Close();
                thisConnection.Close();
                //System.Console.WriteLine("Score Complete");
                //System.Console.ReadLine();
                
                //System.Console.WriteLine("Press Any Key to Finish!!!!");
                //System.Console.ReadLine();

            }
            catch (SqlException e)
            {
                Console.WriteLine(e.Message);
            }


        }
    }
}
