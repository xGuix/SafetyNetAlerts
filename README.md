# SafetyNetAlerts
**APIRest for emergencies services**

This app uses jackson to read and stores the data from Json to memory.
No DataBase includes.


##Provide Alerts system for Firestation</br>
**Aim to help emergency services save lives.**

SafetyNetAlert can provide:
- Persons location at specific address during fire
- Phone and addresses of citizen to alert on emergency
- Medical record files to any citizen 


## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.</br>
See deployment for notes on how to deploy the project on a live system.


### App built with
What things you need to install this App

- Java 1.8
- SpringBoot 2.5.6
- Maven 3.0.0
- Jackson 2.12.5
- JUnit 5
- Jacoco 0.8.5
- Log4j 2.14.1


### Installing
A step by step series of examples that tell you how to get a development env running:

1.Install Java:
https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html

2.Install Maven:
https://maven.apache.org/install.html

3.Install SpringBoot:
https://spring.io/projects/spring-boot

After downloading and installing it, no specific setup required.


### App Data
You will have to set up your own data with Json File `data.json` under the `resources` folder in the code base.
If you need to implement full set of data just add into the Json file.


### Running App
Finally, you will be ready to import the code into an IDE and launch the application.

You can execute the `main` method in the `com.safetynet.alert.SafetyNetAlertsApplication`

Otherwise use [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html)

```shell
mvn spring-boot:run
```


### Testing
The existing tests need to be triggered from maven-surefire plugin while we try to generate the final executable jar file.
Run the tests from maven, go to the folder that contains the pom.xml file and execute the below command.

* Run unit tests, use command:

```shell
mvn:test
```

* Run integration tests, use command:

```shell
mvn failsafe:integration-test
```

* Run all tests, use command:

```shell
mvn verify
```


## Reports
Maven site to get all reports:

- **SureFire Report** for all unit Tests.
- **Jacoco Report** for tests coverage.
- **SpotBugs Report** for find bugs. 

Run build site, use command:

```shell
mvn site
```

Access file directory : `target/site` 
Run the `index.html` in your web browser.


## Jacoco Coverage
Jacoco coverage is automatically done with tests.

Access file directory : `target/site/jacoco/index.html`


## APIRest Access

Start access URL:

```html
https://localhost:8080/
```


## APIRest controllers

- **Person:** Manage endpoints to entitie "Person"
- **Firestation:** Manage endpoints to entitie "Firestation"
- **MedicalRecord:** Manage endpoints to entitie "MedicalRecord"
- **Alerts:** Manage all endpoints relative to URLS


## Endpoints /person

- **GET{persons}**
Get list of all persons in files

```html
localhost:8080/persons
```

- **GET{person}**
Get person with firstName and lastName

```html
localhost:8080/person?firstName=<firstName>&lastName=<lastName>
```

- **POST{person}**
Add person with full body "Person"

```html
localhost:8080/person
```

- **PUT{person}**
Update person with full body "Person"

```html
localhost:8080/person
```

- **DEL{person}**
Delete person with firstName and lastName

```html
localhost:8080/person?firstName=<firstName>&lastName=<lastName>
```


## Endpoints /firestation

- **GET{firestations}**
Get list of all firestations in files

```html
localhost:8080/firestations
```

- **GET{firestation}**
Get one firestation with address and station

```html
localhost:8080/firestation?address=<address>&station=<station>
```

- **GET{firestationsStation}**
Get firestations list for station number

```html
localhost:8080/stationAddresses?station=<station>
```

- **GET{stationAddresses}**
Get firestation addresses with station number

```html
localhost:8080/stationAddresses?station=<station>
```

- **POST{firestation}**
Add firestation with full body "firestation"

```html
localhost:8080/firestation
```

- **PUT{firestation}**
Update firestation with address and station for check matches.</br>
The full body of the new "firestation" to update.

```html
localhost:8080/firestation
```

- **DEL{firestation}**
Delete firestation with address and station

```html
localhost:8080/firestation?address=<address>&station=<station>
```


## Endpoints /medicalRecord

- **GET{medicalRecords}**
Get list of all medicalRecord in files

```html
localhost:8080/medicalRecords
```

- **GET{medicalRecord}**
Get medicalRecord with firstName and lastName

```html
localhost:8080/medicalRecord?firstName=<firstName>&lastName=<lastName>
```

- **POST{medicalRecord}**
Add medicalRecord with full body "medicalRecord"

```html
localhost:8080/medicalRecord
```

- **PUT{medicalRecord}**
Update medicalRecord with firstName and lastName for check matches.</br>
The full body of the new "medicalRecord" to update.

```html
localhost:8080/medicalRecord?firstName=<firstName>&lastName=<lastName>
```

- **DEL{medicalRecord}**
Delete medicalRecord with firstName and lastName

```html
localhost:8080/medicalRecord?firstName=<firstName>&lastName=<lastName>
```


## URLS Alerts

- **GET{firestationPersonAlert}**
Get list of all Persons at station addresses.</br>
Give the count of children and adults

```html
localhost:8080/firestationPersonAlert?station=<station>
```

- **GET{childAlert}**
Get list of all children at address.</br>
Each child contains a list with family adults

```html
localhost:8080/childAlert?address=<address>
```

- **GET{phoneAlert}**
Get list of all phone number at addresses station.</br>
Give for each person the name follow by phone number

```html
localhost:8080/phoneAlert?station=<station>
```

- **GET{fireAlert}**
Get list of all Persons living at address and the firestation in charge.</br>
Give for each person :</br>
name, phone, age and medicals records : medication and allergie

```html
localhost:8080/fireAlert?address=<address>
```

- **GET{floodAlert}**
Get list of all family home at addresses station.</br>
Give for each family home list, the persons details :</br>
name, phone, age and medicals records: medication and allergie

```html
localhost:8080/floodAlert?station=<station>
```

- **GET{personInfo}**
Geta full info of a person with firstName and lastName.</br>
Give a person and its medical record : medication and allergie

```html
localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>
```

- **GET{communityEmail}**
Get list of all emails at city.</br>
Give for each person the name follow by phone number

```html
localhost:8080/communityEmail?city=<city>
```