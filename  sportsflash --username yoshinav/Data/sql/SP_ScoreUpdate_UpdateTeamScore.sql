USE [SportsFlash]
GO
/****** Object:  StoredProcedure [dbo].[SP_ScoreUpdate_UpdateTeamScore]    Script Date: 08/06/2008 16:39:44 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Navdeep Alam>
-- Create date: <July 29, 2008>
-- Description:	<Update Teams scores>
-- =============================================
CREATE PROCEDURE [dbo].[SP_ScoreUpdate_UpdateTeamScore]
	@teamID int,
	@score int
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	UPDATE MLBFantasyTeams SET score = @score where teamid = @teamID

END
