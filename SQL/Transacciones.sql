--TIPS PARA CREAR SPs

--1) Usar identaci�n
--2) Especificar autor, fecha de creaci�n, descripci�n breve y comentarios que ayuden a entender la l�gica.
--3) Declarar e inicializar las variables al inicio
--4) Agregar SET NOCOUNT ON para evitar el mensaje innecesario de n�mero de filas afectadas.
--5) Solo crear tablas temporales y cursores si es necesario para ya que impactan en el rendimiento.
--6) Devolver solo las columnas necesarias, evitar �SELECT * �.
--7) Es mejor usar CASE que el anidamiento de IF-ELSE.

--ADICIONALMENTE

--Por favor cuando creen Stored Procedures (o Tablas) para un m�dulo en especial o para un procedimiento espec�fico,
--si van a ser varios, usen un prefijo al nombrarlos.

--Por ejemplo: Pronostico de Compras: sp_RespPron_1

--Rescate de Cobranza: sp_ResCob_Consulta, sp_ResCob_CambioEstatus

--En el caso de Tablas, por ejemplo, para el m�dulo de manejo de tarjeta de Cr�dito Elizondo:

--BitCambios_Tarjeta		�	Tarjeta_BitCambios
--CatEstatus_Tarjeta		�	Tarjeta_CatEstatus         
--MapeoClientes_Tarjeta	�	Tarjeta_MapeoClientes
--Cat_Tarjeta				�	Tarjeta_Cat�logo

-- **** EJEMPLO DE PROCEDIMIENTO ALMACENADO

--USE [BDELI]
--GO
--=============================================================================================================
-- AUTOR: HIRAM ENCINAS DREW
-- CREACION: 2020-12-01 [YYYY-MM-DD]
-- DESCRIPCION: [Objetivo y detalles de el procedimiento almacenado.]
---------------------------------------------------------------------------------------------------------------
-- FECHA		MODIFICADOR			DESCRIPCION
---------------------------------------------------------------------------------------------------------------
-- 2020-12-15	IVAN MONREAL		Se agreg� la columna 'X' en la inserci�n de un nuevo cliente. [Detalles del Cambio] 
--=============================================================================================================
CREATE PROCEDURE sp_PedidosMovil_ValidarGarantiaExtendida
	@pCLIENTE_ID INT
AS
BEGIN TRAN
	BEGIN TRY
	
		-- INICIA SCRIPT ***************************************************************************************************************************


		-- # VARIABLES LOCALES
		
		DECLARE
			@NOMBRE VARCHAR(100),
			@PATERNO VARCHAR(60),
			@MATERNO VARCHAR(60)

		-- # ASIGNACIONES
		SELECT TOP 1
			@NOMBRE	= ISNULL(LTRIM(RTRIM(NOMRAS_CTE)), ''),
			@PATERNO = ISNULL(LTRIM(RTRIM(PATRES_CTE)), ''),
			@MATERNO = ISNULL(LTRIM(RTRIM(MATRFC_CTE)), '')
		FROM 
			CATCLIENTESNVA
		WHERE
			NUMCTE_CTE = @pCLIENTE_ID
		ORDER BY 
			NUMCTE_CTE ASC;

		-- # INSERCION DE EJEMPLO
		INSERT INTO 
			ERRORESLOG
		VALUES
		(
			'10',
			'20',
			'30',
			'40',
			@NOMBRE,
			@PATERNO,
			@MATERNO,
			GETDATE()
		);

		-- # EJEMPLO DE PROVOCAR UN ERROR
		-- Generate a divide-by-zero error  
		SELECT 1 / 0 AS Error;
		--DECLARE @i INT;
		--SET @i = CONVERT(INT,'ABC');

		--RAISERROR ('Text', -- Message text.  
		--	16, -- Severity.  
		--	1 -- State.  
		--); 


		IF LEN(@NOMBRE) <= 0 BEGIN

			-- # EJEMPLO DE MENSAJE NEGATIVO RESULTANTE HACIA EL API
			SELECT 'No' RESULT, 'La cuenta ingresada no es v�lida.' [MESSAGE];

		END
		BEGIN

			-- # EJEMPLO DE MENSAJE NEGATIVO RESULTANTE HACIA EL API
			SELECT 'Ok' RESULT, 'La cuenta registrada con �xisto.' [MESSAGE];

		END


		-- FINALIZA SCRIPT *************************************************************************************************************************

	END TRY
	BEGIN CATCH

		IF @@TRANCOUNT > 0
        BEGIN
            ROLLBACK TRAN;
        END

		SELECT 'Fail' RESULT, 'Fall� el sp con los parametros: @ClienteID = ' + CAST(@pCLIENTE_ID AS VARCHAR(10)) + ';' [MESSAGE];

		DECLARE
			@Message VARCHAR(MAX) = 'Parameters: @ClienteID = ' + CAST(@pCLIENTE_ID AS VARCHAR(10)) + '; Message: ' + ERROR_MESSAGE(),
			@Severity INT = ERROR_SEVERITY(),
			@State SMALLINT = ERROR_STATE();

		INSERT INTO 
			ERRORESLOG 
		VALUES
		(
			SUSER_SNAME(),
			ERROR_NUMBER(),
			ERROR_STATE(),
			ERROR_SEVERITY(),
			ERROR_LINE(),
			ERROR_PROCEDURE(),
			@Message,
			GETDATE()
		);
 
		RAISERROR(@Message, @Severity, @State);
	END CATCH
IF @@TRANCOUNT >0
BEGIN
    COMMIT TRAN;
END
GO



-- **** CONSULTAR TABLA DE ERRORES DE PROCEDIMIENTOS ALMACENADOS

SELECT TOP 10
	*
FROM
	ERRORESLOG
ORDER BY 1 DESC;