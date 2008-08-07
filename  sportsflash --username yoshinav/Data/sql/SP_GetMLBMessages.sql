USE [SportsFlash]
GO
/****** Object:  StoredProcedure [dbo].[SP_GetMLBMessages]    Script Date: 08/06/2008 23:54:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Navdeep Alam>
-- Create date: <July 19, 2008>
-- Description:	<Get MLB League>
-- =============================================
CREATE PROCEDURE [dbo].[SP_GetMLBMessages]
	-- Add the parameters for the stored procedure here

AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Select Message
	SELECT title, message FROM MLBFantasyMessageBoards

END
