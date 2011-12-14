REM Parametros que pueden ser modificados
REM Drive donde sera instalado simon
set dest_drive=C
REM Password del usuario root de MySQL
set mysql_root_password=root
REM Puerto de MySQL
set mysql_port=3306
REM Nombre de la base a crear
set base=SIMON
REM Nombre del usuario a crear
set dbUser=SIMON_USER
REM Password del usuario a crear
set dbPassword=SIMON_PASSWORD
REM Nombre del pais host
set countryHost=Argentina
REM Password del usuario Admin
set adminPassword=Admin
REM Subpath temporal
set subpath=simon
ECHO Instalando java 1.6
jdk-6u21-windows-i586.exe /s INSTALLDIR=\"%dest_drive%:\simon\jdk1.6.21\"
ECHO Instalando MySQL
msiexec /i mysql-5.5.9-win32.msi INSTALLDIR=%dest_drive%:\simon\MySQL5.5.9 /quiet /L* %dest_drive%:\simon\mysql_install_log.txt
ECHO Creando instancia MySQL
%dest_drive%:\simon\MySQL5.5.9\bin\MySQLInstanceConfig.exe -i -q "-l%dest_drive%:\simon\mysql_config_log.txt" "-nMySQL" "-p%dest_drive%:\simon\MySQL5.5.9" -v5.5.9 "-t%dest_drive%:\simon\MySQL5.5.9\my-template.ini" "-c%dest_drive%:\simon\MySQL5.5.9\my.ini" ServerType=SERVER DatabaseType=MIXED ConnectionUsage=OLTP Port=%mysql_port% ServiceName=MySQL RootPassword=%mysql_root_password%
ECHO Instalando Tomcat 6
mkdir %dest_drive%:\simon\apache-tomcat-6.0.32
xcopy "..\common\apache-tomcat-6.0.32\*.*" "%dest_drive%:\simon\apache-tomcat-6.0.32" /e /Q
java -jar ../common/simon_install.jar installationPath=%dest_drive%: server=localhost port=%mysql_port% rootPassword=%mysql_root_password% base=%base% user=%dbUser% password=%dbPassword% countryHost=%countryHost% adminPassword=%adminPassword% subpath=%subpath%

