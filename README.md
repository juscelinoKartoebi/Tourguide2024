Opdracht 1:
Vervang de leeftijd met een geboorte datum. Wat zijn de voordelen daarvan?

Opdracht 2:
Voeg een adres toe aan de Customer. Dit moet een reference zijn naar een Adres Entity. Bedenk zelf de velden

Opdracht 3:
Maak een de volgende entiteiten. Bedenk zelf de velden en relaties.
    - Produkt
    - OrderSummary
    - OrderLine
Schrijf een integratie test in een class OrderSummaryDaoIntegrationTest die verifieerd
of de opgeslagen order datum voor een order correct wordt opgehaald uit de database.

------------------------
Opdracht 4:
###################
### Validations ###
###################
Open de unit test in com.unasat.shop.ProductValidatorImplTest.
De unit test bevat de twee validaties op productcode veld van product.
- Productcode is een verplicht veld
- Een productcode mag minimaal 3 en maximaal 5 characters bevatten.
Draai steeds de unit tests op class niveau (alle tests zonder @ignore) in volgorde en laat ze één voor één slagen.
Zodrag de eerste test slaagt, ga naar test2 en verwijder de @Ignore en laat die ook slagen en ga zo door naar de volgende.

Schrijf op dezelfde/soortgelijke wijze een nieuwe unit test class voor de volgende validaties van Customer (Customer Validator en unit test)
- Een Customer mag niet jonger dan 18 jaar zijn
- Een Customer mag maximaal 5 van hetzelfde product bestellen per keer.

#####################
### Transactions  ###
#####################
- save een lijst met objects waarvan 1 een validatie error heeft
 (zonder cascade)