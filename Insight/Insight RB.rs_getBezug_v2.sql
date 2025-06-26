CREATE OR ALTER FUNCTION "RB"."rs_getBezug_v2" (@olnID int, @nullResult nchar(255))
    RETURNS varchar(255) AS
BEGIN

    DECLARE @result varchar(255)
    DECLARE @resultLen int

    SELECT @resultLen = LEN(@nullResult)

    SELECT @result = substring(isnull((select FeatureOptionAttributeValues.foavValue
        from Report.vwSalesOrderLineOptions_v6
        , Product.Options
        , Product.FeatureOptionAttributeValues
        where [vwSalesOrderLineOptions_v6].[Option] = Options.optCode
        and Options.optID = FeatureOptionAttributeValues.optID
        and vwSalesOrderLineOptions_v6.olnID = @olnID
        and FeatureOptionAttributeValues.ftrID =  1111
        and vwSalesOrderLineOptions_v6.Feature = '1001'
        and FeatureOptionAttributeValues.atbID = '1306'), @nullResult),1, @resultLen)

        IF(@resultLen > LEN(@result))
    SET @result =  @result + REPLICATE(' ', @resultLen - LEN(@result))

    RETURN @result
END