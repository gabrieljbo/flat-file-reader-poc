# flat-file-reader-poc
Proof of Concept for read flat files with fixed-length fields, using Spring Batch and Apache Commons IO. <br/>
The proof was addressed with the file **flat_file_example.CSV**, which has the following structure:

1. Header / Control information (1st line)

|Field|Start Position|End Position|Type|Format|Description|
|---|---|---|---|---|---|
|day|1|10|LocalDate|dd/MM/yyyy|Generation date|
|hour|11|15|LocalTime|HH:mm|Generation hour|
|records|16|17|int||Number of records in payload|

2. Payload (line 2 and following)

|Field|Start Position|End Position|Type|Format|Description|
|---|---|---|---|---|---|
|id|1|2|int||Person ID|
|name|3|17|String||Person name|
|gender|18|18|String|M: male; F: female|Person gender|
|day|19|28|LocalDate|dd/MM/yyyy|Person's birthday|
|hour|29|33|LocalTime|HH:mm|Person's birth time|
|alive|34|34|boolean|1: yes; 0: no|Person was born alive?|

File content snapshot: <br/>

```
08/09/201820:1502
01MARIELA BENITEZF24/04/197822:301
02JAVIER PEREZ   M17/01/196313:210
```
