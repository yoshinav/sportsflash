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

    -- Select League
	SELECT a.name, a.description, a.leagueid, a.[1bid], b.lastname, b.position, b.team,
	a.[

	FROM MLBFantasyTeams a, MLBPlayers b WHERE a.teamid = @id AND a.[1bid] = b.id

END
GO
