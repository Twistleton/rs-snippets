#!/bin/bash

#
# ./insight-db2.sh <datatype> <from-date-time> <to-date-time>
#
# ./insight-db2.sh ae                                          // Verarbeite die AE-Daten - Zeitraum wird automatisch ermittelt
# ./insight-db2.sh ae 2023-10-20T17:30:00 2023-10-21T17:29:59  // Verarbeite die AE-Daten des Zeitraums vom 20.10.2023 ab 17:30:00 Uhr bis zum 21.10.2023 um 17:29:59 Uhr
#
# ./insight-db2.sh um                                          // Verarbeite die Umsatz-Daten - Zeitraum wird automatisch ermittelt
# ./insight-db2.sh um 2023-10-20T17:30:00 2023-10-21T17:29:59  // Verarbeite die Umsatz-Daten des Zeitraums vom 20.10.2023 ab 17:30:00 Uhr bis zum 21.10.2023 um 17:29:59 Uhr
#
# ./insight-db2.sh pl                                          // Verarbeite die Platzierungsdaten - Zeitraum wird automatisch ermittelt
# ./insight-db2.sh pl 2023-10-20T17:30:00 2023-10-21T17:29:59  // Verarbeite die Platzierungsdaten des Zeitraums vom 20.10.2023 ab 17:30:00 Uhr bis zum 21.10.2023 um 17:29:59 Uhr
#


if [ $# -eq 0 ]; then
    datatype="ae"
else
    datatype="$1"
fi

if [ $# -ge 3 ]; then
    from_date="$2"
    to_date="$3"
else

    if [ "$datatype" = "ae" ]; then
        from_date=$(date +"%Y-%m-%dT00:00:00")
        to_date=$(date   +"%Y-%m-%dT17:59:59")
    else
        from_date=$(date -d "yesterday" +"%Y-%m-%dT18:03:00")
        to_date=$(date +"%Y-%m-%dT18:02:59")
    fi
fi

echo ""
echo "*** insight-db2.sh * processing $datatype from $from_date to $to_date ***"
echo ""


if [ "$datatype" = "ae" ]; then
   curl localhost:8010/api/$datatype/$from_date/$to_date > /root/Documents/Insight/Input/$datatype-$(date -I).json
else
   #curl localhost:8010/api/$datatype/status/1950/$from_date/$to_date > /root/Documents/Insight/Input/$datatype-$(date -I).json
   #
   # Status 1150 -> Testen
   # Status 1950 -> Fakturiert
   #
   curl localhost:8010/api/$datatype/status/1950/$from_date/$to_date > /root/Documents/Insight/Input/$datatype-$(date -I).json
fi


/opt/graalvm-jdk-21.0.1+12.1/bin/java -jar -Dspring.profiles.active=production /root/Documents/Insight/db2Loader-2024.7.jar --datatype=$datatype --filename=/root/Documents/Insight/Input/$datatype-$(date -I).json >> /root/Documents/Insight/Logs/insight-$datatype-db2-$(date -I).log
