--
--  select rb.rs_getItemAttribute(166826, 'RBSAPNR', '00000000000000') as 'tstsaptnr';
--


CREATE OR ALTER FUNCTION "RB"."rs_getItemAttribute" (@itemId int, @atbCode nchar(255), @nullResult nchar(255))
    RETURNS varchar(255) AS
BEGIN

    DECLARE @result varchar(255)
    DECLARE @resultLen int

    SELECT @resultLen = LEN(@nullResult)

    SELECT @result = substring(isnull((select itmavValue
        from dbo.Attributes
        , dbo.ItemAttributeValues
        where Attributes.atbID = ItemAttributeValues.atbID
        and ItemAttributeValues.itmID = @itemID
        and atbCode = @atbCode), @nullResult),1, @resultLen)


        IF(@resultLen > LEN(@result))
    SET @result =  @result + REPLICATE(' ', @resultLen - LEN(@result))

    RETURN @result
END;