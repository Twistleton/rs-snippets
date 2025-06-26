--
--  select rb.rs_getOrderStatusLog(2372, 1800, '01.01.1970');
--
CREATE OR ALTER FUNCTION "RB"."rs_getOrderStatusLog" (@ordID int, @sstValue int, @nullResult nchar(255))
    RETURNS varchar(255) AS
BEGIN

    DECLARE @result varchar(255)
    DECLARE @resultLen int

    SELECT @resultLen = LEN(@nullResult)

    SELECT @result = isnull((select CONVERT(VARCHAR(25), MAX(OrderStatusLog.ordlChangeDate), 104)
        from dbo.OrderStatusLog
        , dbo.OrderStatuses
        where OrderStatusLog.sttID = OrderStatuses.sttID
        and OrderStatusLog.ordID = @ordID
        and OrderStatuses.sttValue = @sstValue), @nullResult)

        IF(@resultLen > LEN(@result))
    SET @result =  @result + REPLICATE(' ', @resultLen - LEN(@result))

    RETURN @result
END;