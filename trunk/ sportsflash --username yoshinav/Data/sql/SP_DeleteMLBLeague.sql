USE [SportsFlash]
GO
/****** Object:  StoredProcedure [dbo].[SP_DeleteMLBLeague]    Script Date: 07/20/2008 22:54:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Navdeep Alam>
-- Create date: <July 19, 2008>
-- Description:	<Delete MLB League>
-- =============================================
ALTER PROCEDURE [dbo].[SP_DeleteMLBLeague]
	-- Add the parameters for the stored procedure here
	@id int

AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Update
	DELETE FROM MLBFantasyLeagues WHERE leagueid = @id
	
	--Return ID
	select @@IDENTITY	

END
