Feature: View attending and organizing events
  As user,
  I want to be able to sort the events on my home page into events I am attending and events I am organizing,
  so that I can manage my events

  Background:
    Given the following user exists in the system
      | username | password | firstname | lastname | birthday   | email                  |
      | normCul  | nv1234   | Norma     | Culver   | 1975-12-10 | norma.culver@gmail.com |
      | alstyles | al1234   | Alice     | Styles   | 1977-10-05 | alice.styles@gmail.com |
      | robkell  | rb1234   | Robin     | Kelly    | 1980-04-01 | robin.kelly@gmail.com  |
    Given the following events exist in the system
      | eventDate  | isPrivate | isVirtual | location           | description     | image       | organizers | attendees |
      | 2022-09-15 | True      | False     | 872 Rue du Couvent | Wedding         | wedding.png | normCul    | alstyles  |
      | 2022-11-15 | False     | False     | 3715 Av. Lacombe   | Pottery Lessons | pots.png    | robkell    | normCul   |
      | 2022-10-22 | False     | True      | Zoom               | DnD Game        | game.png    | robkell    | normCul   |
      | 2022-06-30 | False     | False     | 720 Rue Woodland   | Crochet Club    | yarn.png    | alstyles   | normCul   |

  Scenario: View attending events
    Given that "normCul" is logged in
    When the user selects to view their attending events
    Then the following events should be displayed
      | eventDate  | isPrivate | isVirtual | location         | description     | image    | organizers | attendees |
      | 2022-11-15 | False     | False     | 3715 Av. Lacombe | Pottery Lessons | pots.png | robkell    | normCul   |
      | 2022-10-22 | False     | True      | Zoom             | DnD Game        | game.png | robkell    | normCul   |
      | 2022-06-30 | False     | False     | 720 Rue Woodland | Crochet Club    | yarn.png | alstyles   | normCul   |

  Scenario: View organizing events
    Given that "robkell" is logged in
    When the user selects to view their organizing events
    Then the following events should be displayed
      | eventDate  | isPrivate | isVirtual | location         | description     | image    | organizers | attendees |
      | 2022-11-15 | False     | False     | 3715 Av. Lacombe | Pottery Lessons | pots.png | robkell    | normCul   |
      | 2022-10-22 | False     | True      | Zoom             | DnD Game        | game.png | robkell    | normCul   |

  Scenario: View all events
    Given that "normCul" is logged in
    Then the following events should be displayed
      | eventDate  | isPrivate | isVirtual | location           | description     | image       | organizers | attendees |
      | 2022-09-15 | True      | False     | 872 Rue du Couvent | Wedding         | wedding.png | normCul    | alstyles  |
      | 2022-11-15 | False     | False     | 3715 Av. Lacombe   | Pottery Lessons | pots.png    | robkell    | normCul   |
      | 2022-10-22 | False     | True      | Zoom               | DnD Game        | game.png    | robkell    | normCul   |
      | 2022-06-30 | False     | False     | 720 Rue Woodland   | Crochet Club    | yarn.png    | alstyles   | normCul   |
