# RMI Registration System

A simple **Java RMI-based Registration System** that allows clients to register users through a GUI while a remote server handles and stores the registration data.

## Features

-  Java Swing-based client interface.
-  RMI server for handling registration requests.
-  Can run client and server on **different computers** within the same network.
-  Demonstrates Java RMI concepts and distributed computing.

## Project Structure

```
RMI-Registration-System/
│
├── client/          # Client-side GUI application
├── server/          # Server-side RMI application
├── shared/          # Shared interfaces on both server and client
├── lib/             # External libraries (e.g., MySQL connector) for server
└── README.md
```

## Prerequisites

-  **Java JDK 8 or higher** installed on both client and server computers.
-  **Network access** between the client and server (both should be on the same local network).
-  MySQL connector in `lib` folder if using a database (optional if using in-memory storage).

---

## Step 1: Compile and run the Project on **server machines**:

`Compile`

```bash
javac server/*.java shared/*.java
```

`Run`

```bash
java -cp "lib/*;." server.RegistrationServer
```

---

## Step 2: Compile and run the Project on **client machines**:

`Compile`

```bash
javac client/*.java shared/*.java
```

`Run`

```bash
java  client.RegistrationClient
```

-  and pass the **server's IP address** when prompted.

## Step 3: Notes for Network Setup

-  Both client and server must be on the **same local network**.
-  Ensure **firewall allows communication on port 1099**.
-  To change the default RMI port, modify the server code:

---

## Step 4: Troubleshooting

| Error                        | Solution                                                                          |
| ---------------------------- | --------------------------------------------------------------------------------- |
| `java.rmi.ConnectException`  | Check that the server IP and port are correct and accessible from the client.     |
| `java.rmi.NotBoundException` | Make sure the server has bound the service to the expected name.                  |
| Client cannot connect        | Check firewall and network settings. Ensure both machines are in the same subnet. |

---

## License

MIT License

---
