create proc [dbo].[sp_Layout_GetDetailTable]
@Suc as int,
@Dep as int,
@Lin as int,
@SqlSelectionCommand as varchar(3)
as
begin

--DECLARE @WhereSelectionPrueba as varchar(3)
DECLARE @Query as nvarchar(max) 

--set @WhereSelectionPrueba = 'VFF'
set @Query ='SELECT CATSUCURSALES_LAYOUT_DET.idDet,CATSUCURSALES.NOMSUC_SUC,CATDEPTOSART.NOMDEP_DEP,CATLINEAS.NOMLIN_LIN,CATSUBLINEAS.DESSUB_SUB,CATSUCURSALES_LAYOUT_DET.Cant from CATSUCURSALES_LAYOUT_DET'
set @Query = @Query + ' inner join CATSUCURSALES_LAYOUT on CATSUCURSALES_LAYOUT_DET.idMaster = CATSUCURSALES_LAYOUT.idMaster'
set @Query = @Query + ' inner join CATSUCURSALES on CATSUCURSALES_LAYOUT.CODSUC_SUC = CATSUCURSALES.CODSUC_SUC'
set @Query = @Query + ' inner join CATDEPTOSART on CATSUCURSALES_LAYOUT_DET.CODDEP_DEP = CATDEPTOSART.CODDEP_DEP'
set @Query = @Query + ' inner join CATLINEAS on CATSUCURSALES_LAYOUT_DET.CODLIN_LIN = CATLINEAS.CODLIN_LIN'
set @Query = @Query + ' inner join CATSUBLINEAS on CATSUCURSALES_LAYOUT_DET.CODSUB_SUB = CATSUBLINEAS.CODSUB_SUB '

set @Query = @Query + case
									when @SqlSelectionCommand = 'VVV' then 'where CATSUCURSALES_LAYOUT_DET.idMaster = @Suc and CATSUCURSALES_LAYOUT_DET.CODDEP_DEP =@Dep and CATSUCURSALES_LAYOUT_DET.CODLIN_LIN =@Lin'
									when @SqlSelectionCommand = 'VVF' then 'where CATSUCURSALES_LAYOUT_DET.idMaster = @Suc and CATSUCURSALES_LAYOUT_DET.CODDEP_DEP =@Dep'
									when @SqlSelectionCommand = 'VFF' then 'where CATSUCURSALES_LAYOUT_DET.idMaster = @Suc'
									when @SqlSelectionCommand = 'FVV' then 'where CATSUCURSALES_LAYOUT_DET.CODDEP_DEP =@Dep and CATSUCURSALES_LAYOUT_DET.CODLIN_LIN =@Lin'
									when @SqlSelectionCommand = 'FVF' then 'where CATSUCURSALES_LAYOUT_DET.CODDEP_DEP =@Dep'
									else ''
								  end
--print(@QueryPrueba)
--EXEC(@QueryPrueba)
Execute sp_executesql @Query,
N'@Suc int, @Dep int, @Lin int, @SqlSelectionCommand varchar(3)',
@Suc,
@Dep,
@Lin,
@SqlSelectionCommand;
end
GO


