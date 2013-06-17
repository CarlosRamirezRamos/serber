Install SERBER
--------------

The SERBER project is divided in two sub-projects, a web application *serving* web services by offering them and a web application *interfacing* web services by reading, searching, navigating and viewing data, i.e., a demo site that uses those web services.  Both repositories are needed.

Once the repositories have been cloned to local, we need to accomplish the following steps:

1.  Entering the Play console for **SERBER web application services**
	-   Being at `serber/` directory, enter the Play console from the command prompt:

			$ play

2.  Accessing an SQL database [only if necessary]
	-   When using H2 database, the *H2 Console* should be started before running SERBER web application:

			[serber] $ h2-browser
	-   The *H2 Console* is started on localhost's port 8082: `http://localhost:8082`
		+   The connection configuration should match the one reflected in `conf/application.conf` file, which by default is:

				Driver Class: org.h2.Driver
				JDBC URL: jdbc:h2:mem:play
				User Name:
				Password:
		+   In case the `default` connection pool configured in `conf/application.conf` file has been changed, it should be reflected in the connection configuration above.
	-   Once the connection has been established, the database can be accessed, though it will be empty, the database still needs to be populated.

3.  Running **SERBER web application services** on the server
	-   From the Play console, execute the SERBER web application:

			[serber]$ run
	-   A web server is started on localhost's port 9000.

4.  Querying **SERBER web application services** from the client
	-   From the browser address bar, access the SERBER web application:

			http://localhost:9000
		+   A message stating `Database 'default' needs evolution!` will be display.  The appropriate script will be generated in `/conf/evolutions/default/1.sql` file.
		+   This script must be executed on the database engine.
		+   This script can be executed by simply clicking `Apply this script now!` button.
		+   If H2 database and default JDBC data source `default` is used, the newly created database schema can be viewed on the *H2 Console*.
	-   A *static* web about the SERBER project is served.  The static page can be displayed, though there is no navigation, the SERBER web application is just *serving* web services.

5.  Entering the Play console for **SERBER web application interfaces**
	-   Being at `serber-interface/` directory, enter the Play console from the command prompt:

			$ play

6.  Running **SERBER web application interfaces** on the server
	-   From the Play console, execute the SERBER web application:

			[serber-interface] $ run 9001
	-   A web server is started on localhost's port 9001.

7.  Querying **SERBER web application interfaces** from the client
	-   From the browser address bar, access the SERBER web application:

			http://localhost:9001
	-   A *dynamic* web about the SERBER project is served.  The dynamic page can be displayed and navigated, the SERBER web application is just *interfacing* web services.

8.  Populating the Database
	-   To populate the database an SQL script needs to be executed.  A sample data SQL script is supplied at `serber/` directory in `conf/script.sql` file.
	-   If H2 database and default JDBC data source `default` is used, the SQL script can be executed on the *H2 Console*.



### Editors, Terminals & IDEs ###

To edit, compile and run the system any text editor and terminal is more than enough, however, Play allows to generate a working Eclipse project derived from any working Play application.
