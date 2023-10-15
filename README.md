# Java Desktop File Organizer

## Overview
This application serves as an automated file organizer for the desktop. Based on specific criteria like file prefix, it reorganizes files into an established directory structure. It operates within defined hours, provides notifications for exceptions, and offers flexibility through configuration files.

## Features

### 1. Desktop Monitoring
- Continuously monitor the desktop for any changes to file names every 5 minutes.

### 2. File Movement Based on Prefix
- On detecting a filename change, the program checks its prefix.
- Uses a JSON configuration file to determine the correct destination based on the prefix.

### 3. JSON Configuration
- Use a JSON file to represent and configure the directory structure.
- The structure reflects the directory hierarchy, including nested directories.

### 4. Exception Handling
- Handles anomalies, such as an unrecognized prefix.
- Sends native OS notifications in exception cases.

### 5. Operating Hours
- Only operates on weekdays from 9 AM to 5 PM.

### 6. Logging and History
- Logs activities, file moves, exceptions/errors, and notifications.

### 7. Graceful Shutdown
- Allows users to shut down the application without interrupting ongoing operations.

### 8. Documentation
- Includes instructions for setup, configuration modification, and expected naming criteria.

### 9. Performance and Efficiency
- Designed for minimal system resource consumption.

### 10. Validation and Error-checking
- Validates the JSON configuration against expected patterns.

### 11. Configuration Flexibility (Bonus)
- Provides multiple methods to adjust settings, such as operating hours.

## Libraries & Tools

### Build System

#### Maven
- A powerful build tool for Java projects.
- [Apache Maven](https://maven.apache.org/)

### Testing

#### JUnit 5
- Standard testing framework for Java.
- [JUnit 5](https://junit.org/junit5/)

#### Mockito
- Mocking framework that integrates with JUnit.
- [Mockito](https://site.mockito.org/)

### Libraries

#### Jackson
- High-performance JSON processor for Java.
- [Jackson](https://github.com/FasterXML/jackson)

#### Apache Commons IO
- Utilities for IO operations and file manipulations.
- [Apache Commons IO](https://commons.apache.org/proper/commons-io/)

#### Log4j
- Reliable logging library for Java.
- [Log4j](https://logging.apache.org/log4j/2.x/)

#### Desktop (Java built-in)
- Allows interactions with the host environment.
- [Java Desktop API](https://docs.oracle.com/javase/8/docs/api/java/awt/Desktop.html)
