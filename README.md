# Assignment Scalable Web

This implementation was carried out using Spring Boot. To start the server, execute the main method in the [Application](src/main/java/com/waes/rockfield/waesscalableweb/Application.java) class.

This will start a Java Application Server in the 8080 port by default. From now on, we will assume the service is running through the http://localhost:8080/ address.

## Available Services

Refer to [DiffControllerIntegrationTest](src/test/java/com/waes/rockfield/waesscalableweb/controller/DiffControllerIntegrationTest.java) for more deatails about how to use the REST services.

There are three available services as described below:

### Left side service

It may be reached through a <b>POST</b> request to the following address: http://localhost:8080/v1/diff/{id}/left

Where {id} should be replaced by the identification string to be used to locate the file. Mind that left and right files to be compared need to use the same id.

The REST service requires a [Base64Json](src/main/java/com/waes/rockfield/waesscalableweb/model/Base64Json.java) JSON object, which will contain the Base64 encoded String in the "encoded" property.

The response will be a [ReceivedResponse](src/main/java/com/waes/rockfield/waesscalableweb/model/ReceivedResponse.java) JSON object with two properties: status (OK or ERROR) and message (a more detailed explanation if status is ERROR).

### Right side service

It's the exactly same functionality as the left side service. 

It may be reached through a <b>POST</b> request to the following address: http://localhost:8080/v1/diff/{id}/right

Where {id} should be replaced by the identification string to be used to locate the file. Mind that left and right files to be compared need to use the same id.

The REST service requires a [Base64Json](src/main/java/com/waes/rockfield/waesscalableweb/model/Base64Json.java) JSON object, which will contain the Base64 encoded String in the "encoded" property.

The response will be a [ReceivedResponse](src/main/java/com/waes/rockfield/waesscalableweb/model/ReceivedResponse.java) JSON object with two properties: status (OK or ERROR) and message (a more detailed explanation if status is ERROR).

### Diff service

This services executes the comparison between left and right sides if both files have been correctly set.

It may be reached through a <b>GET</b> request to the following address: http://localhost:8080/v1/diff/{id}

Where {id} should be replaced by the identification string to be used to locate the files. Mind that left and right files to be compared need to use the same id.

The response will be a [DiffResponse](src/main/java/com/waes/rockfield/waesscalableweb/model/DiffResponse.java) JSON object with two properties: status (EQUAL, DIFFERENT or ERROR) and message (with more details to complement the status).
