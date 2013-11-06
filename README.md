SERBER
======

**SERBER** (SErvicio de Registro de Bibliotecas y Entidades Relacionadas, Registry Service of Libraries and Related Organizations) is a system that faithfully implements the abstract model of ISO 2146:2010, with its five entities, the main class registry object, and the subclasses Parties (persons and organizations), Activities, Collections, and Services.  This system aims to be a general-purpose tool.  It should not be limited to the library sector as it could be used to build a registry service for any other community.

**SERBER** provides an API of RESTful web services (JSON and XML serializations) for querying, reading, adding and updating the registry data and sharing with third parties.

**SERBER** has been built with an open approach in mind, i.e., using an architecture focused on reuse and open source libraries, models and applications.



Development Environment & Associated Technologies
-------------------------------------------------

The development environment selected for this project is Play Framework ([http://www.playframework.com](http://www.playframework.com)): A modern and advanced Java/Scala stack to build web applications.  Play provides the MVC architectural pattern, RESTful framework, JSON/XML parsers and marshallers, persistence layer based on JPA, JavaScript, LESS and many other modern and well accepted web technologies.

The pattern selected for the controller is a VM (ViewModel).  This pattern strengthens the decoupling between the interface and the data let alone the development environment.  At the moment, SERBER implements such pattern by means of Knockout and AJAX.

To access an SQL database, Play provides a plugin for managing JDBC connection pools as well as the Ebean ORM for mapping to the database.  These can be configured at `serber/` directory in `conf/application.conf` file.  At the moment, SERBER is configured to use H2 in memory database as its default RDBMS.

The SERBER project is divided in two sub-projects, a web application *serving* web services ([serber-project/serber](http://github.com/serber-project/serber)) by offering them and a web application *interfacing* web services ([serber-project/serber-interface](http://github.com/serber-project/serber-interface)) by reading, searching, navigating and viewing data, i.e., a demo site that uses those web services.  Both repositories are needed.

To compile and run these sub-projects, it is mandatory to install Play which can be downloaded from its web site ([http://www.playframework.com/download](http://www.playframework.com/download)).  Currently, SERBER uses Play version 2.2.0: [http://downloads.typesafe.com/play/2.2.0/play-2.2.0.zip](http://downloads.typesafe.com/play/2.2.0/play-2.2.0.zip).

Prerequisites: JDK 6 or later.

Installing Play Framework: [http://www.playframework.com/documentation/2.2.0/Installing](http://www.playframework.com/documentation/2.2.0/Installing)



Install
-------

Wanting to get SERBER up and running? Please, take a look at `INSTALL.md`.



Contributing
------------

Wanting to participate in SERBER development? Please, take a look at `CONTRIBUTING.md`.



Authors
-------

**Carlos Ramírez**

-   [http://github.com/CarlosRamirezRamos](http://github.com/CarlosRamirezRamos)

**Pablo Ripollés**

-   [http://github.com/protolab-org](http://github.com/protolab-org)



Copyright and License
---------------------

Copyright 2013 Spanish Ministry of Education, Culture and Sport

Licensed under the EUPL, Version 1.1 or – as soon they will be approved by the European Commission - subsequent versions of the EUPL (the "Licence");
You may not use this work except in compliance with the License.
You may obtain a copy of the License at:

[http://ec.europa.eu/idabc/servlets/Docbb6d.pdf?id=31979](http://ec.europa.eu/idabc/servlets/Docbb6d.pdf?id=31979)

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" basis,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and limitations under the License.
