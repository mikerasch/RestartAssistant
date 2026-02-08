# Overview
RestartAssistant is a helper process spawned by a parent process to manage and complete the parent’s restart.
## Required Arguments

The following arguments must be provided when launching RestartAssistant:

- `--pid`  
  The process ID of the parent process to be restarted.

- `--executablePath`  
  The path to the parent process’s executable. (The executable you want to restart)

# Getting Started
This project uses jlink and jpackage in order to get a small native application.
For building run:
```
mvn clean verify jlink:jlink jpackage:jpackage
```
and navigate to target/dist folder.

(If you are unaware of how jpackage works you must run builds on each target OS)
