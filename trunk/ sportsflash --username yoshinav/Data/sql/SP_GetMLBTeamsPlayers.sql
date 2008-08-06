USE [SportsFlash]
GO
/****** Object:  StoredProcedure [dbo].[SP_GetMLBTeamsPlayers]    Script Date: 08/06/2008 17:54:18 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Navdeep Alam>
-- Create date: <June 29, 2008>
-- Description:	<Retrive MLB Teams by id>
-- =============================================
CREATE PROCEDURE [dbo].[SP_GetMLBTeamsPlayers]
	-- Add the parameters for the stored procedure here
	@teamid int,
	@playerPos int
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT a.name, a.description, b.firstname, b.lastname, b.position, b.team, b.stats_hr, b.stats_rbi, b.stats_battingaverage, b.stats_saves, b.stats_saves, b.stats_wins, b.stats_era from MLBFantasyTeams a, MLBPlayers b where a.teamid = @teamid AND b.id = @playerPos
END
