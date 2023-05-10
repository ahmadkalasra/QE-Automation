# QE-Automation
A maven project for Selenium with latest dependencies. Please follow below procedures to clone, execute or write test cases.

1. Open a terminal window/command prompt
2. Clone this project
3. cd cloned-repo
4. mvn clean verify

All dependencies should now be downloaded

### What should I know?

To run the test suite please user below mvn command:
* mvn clean test -fae -DBrowserType="CHROME"

Yes you can specify which browser to use by using one of the following on the command line:
- `-Dbrowser=chrome` -  (It's implemented)

Following browsers are not yet implemented:

- `-Dbrowser=chrome_headless` -  (It's not yet implemented)
- `-Dbrowser=firefox` -  (It's not yet implemented)
- `-Dbrowser=ie` -  (It's not yet implemented)
- `-Dbrowser=edge` -  (It's not yet implemented)
- `-Dbrowser=opera` -  (It's not yet implemented)
- `-Dbrowser=brave` -  (It's not yet implemented)


If the tests pass/fail Extent Report will be saved in ${project.basedir}/Reports/ExecutionReport.html
