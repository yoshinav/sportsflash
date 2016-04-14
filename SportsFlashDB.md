# Introduction #

This wiki is used to discuss and collaborate on the design of the database.


# Details #

The source for this data is from real time XML feeds from MLB Advanced Media (http://gd2.mlb.com/components/game/mlb/year_2008/).

The data is loaded into the database using an Microsoft Business Intelligence Integration Service project (MLBPlayer\_ELT), which can be found in the source code, in the "BI" package.

The following is the list of database tables:
  * MLBFantasyLeagues - SportsFlash MLB Fantasy Leagues
  * MLBFantasyTeams - SportsFlash MLB Fantasy Teams
  * MLBPlayers - MLB Players (as of June 4, 2008)
  * MLBFantasyMessageBoards - SportsFlash message boards

All data is available in the "Data" package of the source code.  DataBase diagram should be available from the downloads.