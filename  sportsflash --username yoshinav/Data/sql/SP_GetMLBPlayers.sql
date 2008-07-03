USE [SportsFlash]
GO
/****** Object:  StoredProcedure [dbo].[SP_GetMLBPlayers]    Script Date: 07/03/2008 07:49:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Navdeep Alam>
-- Create date: <July 2, 2008>
-- Description:	<Retrieve all MLBPlayers>
-- =============================================
CREATE PROCEDURE [dbo].[SP_GetMLBPlayers]
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT * from MLBPlayers
END
