select right('000000' + rtrim(Orders.ordOrderNo), 6) as Auftragsnummer
     , right('0000' + rtrim(datepart(year,  Orders.ordSchedShipDate)), 4) as Fakturierungsjahr
     , right('00'   + rtrim(datepart(month, Orders.ordSchedShipDate)), 2) as Fakturierungsmonat
     , right('00'   + rtrim(datepart(day,   Orders.ordSchedShipDate)), 2) as Fakturierungstag
     , OrderLines.olnLineNo
     , case OrderTypes.otpCode
           when 'GUT' then '31'
           else '30'
    end as Satzart
     , isnull((select min(Catalogs.clCode) as Programmnummer
               from dbo.OrderLineProducts
                  , product.Catalogs
               where OrderLineProducts.clID = Catalogs.clID
                 and OrderLineProducts.ordID = Orders.ordID), '000') as Programm
     , '??' as Katalogversion
     , Products.pdCode as Modellnummer
     , RB.rs_getProductVersionAttribute(Products.pdID, 'spez', '00') as Teilespezifikation
     , Products.pdDescription as Teilebezeichnung
     , RB.rs_getProductVersionAttribute(Products.pdID, 'Collection', '00') as Collection
     , OrderLineProducts.olnpdQuantity as Menge
     , OrderLineProducts.olnpdQuantity * cast((RB.rs_getProductVersionAttribute(Products.pdID, 'SE', '0')) as bigint) as Sitzeinheiten
     , '|'
     , OrderLineProducts.*
     , Products.pdID
from dbo.Orders
   , dbo.OrderLines
   , dbo.OrderLineProducts
   , dbo.OrderLineAttributeValues
   , dbo.OrderTypes
   , Product.Products
where Orders.ordID = OrderLines.ordID
  and OrderLines.olnID = OrderLineAttributeValues.olnID
  and OrderLineAttributeValues.olnavValue in ('K', 'M')
  and Orders.otpID = OrderTypes.otpID
  and OrderLines.olnID = OrderLineProducts.olnID
  and OrderLineProducts.pdID = Product.Products.pdID

  and Orders.ordOrderNo = 561
-- and Orders.ordSchedShipDate between '2022-03-01' and '2023-05-23'