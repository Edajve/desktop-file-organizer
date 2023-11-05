Feature: Keyword functionality

  Background:
    Given There is a desktop folder named "test_directory"
    And two files named "test_file_one" and "test_file_two"

  Scenario: Two plus two equal 9
    Given we have "2" and "2"
    When we add them together
    Then it should equal "9", hopefully

  Scenario: Clean up
    When all files are deleted in above tests
    Then there should be no files or directectories left