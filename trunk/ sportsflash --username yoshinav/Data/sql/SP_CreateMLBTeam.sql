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
-- Description:	<Create MLB Team>
-- =============================================
CREATE PROCEDURE [dbo].[SP_CreateMLBTeam]
	-- Add the parameters for the stored procedure here
	@leagueid int, 
	@name nvarchar(25),
	@description nvarchar(200)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	INSERT INTO MLBFantasyTeams (leagueid, name, description) VALUES (@leagueid, @name, @description)
	
	--Return ID
	select @@IDENTITY AS ID

END
GO
