# Player Communication Project

This project demonstrates communication between two players using both single-process and inter-process communication methods. The players can either communicate within the same JVM or as separate processes.

## Prerequisites

- Java 11 or higher
- Maven
## Project Structure
        PlayerCommunication/
        ├── pom.xml
        ├── src/
        │   ├── main/
        │   │   ├── java/
        │   │   │   └── org/
        │   │   │       └── threesixtyT/
        │   │   │           ├── ClientPlayer.java
        │   │   │           ├── Player.java
        │   │   │           ├── ServerPlayer.java
        │   │   │           ├── SingleProcessMain.java
        │   │   ├── resources/
        │   │       └── scripts/
        │   │           ├── start_client.sh
        │   │           ├── start_server.sh
        │   │           └── start_single_process.sh
        └── README.md


## File Descriptions

- **ClientPlayer.java:** Defines the client player which connects to the server and communicates.
- **ServerPlayer.java:** Defines the server player which listens for connections and communicates with the client.
- **Player.java:** Defines a player that can communicate with another player within the same JVM.
- **SingleProcessMain.java:** Contains the main method to run both server and client within a single process.
- **start_server.sh:** Shell script to start the server process.
- **start_client.sh:** Shell script to start the client process.
- **start_single_process.sh:** Shell script to start both server and client in a single process.

## How to Run:
1.  Make sure you are in root folder of the project
2.  Ensure that **start_server.sh** and **start_client.sh** have execute permissions. If not, run:
      - ```chmod +x <full-path>/start_server.sh```
      - ```chmod +x <full-path>/start_client.sh```
      - ```chmod +x <full-path>/start_single_process.sh```
2. Running Server and Client in a Single Process:
      - To run both server and client within a single JVM, use the start_single_process.sh script:
        ```<full-path>/start_single_process.sh```
3. Running the Server and Client as Separate Processes:
      - Run the **start_server.sh** script to start the server:
        ```<full-path>/start_server.sh```
      - Run the **start_client.sh** script to start the client:
         ```<full-path>/start_client.sh```

 

 


