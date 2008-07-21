-- ================================================
-- Template generated from Template Explorer using:
-- Create Procedure (New Menu).SQL
--
-- Use the Specify Values for Template Parameters 
-- command (Ctrl-Shift-M) to fill in the parameter 
-- values below.
--
-- This block of comments will not be included in
-- the definition of the procedure.
-- ================================================
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Navdeep Alam>
-- Create date: <July 19, 2008>
-- Description:	<Get MLB Team>
-- =============================================
CREATE PROCEDURE [dbo].[SP_GetMLBTeam]
	-- Add the parameters for the stored procedure here
	@id int

AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Select Team

	SELECT a.name, a.description, a.leagueid, a.[1bid], b.lastname AS [1bLastName], b.position AS [1b], b.team AS [1bTeam]
	FROM MLBFantasyTeams a, MLBPlayers b WHERE a.teamid = @id AND a.[1bid] = b.id
	UNION
	SELECT null, null, null, a.[2bid], b.lastname AS [2bLastName], b.position AS [2b], b.team AS [2bTeam]
	FROM MLBFantasyTeams a, MLBPlayers b WHERE a.teamid = @id AND a.[2bid] = b.id
	UNION
	SELECT null, null, null, a.[3bid], b.lastname AS [3bLastName], b.position AS [3b], b.team AS [3bTeam]
	FROM MLBFantasyTeams a, MLBPlayers b WHERE a.teamid = @id AND a.[3bid] = b.id
	UNION
	SELECT null, null, null, a.[ssid], b.lastname AS [ssLastName], b.position AS [ss], b.team AS [ssTeam]
	FROM MLBFantasyTeams a, MLBPlayers b WHERE a.teamid = @id AND a.[ssid] = b.id
	UNION
	SELECT null, null, null, a.[cid], b.lastname AS [cLastName], b.position AS [c], b.team AS [cTeam]
	FROM MLBFantasyTeams a, MLBPlayers b WHERE a.teamid = @id AND a.[cid] = b.id
	UNION
	SELECT null, null, null, a.[pid], b.lastname AS [pLastName], b.position AS [p], b.team AS [pTeam]
	FROM MLBFantasyTeams a, MLBPlayers b WHERE a.teamid = @id AND a.[pid] = b.id
	UNION
	SELECT null, null, null, a.[rfid], b.lastname AS [rfLastName], b.position AS [rf], b.team AS [rfTeam]
	FROM MLBFantasyTeams a, MLBPlayers b WHERE a.teamid = @id AND a.[rfid] = b.id
	UNION
	SELECT null, null, null, a.[cfid], b.lastname AS [cfLastName], b.position AS [cf], b.team AS [cfTeam]
	FROM MLBFantasyTeams a, MLBPlayers b WHERE a.teamid = @id AND a.[cfid] = b.id
	UNION
	SELECT null, null, null, a.[lfid], b.lastname AS [lfLastName], b.position AS [lf], b.team AS [lfTeam]
	FROM MLBFantasyTeams a, MLBPlayers b WHERE a.teamid = @id AND a.[lfid] = b.id
	UNION
	SELECT null, null, null, a.[dhid], b.lastname AS [dhLastName], b.position AS [dh], b.team AS [dhTeam]
	FROM MLBFantasyTeams a, MLBPlayers b WHERE a.teamid = @id AND a.[dhid] = b.id
END
GO
