

-- mxID = 642
-- msxID = 1
-- moID = 1

-- select Matrices.mxID from dbo.Matrices where Matrices.mxCode = '615000010_TR'

-- select MatrixValueSets.mxsID from dbo.MatrixValueSets where MatrixValueSets.mxID = 642

-- select moID from  dbo.MatrixOutputs where MatrixOutputs.mxID = 642


-- cpID = 68
select cpID from dbo.CriteriaProperties where cpCode = 'ProductCode'

select * from dbo.MatrixValueSetValues where MatrixValueSetValues.mxID = 642;

-- 1. Tabellenwertparameter erstellen
DECLARE @werte tt.VPchar;

DECLARE @mxsvInstanceID int;

-- 2. Daten in den Tabellenwertparameter einfÃ¼gen
INSERT INTO @werte (vpID, vpValue) VALUES (1, 24.50);

-- 3. Stored Procedure aufrufen

EXEC dbo.spBAS_mrgMatrixValueSetValues
    @mxID = 642,                       -- Matrices.mxID from dbo.Matrices
    @mxsID = 1,                        -- MatrixValueSets.mxsID from dbo.MatrixValueSets
    @mxsvInstanceID = @mxsvInstanceID OUTPUT, -- (Wird ggf. generiert)
    @mxsvSequence = 0,                 -- fix 0 ?
    @cpXML =  N'<root><Criteria cpID="68" IsInverseOperator="false" OperatorID="5"><Instance ID="1" Min="0003229999"/></Criteria></root>',
    @val = @werte;                       -- Ãœbergabe des Tabellenwertparameters