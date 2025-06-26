--
--  select RB.rs_getProductVersionAttribute(14244, 'Collection', '00') as 'Collection';
--
CREATE OR ALTER FUNCTION "RB"."rs_getProductVersionAttribute" (@pdID int, @atbCode nchar(255), @nullResult nchar(255))
    RETURNS varchar(255) AS
BEGIN

    DECLARE @result varchar(255)
    DECLARE @resultLen int

    SELECT @resultLen = LEN(@nullResult)

    SELECT @result = substring(isnull((select TOP 1 pdvavValue
        from dbo.Attributes
        , Product.ProductVersionAttributeValues
        where Attributes.atbID = ProductVersionAttributeValues.atbID
        and pdID = @pdID
        and atbCode = @atbCode), @nullResult),1, @resultLen)

        IF(@resultLen > LEN(@result))
    SET @result =  @result + REPLICATE(' ', @resultLen - LEN(@result))

    RETURN @result
END;