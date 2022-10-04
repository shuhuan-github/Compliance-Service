# siemens
Compliance Service

## export database
```pg_dump.exe -h localhost -U <user name> <database name>><路径>/pg.sql```

<img src="https://user-images.githubusercontent.com/106481991/173724955-9d5c68ee-ceeb-4807-828d-67d4bbf8a7d3.png" width="700px" />

## import database
```psql -U <user name> <database name> < <路径>/pg.sql```

<img src="https://user-images.githubusercontent.com/106481991/177508344-5e25381c-bb84-47fa-a29f-79b38e75aa66.png" width="700px" />


## connect to database
<img src="https://user-images.githubusercontent.com/106481991/173718329-21861102-a84b-4109-a4dd-6b015baa1b6b.png" width="700px" />

## format
https://blog.csdn.net/mxw968/article/details/90900433

## insert data example
```sql
ALTER TABLE config ALTER COLUMN cid SET default nextval('public.cid');
INSERT INTO config 
	(timestamp, id, os, lang, rule_id, data, type, param)
VALUES
	(now(),	2, 'debain10',	'Chinese', 'BL999_8949', '{masked}',	0,	'{"systemctl is-enabled ctrl-alt-del.target"}'),
	(now(), 2, 'debain10',	'Chinese', 'BL999_7387', '{"fs.protected_symlinks = 1","fs.protected_hardlinks = 1"}',	0,	'{"sysctl fs.protected_symlinks","sysctl fs.protected_hardlinks"}'),
    (now(), 2, 'debain10',	'Chinese', 'BL999_3597', '{""}',	0,	$${"if echo $PATH | grep -q \\''::\\'' ; thenntecho \\''Empty Directory in PATH (::)\\''n  fin  if echo $PATH | grep -q \\'':$\\'' ; thenntecho \\''Trailing : in PATH\\''n  fin  for x in $(echo $PATH | tr \\'':\\'' \\'' \\'') ; dontif [ -d \\''$x\\'' ] ; thennt  ls -ldH \\''$x\\'' | awk nt  $9 == \\''.\\'' {print \\''PATH contains current working directory (.)\\''}nt  $3 != \\''root\\'' {print $9, \\''is not owned by root\\''}nt  substr($1,6,1) != \\''-\\'' {print $9, \\''is group writable\\''}nt  substr($1,9,1) != \\''-\\'' {print $9, \\''is world writable\\''} ntelsent  echo \\''$x is not a directory\\''ntfin  done"}$$),
	(now(), 2, 'debain10',	'Chinese', 'BL999_4000', '{""}',	0,	'{"mount | grep -E ''\\\\s/dev/shm\\\\s'' | grep -v nosuid"}'),
	(now(), 2, 'debain10',	'Chinese', 'BL999_6629', '{0644,0,0}',	1,	'{"stat /etc/issue"}'),
	(now(), 2, 'windows10',	'Chinese', 'BL696_0086', '{1}',	0,	'{"Machine\\\\System\\\\CurrentControlSet\\\\Control\\\\SCMConfig:EnableSvchostMitigationPolicy"}'),
	(now(), 2, 'windows10',	'Chinese', 'BL696_7921', '{1,2}',	0,	'{"Machine\\\\Software\\\\Policies\\\\Microsoft\\\\Windows\\\\SettingSync:DisableCredentialsSettingSync", "Machine\\\\Software\\\\Policies\\\\Microsoft\\\\Windows\\\\SettingSync:DisableCredentialsSettingSyncUserOverride"}'),   
	(now(),	2, 'windows10',	'Chinese', 'BL696_0461', null,	0,	'{"Xbox Live 网络服务"}'),
	(now(),	2, 'windows10',	'Chinese', 'BL696-0711', '{1,AllSigned}',	0,	'{"Machine\\\\Software\\\\Policies\\\\Microsoft\\\\Windows\\\\PowerShell:EnableScripts","Machine\\\\Software\\\\Policies\\\\Microsoft\\\\Windows\\\\PowerShell:ExecutionPolicy"}');
```

## key Sequence
```sql
ALTER TABLE table_nam ALTER COLUMN table_column SET default nextval('sequence_name');
eg.ALTER TABLE config ALTER COLUMN cid SET default nextval('public.cid');

TRUNCATE table_name RESTART IDENTITY;
eg.TRUNCATE config RESTART IDENTITY;
```

## remote getCS
PostgreSQL\13\data\pg_hba.conf add: 
```host all all IP Address/24 sram-sha-256```

## remote getES
elasticsearch.yml add: 
```network.host: 0.0.0.0```