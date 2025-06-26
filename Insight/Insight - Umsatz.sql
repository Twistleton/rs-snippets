
-- Get Orders

--select Orders.ordID as ID
--     , Orders.ordOrderNo as Auftragsnummer
--     , Orders.venID as Kundennummer
--     , Organizations.venCompanyName as Kundenkurzbezeichnung
--     , Orders.ordSchedShipDate as Fakturierungdatum
--     , datepart(year,  Orders.ordSchedShipDate) as Fakturierungsjahr
--     , datepart(month, Orders.ordSchedShipDate) as Fakturierungsmonat
--     , datepart(day,   Orders.ordSchedShipDate) as Fakturierungstag
--     , datepart(week,  Orders.ordSchedShipDate) as Fakturierungswoche
--     , Orders.ordInfoField2 as Kundenbestelltext
--     , Orders.otpID as Auftragstyp
--     , dbo.Orders.*
--  from dbo.Orders
--     , dbo.Organizations
-- where Orders.venID = Organizations.venID
--   and Orders.ordSchedShipDate between '2023-03-22'
--                                   and '2023-03-23'


-- Get Auftragsattribute

--select ordId
--     , atbCode
--     , atbDescription
--     , ordavValue
--  from dbo.OrderAttributeValues
--     , dbo.Attributes
-- where OrderAttributeValues.atbID = Attributes.atbID
--   and OrderAttributeValues.ordID = 2789


-- Stati

--select OrderStatusLog.ordID
--     , OrderStatusLog.ordlChangeDate
--     , OrderStatuses.sttValue
--     , OrderStatuses.sttDescription
--  from dbo.OrderStatusLog
--     , dbo.OrderStatuses
-- where OrderStatusLog.sttID = OrderStatuses.sttID
--   and OrderStatusLog.ordID = 2789

-- Auftragsfertigmeldung

--select OrderStatusLog.ordID
--     , OrderStatusLog.ordlChangeDate
--     , OrderStatuses.sttValue
--     , OrderStatuses.sttDescription
--  from dbo.OrderStatusLog
--     , dbo.OrderStatuses
-- where OrderStatusLog.sttID = OrderStatuses.sttID
--   and OrderStatusLog.ordID = 2789
--   and sttValue = 1800


-- Programmnummer

--select min(Catalogs.clCode) as Programmnummer
-- from dbo.OrderLineProducts
--    , product.Catalogs
-- where OrderLineProducts.clID = Catalogs.clID
--   and OrderLineProducts.ordID = 2789

-- Auftragstyp (otpID)

--select Orders.ordID as Auftragstyp
--     , OrderTypes.otpCode
--     , OrderTypes.otpDescription
--  from dbo.Orders
--     , dbo.OrderTypes
-- where Orders.otpID = OrderTypes.otpID
--   and Orders.ordID = 2789

-- Markt (maID)

--select Orders.ordID
--     , Markets.maCode
--     , Markets.maDescription
--  from dbo.Orders
--     , Product.Markets
-- where Orders.maID = Markets.maID
--   and Orders.ordID = 2789

-- Vertriebsschiene (salcID)

--select Orders.ordID
--     , SalesChannels.salcCode
--     , SalesChannels.salcDescription
--  from dbo.Orders
--     , Product.SalesChannels
-- where Orders.salcID = SalesChannels.salcID
--   and Orders.ordID = 2789


----------------------------------------------------------------------------

-- Auftrag
-- select * from dbo.Orders where Orders.ordID = 2662

-- Kundenkurzbezeichnung
--select venCompanyName as Kundenkurzbezeichnung
--  from dbo.Organizations
-- where Organizations.venID = 77191


-- Verkaufsgebiet
--select atbCode
--     , ordavValue
--  from dbo.OrderAttributeValues
--     , dbo.Attributes
-- where OrderAttributeValues.atbID = Attributes.atbID
--   and OrderAttributeValues.ordID = 2662
--   and Attributes.atbCode = 'VKG'

-- Attribute des Auftrags
--select atbCode
--     , ordavValue
--  from dbo.OrderAttributeValues
--     , dbo.Attributes
-- where OrderAttributeValues.atbID = Attributes.atbID
--   and OrderAttributeValues.ordID = 2662
--   and Attributes.atbCode in ('AA_01','AA_02','AA_03')

-- Auftragspositionen
-- select * from dbo.OrderLines where OrderLines.ordID = 2662

-- Produkt der Auftragspositionen
-- select * from dbo.OrderLineProducts where OrderLineProducts.ordID = 2662


-- select * from product.CatalogVersionProductVersionsBase where pdID in (4599, 4621)

-- select * from product.Catalogs

-- select * from product.Products

-- Auftragsattribute

--select dbo.OrderAttributeValues.*
--     , dbo.Attributes.*
--  from dbo.OrderAttributeValues
--     , dbo.Attributes
-- where OrderAttributeValues.atbID = Attributes.atbID
--   and OrderAttributeValues.ordID = 2662

-- Kunden-Attribute
select venID
     , atbCode
     , atbDescription
     , venavValue
from dbo.OrganizationAttributeValues
   , dbo.Attributes
where OrganizationAttributeValues.atbID = Attributes.atbID
  and OrganizationAttributeValues.venID = 77191


--select *
--  from dbo.OrderAttributeValues
--     , dbo.Attributes
--where OrderAttributeValues.atbID = Attributes.atbID
--  and OrderAttributeValues.ordID = 2522
--  and atbCode = 'VKG'


declare @ordId int = 2522

declare @atbCode varchar(10) = 'VKG'

declare @maxSpaces int = 2

declare @nullPatternNumber varchar(10) = '0000000000'
declare @nullPatternString varchar(10) = '__________'

select substring(isnull((select ordavValue
from dbo.OrderAttributeValues
    , dbo.Attributes
where OrderAttributeValues.atbID = Attributes.atbID
and OrderAttributeValues.ordID = @ordId
and atbCode = @atbCode), @nullPatternNumber),1,@maxSpaces)



CREATE OR ALTER FUNCTION "dbo"."rs_getOrderAttribute" (@ordId int, @atbCode nchar(10))
    RETURNS varchar(10) AS
BEGIN

    DECLARE @result varchar(100)

    SELECT @result = substring(isnull((SELECT ordavValue
        FROM dbo.OrderAttributeValues
        , dbo.Attributes
        WHERE OrderAttributeValues.atbID = Attributes.atbID
        AND OrderAttributeValues.ordID = @ordId
        AND atbCode = @atbCode), '0000000000'),1,2)

    RETURN @result
END;


select dbo.rs_getOrderAttribute(2662, 'AA_01'), dbo.rs_getOrderAttribute(2662, 'AA_02'), dbo.rs_getOrderAttribute(2662, 'AA_03'), dbo.rs_getOrderAttribute(2662, 'VKG');
