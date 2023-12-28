# Java Desktop File Organizer

** Currently V1 of this project only works for my local directory structure. This is more of a proof of concept before making this fully dynamic

## Overview
This application serves as an automated file organizer for the desktop. Based on specific criteria like file prefix, it reorganizes files into an established directory structure. It operates within defined hours, provides notifications for exceptions, and offers flexibility through configuration files.

## Command instructions/How to run

### keywords
When you initially run the program, it searches on your desktop and looks for all files that has a predefined string appended in the front of your file name. Each predefined string is mapped to a directory in your file system. You can configure this string to directory path at `src/main/java/org/example/src/constants/KeyWords.java`. When the system notices a file with an appended string, it automatically moves or deletes the directory/file depending on what the appended string is. This of course requires you to manually add the appended string with a following hyphen or you can use the rename functionality of this program to rename that as well.

### delete command
passing `delete` or `-d` and following a file/directory name that you want to delete will purge that file or file/directory
passing `delete all` or `-d all` should delete all files that are on the desktop

### move command
passing `move` or `-m` with a file and a keyword, moves that file to a path based on the keyword

### rename command
passing `rename` or `-r` with a file name that is already on your desktop followed by the new name you want to name it
Example `-r oldFileName newFileName`

### exit
use `exit` in CLI to stop program
     
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

## Continuous Integration & Testing

### Jenkins

This project integrates **Jenkins** for continuous integration and testing. A Jenkins pipeline is set up to automatically activate on every Pull Request (PR). The pipeline ensures:
- **Unit Tests**: Run against the latest code changes to verify their correctness.
