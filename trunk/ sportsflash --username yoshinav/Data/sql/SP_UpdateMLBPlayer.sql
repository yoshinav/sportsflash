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
-- Description:	<Update MLB Player>
-- =============================================
CREATE PROCEDURE [dbo].[SP_UpdateMLBPlayer]
	-- Add the parameters for the stored procedure here
	@id int,
	@hr int, 
	@ba int,
	@rbi int,
	@wins int,
	@saves int,
	@era int
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Update
	UPDATE MLBPlayers SET [stats_hr] = @hr, [stats_rbi] = @rbi, [stats_battingaverage] = @ba, [stats_saves] = @saves, [stats_wins] = @wins, [stats_era] = @era WHERE id = @id
	
	--Return ID
	select @@IDENTITY		

END
GO
