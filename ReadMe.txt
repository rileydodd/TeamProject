Steps for installing and running game

--Starting Database
--Only the host/server will need to have the database started
1. First start by opening the xampp controler from your specified path
2. Once opeing the xampp control, start mysql
3. Now open a command prompt and start xampp from your specified path
	1. cd \xampp\mysql\bin
	2. logon to xampp
		1. \xampp\mysql\bin>mysql -h localhost -u student -p
		2. type in your password. Example: hello
	3. Once loged onto xampp, change you database to student_space
		1. use student_space;
	4. Now load the database file in from you specified path
		1. Example: source c:\Temp\Database.sql
		2. just wherever you have it saved

--Ping to get IPV4 addresses
1. open up a command prompt on two different computers to find IPV4 addresses
2. in the command prompt do a ipconfig
3. after doing this find your IPV4 address while the other person finds theirs
4. make sure your firewall is off
	1. open up control panel
	2. click on system and security
	3. next click on Windows Defender Firewall
	4. then click on turn Windows Defender Firewall on and off
	5. from here turn off the firewall
	6. go back to your command prompt
5. next ping the person you are trying to connect with to make sure a connection can be made
	1. ping 127.0.0.1, for example
6. once establishing that a connection is made, decide who will be server and who will be client
7. once deciding who will be server, the client will go into the code to the CheckersGUI class and enter in the IP address of server person
	Example:
	1. client.setHost("10.252.161.60");
8. once this is done you are now ready to run the game

--Starting program
1. load the files into papyrus by dragging and dropping the project over.
2. Next open up a command prompt and get to the specified path you have saved the project under. 
	Example on my computer: 
	1. cd sw*S2024
	2. \sw_TempS2024>cd TeamProject
	3. \sw_TempS2024\TeamProject>dir
	4. find the bat files under the name of TeamProjectServer.bat and TeamProjectClient.bat
3. After successfully finding the .bat files, the host will need to run the Server bat file first
	Example:
	1. \sw_TempS2024\TeamProject>TeamProjectServer.bat
	2. this will start the server
4. next the clients will run the TeamProjectClient.bat file to start the client side
	Example:
	1. \sw_TempS2024\TeamProject>TeamProjectClient.bat
	2. client side will be started now and hooked up to the server
5. Once host and clients are started, clients can now create an account
	1. Clients will have the option to login or create an accound
	2. If no previous account select create account
	3. once selecting this enter in your username and password and your repassword
	4. clicking submit will take you into the game and hook you up with another client
6. Once two clients are logged in they will enter a game
	1. The clients can now play a checkers game
7. At anytime during the game a client can logout and leave the game.
	1. Either while playing or after a game has been played
	2. A client can logout at anytime