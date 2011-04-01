drop table DEPLOYED_SCRIPTS;
delete from SYSPROPERTIES where propKey IN ('simon.mailserver', 'simon.mayorVersion', 'simon.minorVersion');
delete from RESOURCEBUNDLE where rbKey IN ('servidorDeEmail');
delete from RESOURCEBUNDLE where rbContext = 'botones';
commit;
