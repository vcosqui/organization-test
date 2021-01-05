Feature: Organization scenarios, these scenarios should cover the basic, most critical functionalities for organization management.

  Scenario: Organization hierarchy should be correctly configured
    Given we have set the following organization hierarchy:
      | employee | supervisor |
      | Pete     | Nick       |
      | Barbara  | Nick       |
      | Nick     | Sophie     |
      | Sophie   | Jonas      |
    When we check the organization hierarchy
    Then organization hierarchy is:
    """
      {"Jonas":[{"Sophie":[{"Nick":[{"Pete":[]},{"Barbara":[]}]}]}]}
    """