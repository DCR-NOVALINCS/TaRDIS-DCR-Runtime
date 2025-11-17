# TaRDIS/DCR Runtime

A [Babel](https://codelab.fct.unl.pt/di/research/tardis/wp6)-based runtime platform for deploying decentralized [DCR](https://codelab.fct.unl.pt/di/research/tardis/wp3/TaRDIS-DCR-Compiler)-driven endpoints. 



## Table of Contents
- [Quick Start](#quick-start)
  - [Project Requirements](#project-requirements)
  - [Installation](#installation)
  - [Endpoint Deployment](#endpoint-deployment)
- [Configuration](#configuration)
- [Interacting with Endpoints](#interacting-with-endpoints)

[//]: # (- [Troubleshooting]&#40;#troubleshooting&#41;)

## Quick Start

### Project Requirements
- **Java**: JDK version 21+
- **Maven**: Apache Maven 3.6.11+
- **Docker** (optional)

Start by ensuring you already have `Java` and `Maven` installed on your machine by running
the following commands:
```sh
java --version
mvn --version
```
If these trigger errors, you might have to install these dependencies. Additionally, ensure 
that Maven correctly detects your JDK installation.

### Installation
This project comes with a DCR-based specification for two types of endpoints 
(*Community Orchestrators*, and *Prosumers*), which can be used to quickly demo its
usage.

Start by cloning the project

```sh
git clone https://codelab.fct.unl.pt/di/research/tardis/wp3/TaRDIS-DCR-Runtime
```

From its root directory, build the project

```sh
cd ./TaRDIS-DCR-Runtime
mvn clean package
```

### Endpoint Deployment
You can test the installation a pre-defined specification supporting two types of endpoint: *Community 
Orchestrator* and *Prosumer*. 

You might want to start by deployin a single endpoint locally first, for instance, 
a *Prosumer*:

```sh
java -jar target/babel-backend.jar interface=eth0 role=P id=1 cid=1
```
(you may need to define a different `interface`, according to the network interfaces
available in your system - *e.g.*, `wlan0` or `en0`).

Finally, check that the endpoint is running locally on port 8080:  
```sh
curl --location 'localhost:8080/rest/dcr'
```

Alternatively, you can also deploy multiple endpoints locally. A straightforward approach
is to leverage `Docker` containers and the `docker-compose.yml` already made available for 
demo purposes.

Assuming `Docker` is already installed, run the following command from the root directory
of the project:
```
mvn clean package && docker compose up --build -d
```

This will launch local endpoints, following the services defined in the
`docker-compose.yml`.

Note that each container maps its port `8080` to a distinct 
host port. For instance, the *Community Orchestrator* of community `1` is mapped to port
 `1241`, which can be checked by running:
```sh
curl --location 'localhost:1241/rest/dcr'
```

To stop the containers and cleanup intermediate images, run the following command:
```
docker compose down && docker image prune
```

## Configuration

### Endpoint Specification

An endpoint specification defines the (parameterizable) behaviour associated to a specific 
swarm participant, based on its **role**. Endpoint specifications must be
placed under `src/main/resources/protocols/application`.

Endpoint specifications are encoded within a single `.json` file, following the schema found in
`src/main/resources/protocols/dcr-schema.json`. 

The suggested (and expected) approach to 
generate such specifications is through the companion 
[TaRDIS/DCR Compiler](https://codelab.fct.unl.pt/di/research/tardis/wp3/TaRDIS-DCR-Compiler) tool,
 which automatically derives such specification off a (global) specification:
a *DCR Choreography*.

The project currently contains specifications for two parameterizable roles, based on 
EDP's *Energy Communities* use case (see `resources/protocols/application/choreo.json`):
 - `P`: a **P**rosumer role expecting **id** and **cid** parameters
   - `P(id:String; cid:Integer)`
  - `CO`: a **C**ommunity *O*rchestrator expecting a **cid** parameter
    - `CO(cid:String)`

### Babel

Endpoint deployment depends on the Babel framework. Babel-related configurations can be
found under `resources/config.properties`. These properties can be overridden upon 
launching the node, as already exemplified for the `interface=eth0` property.

## Interacting with endpoints.
The most straightforward way to visualize endpoints and trigger events is by using the
[TaRDIS/DCR Dashboard](https://codelab.fct.unl.pt/di/research/tardis/wp3/TaRDIS-DCR-Dashboard) 
tool.


[//]: # (## Troubleshooting)
[//]: # (ensure that Maven correctly detects your JDK installation)
 