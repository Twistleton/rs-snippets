--
--  select rb.rs_getOrganizationAttribute(77403, 'Staat', '___') as Staat;
--

CREATE OR ALTER FUNCTION "RB"."rs_getOrganizationAttribute" (@venID int, @atbID nchar(255), @nullResult nchar(255))
    RETURNS varchar(255) AS
BEGIN

    DECLARE @result varchar(255)
    DECLARE @resultLen int

    SELECT @resultLen = LEN(@nullResult)

    SELECT @result = substring(isnull(( select venavValue
        from dbo.OrganizationAttributeValues
        , dbo.Attributes
        where OrganizationAttributeValues.atbID = Attributes.atbID
        and OrganizationAttributeValues.venID = @venID
        and Attributes.atbCode = @atbID), @nullResult),1, @resultLen)

        IF(@resultLen > LEN(@result))
    SET @result =  @result + REPLICATE(' ', @resultLen - LEN(@result))

    RETURN @result
END;