The ISS is a Informational Screens System
=========================================

Installation (local)
--------------------

1. Install Tomcat7
1. Install mysql
1. Create database
  * log in to mysql and type:
  
  >	mysql> create database iss;<br/>
  >	mysql> create user iss;<br/>
  >	mysql> grant all on iss.* to 'iss'@'localhost' identified by 'iss123';
1. Deploy ROOT.war on tomcat
  * previously undeploy the ./ application
1. Create folder /local/storage for the pictures
  * change owner of this folder:
  
  >	sudo mkdir -p /local/storage<br/>
	> sudo chown -R tomcat7:tomcat7 /local/storage 
1. Set the folder to be visible from tomcat
  * Update server.xml file (in /etc/tomcat7/)
	* In Host section add:
	
  > &lt;!-- Static path to store ISS local pictures--&gt;<br/>
  > &lt;Context  path="/" aliases="/static=/local/storage/"/&gt;
1. Set the path for the local storage
  * open /admin page
	* add setting with key: content.local.file.dir value: /local/storage
1. Download ROOT.war 
  * Unzip and edit persistence.xml file - update with your data
  * deploy on Tomcat server

Configuration parameters:
-------------------------
* *global.registration.disabled*
  + TRUE/FALSE  
    - decides if registration is enabled or disabled (default FALSE)
* *loging.LDAP.enabled* 
  + TRUE/FALSE 
    - enables/disables LDAP logging (not ready yet)
* *content.local.file.dir*
  + /path/to/dir
    - decides where the pictures loaded from disk will be stored


FAQ
---

1. How to make user an admin?
  * log into DB and find user and it's roleId;
  * execute:
  
  > update Role set roleDescription ='ADMIN' where roleId = 1;

