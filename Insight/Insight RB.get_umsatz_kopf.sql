#

select   right('000000' + rtrim(Orders.ordOrderNo), 6) as Auftragsnummer
     , right('0000' + rtrim(datepart(year,  Orders.ordSchedShipDate)), 4) as Fakturierungsjahr
     , right('00'   + rtrim(datepart(month, Orders.ordSchedShipDate)), 2) as Fakturierungsmonat
     , right('00'   + rtrim(datepart(day,   Orders.ordSchedShipDate)), 2) as Fakturierungstag
     , right('00'   + rtrim(datepart(week,  Orders.ordSchedShipDate)), 2) as Fakturierungswoche
     , case OrderTypes.otpCode
           when 'GUT' then '31'
           else '30'
    end as Satzart
     , format(Orders.ordSchedShipDate, 'dd.MM.yyyy') as Fakturierungsdatum
     , rb.rs_getOrderStatusLog(Orders.ordID, 1800, '01.01.1970') as Auftragsfertigmeldung -- 1800 = Auftragsfertigmeldung
     , right('0000000000' + rtrim(Orders.otpID), 10) as Auftragstyp
     , right('0000000000' + rtrim(Orders.maID), 10) as Markt
     , right('0000000000' + rtrim(Orders.salcID), 10) as Vertriebsschiene
     , rb.rs_getOrderAttribute(Orders.ordID, 'AA_01', '00') as 'Auftragsart 1'
       , rb.rs_getOrderAttribute(Orders.ordID, 'AA_02', '00') as 'Auftragsart 2'
       , rb.rs_getOrderAttribute(Orders.ordID, 'AA_03', '00') as 'Auftragsart 3'
       , '0' as Gutschriftsart
     , right('0000000' + rtrim(Organizations.venCode), 7) as Kundennummer
     , isnull((select min(Catalogs.clCode) as Programmnummer
               from dbo.OrderLineProducts
                  , product.Catalogs
               where OrderLineProducts.clID = Catalogs.clID
                 and OrderLineProducts.ordID = Orders.ordID), '000') as Programm
     , '??' as Katalogversion
     , isnull((select ShipmentModes.shmCode from dbo.ShipmentModes where ShipmentModes.shmID = Orders.shmIDDefault), '000') as Versandart
     , rb.rs_getOrganizationAttribute(Orders.venID, 'AE/UM', '___') as Staatenkennziffer
     , rb.rs_getOrganizationAttribute(Orders.venID, 'Land', '___') as Bundesland
     , rb.rs_getOrganizationAttribute(Orders.venID, 'Kreis', '___') as Kreis
     , rb.rs_getOrganizationAttribute(Orders.venID, 'Absatzweg', '__') as Absatzweg
     , '??' as Bonuskennzeichen
     , isnull((select txcLocationCode
               from dbo.TaxCodes
               where txcID = Orders.taxID), '00') as Mehrwertsteuerschluessel
     , isnull((select TOP(1)
                          substring(c.venCode,5,8)
               from dbo.Organizations a
                  , dbo.OrganizationRelationshipSalesReps b
                  , dbo.Organizations c
               where a.venID = b.venIDFrom
                 and b.venIDTo = c.venID
                 and a.venID in (Orders.venID)
                 and c.venCode like 'ADM%'), '000') as ADM
     , isnull((select TOP(1)
                   b.orsrCommissionPercent
               from dbo.Organizations a
                  , dbo.OrganizationRelationshipSalesReps b
                  , dbo.Organizations c
               where a.venID = b.venIDFrom
                 and b.venIDTo = c.venID
                 and a.venID in (Orders.venID)
                 and c.venCode like 'ADM%'), 0) as Provision
     , rb.rs_getOrderAttribute(Orders.ordID, 'VKG',   '00') as 'Verkaufsgebiet'
       , Organizations.venCompanyName as Kundenkurzbezeichnung
     , rb.rs_getOrganizationAttribute(Orders.venID, 'Art', '0') as Kundenart
     , rb.rs_getOrganizationAttribute(Orders.venID, 'Status', '0') as Kundenart
     , rb.rs_getOrganizationAttribute(Orders.venID, 'Konzern', '0000000') as Konzern
     , left(ltrim(Orders.ordInfoField2) + '                                ', 32) as Kundenbestelltext

     , isnull((select PaymentTerms.payDaysToPay from dbo.PaymentTerms where payID =  Orders.payID), '0') as Valuta
     , isnull((select PaymentTerms.payDiscountDays from dbo.PaymentTerms where payID =  Orders.payID), '0') as Skontotage
     , isnull((select PaymentTerms.payDiscountPercent from dbo.PaymentTerms where payID =  Orders.payID), '0') as Skontotage
from dbo.Orders
   , dbo.Organizations
   , dbo.OrderTypes
where Orders.venID = Organizations.venID
  and Orders.otpID = OrderTypes.otpID
  and Orders.ordSchedShipDate between '2023-03-22'
    and '2023-03-23'