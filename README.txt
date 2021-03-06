This application shows how one could implement observer pattern at remote level

The project consists of one maven root project and 4 modules : *core, *server, *server-test, *ui
*core : the core (services + domain)
*server : webapp which exposes some webservices with CXF and ActiveMQ
*server-test : tests webservices and remote notifications
*ui : triggers changes and observes changes notifications

To build the example
 - cd *root, mvn clean install

To run integration tests
 - cd *root, mvn clean install -Pserver-tests

To visually run the example
 - run webapp : cd *server, mvn clean install jetty:run-exploded
 - run ui : cd *ui, mvn clean install exec:java -Dexec.mainClass="org.diveintojee.poc.remote.observer.pattern.ApplicationLauncher"
 - launch entity creation menu
 - create entity
 - note the message on the status bar : it should display the entity just created, the status bar should change color



