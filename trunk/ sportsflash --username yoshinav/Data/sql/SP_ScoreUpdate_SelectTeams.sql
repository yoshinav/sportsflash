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
-- Create date: <July 29, 2008>
-- Description:	<Select Teams from MLB Teams>
-- =============================================
CREATE PROCEDURE [dbo].[SP_ScoreUpdate_SelectTeams]

AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT teamid, name, description, leagueid, [1bid], [2bid], [3bid], [ssid], [cid], [pid], [rfid], [cfid], [lfid], [dhid], score from MLBFantasyTeams
END
GO
