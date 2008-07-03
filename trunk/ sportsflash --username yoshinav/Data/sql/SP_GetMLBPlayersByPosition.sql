USE [SportsFlash]
GO
/****** Object:  StoredProcedure [dbo].[SP_GetMLBPlayersByPosition]    Script Date: 07/03/2008 08:15:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Navdeep Alam>
-- Create date: <June 29, 2008>
-- Description:	<Retrive MLB Players by Position>
-- =============================================
CREATE PROCEDURE [dbo].[SP_GetMLBPlayersByPosition]
	-- Add the parameters for the stored procedure here
	@position char(2)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT * from MLBPlayers where position = @position
END
