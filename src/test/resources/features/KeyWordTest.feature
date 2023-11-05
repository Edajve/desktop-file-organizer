Feature: Keyword functionality

  Background:
    Given There is a desktop folder named "test_directory"

  Scenario: Delete file with the "trash-" appended
    Given there is a file called "trash-test-file-one"
    When the program is ran
    Then the file "trash-test-file-one" should be deleted
#    Then clean up files
    Then shut down program