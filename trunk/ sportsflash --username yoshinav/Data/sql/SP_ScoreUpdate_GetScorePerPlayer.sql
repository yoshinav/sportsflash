USE [SportsFlash]
GO
/****** Object:  StoredProcedure [dbo].[SP_ScoreUpdate_GetScorePerPlayer]    Script Date: 08/06/2008 16:34:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Navdeep Alam>
-- Create date: <July 29, 2008>
-- Description:	<Select Teams from MLB Teams>
-- =============================================
CREATE PROCEDURE [dbo].[SP_ScoreUpdate_GetScorePerPlayer]
	@playerID int,
	@return_value int OUTPUT
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT @return_value = ([stats_hr] + [stats_rbi] + [stats_battingaverage] + [stats_saves] + [stats_wins] + [stats_era]) from MLBPlayers where id = @playerID
	return @return_value

END
