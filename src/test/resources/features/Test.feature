Feature: Fake Test

  Background:
    Given set up test

  Scenario: Two plus two equal 9
    Given we have "2" and "2"
    When we add them together
    Then it should equal "10", hopefully