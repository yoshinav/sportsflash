USE [SportsFlash]
GO
/****** Object:  StoredProcedure [dbo].[SP_CreateMLBTeam]    Script Date: 08/04/2008 02:02:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Navdeep Alam>
-- Create date: <July 19, 2008>
-- Description:	<Create MLB Team>
-- =============================================
CREATE PROCEDURE [dbo].[SP_CreateMLBTeam]
	-- Add the parameters for the stored procedure here
	@leagueid int, 
	@name nvarchar(25),
	@description nvarchar(200),
	@firstbase int,
	@secondbase int,
	@thirdbase int,
	@shortstop int,
	@catcher int,
	@pitcher int,
	@rightfield int,
	@centerfield int,
	@leftfield int,
	@dhitter int
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	INSERT INTO MLBFantasyTeams (leagueid, name, description, [1bid], [2bid], [3bid], [ssid], [cid], [pid], [rfid], [cfid], [lfid], [dhid]) VALUES (@leagueid, @name, @description, @firstbase, @secondbase, @thirdbase, @shortstop, @catcher, @pitcher,  @rightfield, @centerfield, @leftfield, @dhitter)
	
	--Return ID
	select @@IDENTITY AS ID

END
