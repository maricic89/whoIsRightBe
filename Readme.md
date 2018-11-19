Who is right APPLICATION

1.You need to have maven to build project. 2.Java SDK 1.8

Installation of initial mysql database on docker image (for linux). ----base installation START------ 1.You need to install docker locally on your machine. 2.After docker installation you need to pull docker image of mysql server use

docker pull mysql/mysql-server:tag
(where tag is version of mysql server) 3.Now we have docker image so we need to use it with command

docker run --name=wir -d mysql/mysql-server:5.7 (creates docker image)
(this command create local container with name wir in our docker virtual machine) (5.7 is current latest stable version of mysql-server) (Useful docker command for beginners is docker ps -a) (This command list all already available -or previous installed- docker containers) ----base installation END------

Now we need to find current root password

docker logs wir
(Search log and find initial password for root user) After that we need to access mysql command line with root user

docker exec -it wir mysql -uroot -p
(wir- name of current container) Paste there you initial root password to access mysql command line When you are in that console you need to commit some changes on current scheme or database

ALTER USER 'root'@'localhost' IDENTIFIED BY 'wir';
(You need to use ; at the end, and this command is changing initial password)

CREATE USER 'wir'@'%' IDENTIFIED BY 'wir';
(This command crate user wir)

GRANT ALL PRIVILEGES ON . to 'wir'@'%';
(This command gives all privileges on wir user)

CRATE DATABASE wir;
(This command creates wir database)

Now we have user (wir) with empty password and database (wir) Now if you are using linux just use

docker logs wir
And find you address for local docker machine my is (host) 172.17.0.2 and default port is 3306 for mysql.