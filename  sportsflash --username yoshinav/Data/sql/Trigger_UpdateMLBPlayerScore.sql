USE [SportsFlash]
GO
/****** Object:  StoredProcedure [dbo].[SP_UpdateMLBPlayer]    Script Date: 08/06/2008 11:55:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Navdeep Alam>
-- Create date: <July 19, 2008>
-- Description:	<Update MLB Player>
-- =============================================
CREATE TRIGGER UpdateMLBPlayerScore
   ON  [SportsFlash] 
   AFTER UPDATE
AS 
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
 
    -- Insert statements for trigger here
 
IF UPDATE (status)
BEGIN
 
DECLARE @Shr int
DECLARE @Srbi int
DECLARE @Sba int
DECLARE @Ssaves int
DECLARE @Swins int
DECLARE @Sera int
DECLARE @Sid int
DECLARE @Score int

SELECT @Shr = (SELECT [stats_hr] from inserted with (NOLOCK))
SELECT @Srbi = (SELECT [stats_rbi] from inserted with (NOLOCK))
SELECT @Sba = (SELECT [stats_battingaverage] from inserted with (NOLOCK))
SELECT @Ssaves = (SELECT [stats_saves] from inserted with (NOLOCK))
SELECT @Swins = (SELECT [stats_wins] from inserted with (NOLOCK))
SELECT @Sera = (SELECT [stats_era] from inserted with (NOLOCK))
SELECT @Sid = (SELECT [id] from inserted with (NOLOCK))

@Score = @Shr + @Srbi + @Sba + @Ssaves + @Swins + @Sera + @Sid

UPDATE [MLBPlayers] Set [

END
