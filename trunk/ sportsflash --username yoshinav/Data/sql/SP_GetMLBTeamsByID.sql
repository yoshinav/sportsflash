USE [SportsFlash]
GO
/****** Object:  StoredProcedure [dbo].[SP_GetMLBTeamsByID]    Script Date: 08/06/2008 17:45:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Navdeep Alam>
-- Create date: <June 29, 2008>
-- Description:	<Retrive MLB Teams by id>
-- =============================================
CREATE PROCEDURE [dbo].[SP_GetMLBTeamsByID]
	-- Add the parameters for the stored procedure here
	@id int
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT b.name AS LeagueName, a.name AS TeamName, a.description AS TeamDescription, a.score AS Score , a.teamid AS teamid, a.[1bid], a.[2bid], a.[3bid], a.[ssid], a.[cid], a.[pid], a.[rfid], a.[cfid], a.[lfid], a.[dhid] from MLBFantasyTeams a, MLBFantasyLeagues b where a.leagueid = @id AND a.leagueid = b.leagueid
END
