sudo apt-get update
#sudo apt-get dist-upgrade
sudo add-apt-repository ppa:multiverse
sudo apt-get install sun-java6-jre sun-java6-jdk
sudo apt-get install mysql-server-5.1
# Password del usuario root de MySQL
echo "Ingrese el password del usuario root de mysql"
read mysql_root_password
# Parametros que pueden ser modificados
# Drive donde sera instalado simon
export dest_drive=/usr/local
# Puerto de MySQL
export mysql_port=3306
# Nombre de la base a crear
export base=SIMON
# Nombre del usuario a crear
export dbUser=SIMON_USER
# Password del usuario a crear
export dbPassword=SIMON_PASSWORD
# Nombre del pais host
export countryHost=Argentina
# Password del usuario Admin
export adminPassword=Admin
# Subpath temporal
export subpath=simon
sudo mkdir $dest_drive/simon
sudo mkdir $dest_drive/simon/apache-tomcat-6.0.32
sudo cp -r ../common/apache-tomcat-6.0.32/* /usr/local/simon/apache-tomcat-6.0.32
sudo chmod +x /usr/local/simon/apache-tomcat-6.0.32/bin/*.sh
sudo java -jar ../common/simon_install.jar installationPath=$dest_drive server=localhost port=$mysql_port rootPassword=$mysql_root_password base=$base user=$dbUser password=$dbPassword countryHost=$countryHost adminPassword=$adminPassword subpath=$subpath
