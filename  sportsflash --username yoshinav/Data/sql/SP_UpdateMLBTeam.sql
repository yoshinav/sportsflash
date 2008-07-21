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
-- Description:	<Update MLB League>
-- =============================================
CREATE PROCEDURE [dbo].[SP_UpdateMLBTeam]
	-- Add the parameters for the stored procedure here
	@id int,
	@position char(2), 
	@playerid int
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Update
	UPDATE MLBFantasyTeams 
		SET [1bid] = CASE WHEN @position = '1b' THEN @playerid END,
		[2bid] = CASE WHEN @position = '2b' THEN @playerid END,
		[3bid] = CASE WHEN @position = '3b' THEN @playerid END,
		[ssid] = CASE WHEN @position = 'ss' THEN @playerid END,
		[cid] = CASE WHEN @position = 'c' THEN @playerid END,
		[pid] = CASE WHEN @position = 'p' THEN @playerid END,
		[rfid] = CASE WHEN @position = 'rf' THEN @playerid END,
		[cfid] = CASE WHEN @position = 'cf' THEN @playerid END,
		[lfid] = CASE WHEN @position = 'lf' THEN @playerid END,
		[dhid] = CASE WHEN @position = 'dh' THEN @playerid END
	WHERE teamid = @id
	
	--Return ID
	select @@IDENTITY	

END
GO
