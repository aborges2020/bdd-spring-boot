Feature: User has age over 18?

  @ListOfString
  Scenario: User has minimum age to logon the form
    Given The form is available and I inform my name and age
      |name    |age|
      |Andre   |28|
      |Matheus |38|
    When User try to submit the form with just name
      |name    |age|
      |Andre   |18|
      |Ingrid  |28|
      |Louis   |300|
    Then User informs invalid name and age
      |name    |age|
      |Andre2   |1000A|
      |Ingrid2  |-1|
      |0 |Matheus2|
      |Louis2   |2A|