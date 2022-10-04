--
-- PostgreSQL database dump
--

-- Dumped from database version 13.7
-- Dumped by pg_dump version 13.7

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: cid; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cid
    START WITH 0
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cid OWNER TO postgres;

--
-- Name: SEQUENCE cid; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON SEQUENCE public.cid IS 'config 自增主键';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: config; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.config (
    cid bigint DEFAULT nextval('public.cid'::regclass) NOT NULL,
    "timestamp" timestamp with time zone NOT NULL,
    id integer NOT NULL,
    os text,
    lang text,
    rule_id text,
    data text[],
    type integer,
    param text[]
);


ALTER TABLE public.config OWNER TO postgres;

--
-- Name: COLUMN config.cid; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.config.cid IS '主键，自增';


--
-- Name: COLUMN config."timestamp"; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.config."timestamp" IS '时间戳，标记存储的时间';


--
-- Name: COLUMN config.id; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.config.id IS '配置文件 ID，方便读取';


--
-- Name: COLUMN config.os; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.config.os IS '标记分发的系统';


--
-- Name: COLUMN config.lang; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.config.lang IS '标记系统语言';


--
-- Name: COLUMN config.rule_id; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.config.rule_id IS '检查项 ID';


--
-- Name: COLUMN config.data; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.config.data IS '检查预期值';


--
-- Name: COLUMN config.type; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.config.type IS '比对类型（0直接比对，1需要进行文本处理）';


--
-- Name: COLUMN config.param; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.config.param IS '字符数组类型，存储多个命令行或 path';


--
-- Name: result; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.result (
    rid bigint NOT NULL,
    "timestamp" timestamp with time zone NOT NULL,
    id integer NOT NULL,
    os text,
    lang text,
    server_ip inet,
    host_ip inet,
    rule_id text,
    expected text[],
    actual text[],
    status text
);


ALTER TABLE public.result OWNER TO postgres;

--
-- Name: COLUMN result.rid; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.result.rid IS '主键，自增';


--
-- Name: COLUMN result."timestamp"; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.result."timestamp" IS '时间戳，标记采集的时间（统一）';


--
-- Name: COLUMN result.id; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.result.id IS '配置文件 ID，方便读取';


--
-- Name: COLUMN result.os; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.result.os IS '标记系统类型';


--
-- Name: COLUMN result.lang; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.result.lang IS '标记系统语言';


--
-- Name: COLUMN result.server_ip; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.result.server_ip IS '标记负责比对的主机';


--
-- Name: COLUMN result.host_ip; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.result.host_ip IS '标记被比对的主机';


--
-- Name: COLUMN result.rule_id; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.result.rule_id IS '标记是哪个检查项';


--
-- Name: COLUMN result.expected; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.result.expected IS '查询结果的预期值';


--
-- Name: COLUMN result.actual; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.result.actual IS '查询结果的实际值';


--
-- Name: COLUMN result.status; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.result.status IS '比对的结果（pass/failed）';


--
-- Name: rid; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.rid
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.rid OWNER TO postgres;

--
-- Name: rid; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.rid OWNED BY public.result.rid;


--
-- Name: SEQUENCE rid; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON SEQUENCE public.rid IS 'result 主键自增';


--
-- Name: result rid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.result ALTER COLUMN rid SET DEFAULT nextval('public.rid'::regclass);


--
-- Data for Name: config; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.config (cid, "timestamp", id, os, lang, rule_id, data, type, param) FROM stdin;
1	2022-08-11 15:47:58.658276+08	2	debain10	Chinese	BL999_8949	{masked}	0	{"systemctl is-enabled ctrl-alt-del.target"}
2	2022-08-11 15:47:58.658276+08	2	debain10	Chinese	BL999_7387	{"fs.protected_symlinks = 1","fs.protected_hardlinks = 1"}	0	{"sysctl fs.protected_symlinks","sysctl fs.protected_hardlinks"}
3	2022-08-11 15:47:58.658276+08	2	debain10	Chinese	BL999_3597	{""}	0	{"if echo $PATH | grep -q \\\\''::\\\\'' ; thenntecho \\\\''Empty Directory in PATH (::)\\\\''n  fin  if echo $PATH | grep -q \\\\'':$\\\\'' ; thenntecho \\\\''Trailing : in PATH\\\\''n  fin  for x in $(echo $PATH | tr \\\\'':\\\\'' \\\\'' \\\\'') ; dontif [ -d \\\\''$x\\\\'' ] ; thennt  ls -ldH \\\\''$x\\\\'' | awk nt  $9 == \\\\''.\\\\'' {print \\\\''PATH contains current working directory (.)\\\\''}nt  $3 != \\\\''root\\\\'' {print $9, \\\\''is not owned by root\\\\''}nt  substr($1,6,1) != \\\\''-\\\\'' {print $9, \\\\''is group writable\\\\''}nt  substr($1,9,1) != \\\\''-\\\\'' {print $9, \\\\''is world writable\\\\''} ntelsent  echo \\\\''$x is not a directory\\\\''ntfin  done"}
4	2022-08-11 15:47:58.658276+08	2	debain10	Chinese	BL999_4000	{""}	0	{"mount | grep -E '\\\\\\\\s/dev/shm\\\\\\\\s' | grep -v nosuid"}
5	2022-08-11 15:47:58.658276+08	2	debain10	Chinese	BL999_6629	{0644,0,0}	1	{"stat /etc/issue"}
6	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_0711	{1,AllSigned}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\PowerShell:EnableScripts","Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\PowerShell:ExecutionPolicy"}
7	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_0461	\N	0	{"Xbox Live 网络服务"}
8	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_0946	\N	0	{XblGameSave}
9	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_0416	\N	0	{XblAuthManager}
10	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_0506	\N	0	{XboxGipSvc}
11	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_0521	\N	0	{RasAuto}
12	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_0961	\N	0	{MSiSCSI}
13	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_0846	\N	0	{LxssManager}
14	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_0386	\N	0	{lfsvc}
15	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_0056	\N	0	{MapsBroker}
16	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_0936	\N	0	{BTAGService}
17	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_0636	\N	0	{bthserv}
18	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_0951	{1}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\PushNotifications:NoCloudApplicationNotification"}
19	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_0926	{2}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\Windows Search:AllowCloudSearch"}
20	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_0655	{1}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CloudContent:DisableSoftLanding"}
21	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_0303	{1}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\Connect:AllowProjectionToPC"}
22	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_0441	{1}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\SecondaryAuthenticationFactor:AllowSecondaryAuthenticationDevice"}
23	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_1831	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\FVE:RDVHardwareEncryption"}
24	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_1416	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\FVE:OSHardwareEncryption"}
25	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_1971	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\FVE:FDVHardwareEncryption"}
26	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_1026	{1}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\OneDrive:DisableFileSync"}
27	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_0701	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\PassportForWork:Enabled"}
28	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_4537	{2}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows Defender:PUAProtection"}
29	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_4754	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows Defender\\\\\\\\Spynet:LocalSettingOverrideSpynetReporting"}
30	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_3654	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows Defender\\\\\\\\Real-Time Protection:DisableRealtimeMonitoring"}
31	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_4876	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows Defender\\\\\\\\Real-Time Protection:LocalSettingOverrideDisableIOAVProtection"}
32	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_5871	{1}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows Defender\\\\\\\\Signature Updates:UpdateOnStartUp"}
33	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_2053	{0,1,2,3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows Defender\\\\\\\\Signature Updates:ASSignatureDue"}
34	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_2033	{0,1,2,3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows Defender\\\\\\\\Signature Updates:AVSignatureDue"}
35	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_2754	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows Defender\\\\\\\\Signature Updates:DisableUpdateOnStartupWithoutEngine"}
36	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_6483	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows Defender\\\\\\\\Scan:DisablePackedExeScanning"}
37	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_6444	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows Defender\\\\\\\\Scan:DisableHeuristics"}
38	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_6476	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows Defender\\\\\\\\NIS:DisableProtocolRecognition"}
39	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_5456	{2}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows Defender\\\\\\\\MpEngine:MpCloudBlockLevel"}
40	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_3987	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows Defender\\\\\\\\Real-Time Protection:DisableScanOnRealtimeEnable"}
41	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_6489	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows Defender\\\\\\\\Real-Time Protection:DisableRawWriteNotification"}
42	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_3934	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows Defender\\\\\\\\Real-Time Protection:DisableOnAccessProtection"}
43	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_3958	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows Defender\\\\\\\\Real-Time Protection:RealtimeScanDirection"}
44	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_2096	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows Defender\\\\\\\\Real-Time Protection:LocalSettingOverrideDisableRealtimeMonitoring"}
45	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_2034	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows Defender\\\\\\\\Real-Time Protection:LocalSettingOverrideDisableBehaviorMonitoring"}
46	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_4653	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows Defender\\\\\\\\Real-Time Protection:LocalSettingOverrideRealtimeScanDirection"}
47	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_4652	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows Defender\\\\\\\\Real-Time Protection:LocalSettingOverrideDisableOnAccessProtection"}
48	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7345	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows Defender\\\\\\\\Remediation:Scan_ScheduleDay"}
49	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_6453	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows Defender\\\\\\\\Spynet:DisableBlockAtFirstSeen"}
50	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_0136	{1}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows Defender\\\\\\\\Windows Defender Exploit Guard\\\\\\\\Network Protection:EnableNetworkProtection"}
51	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_0616	{1}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows Defender\\\\\\\\Scan:CheckForSignaturesBeforeRunningScan"}
52	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_0696	{1}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows Defender\\\\\\\\Signature Updates:SignatureUpdateInterval"}
53	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_0531	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows Defender\\\\\\\\Scan:DisableEmailScanning"}
54	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_0426	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows Defender\\\\\\\\Scan:DisableRemovableDriveScanning"}
55	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_0746	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows Defender\\\\\\\\Scan:DisableArchiveScanning"}
56	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_0771	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows Defender\\\\\\\\Real-Time Protection:DisableBehaviorMonitoring"}
57	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_0541	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows Defender\\\\\\\\Real-Time Protection:DisableIOAVProtection"}
58	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_0076	{1}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows Defender\\\\\\\\Reporting:DisableGenericRePorts"}
59	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_0981	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows Defender:DisableAntiSpyware"}
60	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_3689	{1}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows Defender Security Center\\\\\\\\App and Browser protection:DisallowExploitProtectionOverride"}
61	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_0486	{1}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows Defender Security Center\\\\\\\\App and Browser protection:DisallowExploitProtectionOverride"}
62	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_1421	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\System:EnableSmartScreen"}
63	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7896	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\1:1800"}
64	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7931	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\3:1607"}
65	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7556	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\3:1804"}
66	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7966	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\3:160A"}
67	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7561	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\3:2708"}
68	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7961	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\3:2709"}
69	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7266	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\3:2104"}
70	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7276	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\3:1800"}
71	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7356	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\3:1406"}
72	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7776	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:1806"}
73	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7016	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:1607"}
74	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7701	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:1804"}
75	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7971	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:160A"}
76	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7351	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:2708"}
77	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7381	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:2709"}
78	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7951	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:2104"}
79	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7926	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:1608"}
80	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7091	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:1800"}
81	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7676	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:1407"}
82	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7751	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:2300"}
83	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7261	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:1406"}
84	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7496	{65536}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\3:1A00"}
85	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7876	{196608}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:1A00"}
86	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7206	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\3:2500"}
87	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7156	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:2500"}
88	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7006	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\3:1A04"}
89	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7296	{1}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\3:1609"}
90	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7986	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:1601"}
91	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7316	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:1601"}
92	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7916	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:1609"}
93	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7271	{1}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings:WarnOnBadCertRecving"}
94	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7196	{2688}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings:SecureProtocols"}
95	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7041	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\3:1809"}
96	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7151	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:1809"}
97	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7161	{1}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Internet Explorer\\\\\\\\Security:DisableSecuritySettingsCheck"}
98	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7461	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Internet Explorer\\\\\\\\PhishingFilter:EnabledV8"}
99	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7541	{1}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Internet Explorer\\\\\\\\Main:DisableFirstRunCustomize"}
100	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7361	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Internet Explorer\\\\\\\\PhishingFilter:EnabledV9"}
101	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7231	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\2:2301"}
102	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7721	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\1:2301"}
103	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7086	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\3:2301"}
104	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7906	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:2301"}
105	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7346	{yes}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Internet Explorer\\\\\\\\Main:NoReportSiteProblems"}
106	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7871	{1}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Internet Explorer\\\\\\\\Restrictions:NoCrashDetection"}
107	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7126	{2}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Internet Explorer\\\\\\\\Recovery:AutoRecover"}
108	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7191	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Internet Explorer\\\\\\\\SQM:DisableCustomerImprovementProgram"}
109	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7956	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Internet Explorer\\\\\\\\Suggested Sites:Enabled"}
110	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7121	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Internet Explorer:AllowServicePoweredQSA"}
111	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7726	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Internet Explorer\\\\\\\\FlipAhead:Enabled"}
112	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7766	{1}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Internet Explorer\\\\\\\\Activities:NoActivities"}
113	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7426	{no}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Internet Explorer\\\\\\\\Main:Enable Browser Extensions"}
114	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7326	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\2:1409"}
115	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7526	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\1:1409"}
116	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7651	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\3:1409"}
117	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7616	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\3:2105"}
118	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7021	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\3:2103"}
119	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7631	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\3:2102"}
120	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7576	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\3:1206"}
121	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7976	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\3:1407"}
122	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7591	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\3:140C"}
123	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7806	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:1409"}
124	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7731	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:1402"}
125	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7571	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:2105"}
126	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7771	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:2103"}
127	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7981	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:2102"}
128	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7051	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:1206"}
129	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7831	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:1407"}
130	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7816	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:140C"}
131	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7581	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:1400"}
132	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7481	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\3:2200"}
133	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7536	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:2200"}
134	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7586	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:1604"}
135	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7146	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:1803"}
136	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7641	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\2:120A"}
137	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7306	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\1:120A"}
138	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7501	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\1:1209"}
139	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7036	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\3:120A"}
140	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7681	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\3:1209"}
141	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7226	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:120A"}
142	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7566	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:1209"}
143	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7301	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:2000"}
144	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7366	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\2:270C"}
145	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7881	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\1:1201"}
146	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7371	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\1:1004"}
147	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7786	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\1:270C"}
148	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7886	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\3:1201"}
149	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7781	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\3:1004"}
150	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7216	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\3:1001"}
151	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7026	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\3:270C"}
152	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7851	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\3:2201"}
153	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7066	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\3:120c"}
154	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7856	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\3:120b"}
155	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7246	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:1405"}
156	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7286	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:1200"}
157	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7211	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:1201"}
158	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7411	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:1004"}
159	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7546	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:1001"}
160	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7186	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:270C"}
161	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7866	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:2201"}
162	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7096	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:120c"}
163	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7801	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:120b"}
164	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7416	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\2:2600"}
165	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7446	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\1:2600"}
166	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7941	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\3:2600"}
167	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7626	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\3:2001"}
168	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7331	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\3:2004"}
169	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7761	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:2600"}
170	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7946	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:2001"}
171	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7101	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:2004"}
172	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7911	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:2401"}
173	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7011	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:2402"}
174	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7251	{3}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Internet Settings\\\\\\\\Zones\\\\\\\\4:2400"}
175	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7136	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Internet Explorer\\\\\\\\Feeds:AllowBasicAuthInClear"}
176	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7921	{2,1}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\SettingSync:DisableCredentialsSettingSync","Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\SettingSync:DisableCredentialsSettingSyncUserOverride"}
177	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7111	{2,1}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\SettingSync:DisableWebBrowserSettingSync","Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\SettingSync:DisableWebBrowserSettingSyncUserOverride"}
178	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_0467	{1}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\Appx:BlockNonAdminUserInstall"}
179	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_0891	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\Messaging:AllowMessageSync"}
180	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_1941	{1}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\WCN\\\\\\\\UI:DisableWcnUi"}
181	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_1036	{1}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\WCN\\\\\\\\Registrars:DisableInBand802DOT11Registrar"}
182	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_0086	{1}	0	{"Machine\\\\\\\\System\\\\\\\\CurrentControlSet\\\\\\\\Control\\\\\\\\SCMConfig:EnableSvchostMitigationPolicy"}
183	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_0611	{1}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\System:DisableLockScreenAppNotifications"}
184	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_0181	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\System:EnableActivityFeed"}
185	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_0194	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\System:EnableCdp"}
186	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_1341	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows NT\\\\\\\\Terminal Services:fAllowUnsolicited"}
187	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_1611	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows NT\\\\\\\\Terminal Services:fAllowToGetHelp"}
188	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_1576	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CredentialsDelegation:AllowSavedCredentialsWhenNTLMOnly"}
189	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_1596	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CredentialsDelegation:AllowSavedCredentials"}
190	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_1491	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CredentialsDelegation:AllowSavedCredentials"}
191	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_1696	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CredentialsDelegation:AllowFreshCredentialsWhenNTLMOnly"}
192	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_1481	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CredentialsDelegation:AllowDefCredentialsWhenNTLMOnly"}
193	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_1966	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CredentialsDelegation:AllowDefaultCredentials"}
194	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_1526	{1}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CredentialsDelegation:RestrictedRemoteAdministration"}
195	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_1266	{1}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CredentialsDelegation:AllowProtectedCreds"}
196	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_1821	{1}	0	{"Machine\\\\\\\\System\\\\\\\\CurrentControlSet\\\\\\\\Policies\\\\\\\\EarlyLaunch:DriverLoadPolicy"}
197	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_0306	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\DriverInstall\\\\\\\\Restrictions:AllowUserDeviceClasses"}
198	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_0081	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\StorageSense:AllowStorageSenseGlobal"}
199	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_0141	{0}	0	{"Machine\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\System:AllowCrossDeviceClipboard"}
200	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_1896	{1}	0	{"User\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CloudContent:DisableWindowsSpotlightOnSettings"}
201	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_1251	{1}	0	{"User\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CloudContent:DisableWindowsSpotlightOnActionCenter"}
202	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_1706	{1}	0	{"User\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CloudContent:DisableWindowsSpotlightWindowsWelcomeExperience"}
203	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_1156	{1}	0	{"User\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CloudContent:DisableWindowsSpotlightFeatures"}
204	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_1546	{1}	0	{"User\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CloudContent:DisableTailoredExperiencesWithDiagnosticData"}
205	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_1711	{1}	0	{"User\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CloudContent:DisableThirdPartySuggestions"}
206	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_1066	{2}	0	{"User\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CloudContent:ConfigureWindowsSpotlight"}
207	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_0819	{1}	0	{"User\\\\\\\\Software\\\\\\\\Microsoft\\\\\\\\Windows\\\\\\\\CurrentVersion\\\\\\\\Policies\\\\\\\\Explorer:NoRecycleFiles"}
208	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7636	{1}	0	{"User\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\IEAK:NoAutomaticSignup"}
209	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7471	{no}	0	{"User\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Internet Explorer\\\\\\\\Main:FormSuggest Passwords"}
210	2022-08-11 15:47:58.658276+08	2	windows10	Chinese	BL696_7596	{1}	0	{"User\\\\\\\\Software\\\\\\\\Policies\\\\\\\\Microsoft\\\\\\\\Internet Explorer\\\\\\\\Restrictions:NoHelpItemSendFeedback"}
\.


--
-- Data for Name: result; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.result (rid, "timestamp", id, os, lang, server_ip, host_ip, rule_id, expected, actual, status) FROM stdin;
22	2022-08-11 23:57:59.082+08	2	windows10	Chinese	192.168.56.1	192.168.1.8	BL696_0461	\N	\N	pass
23	2022-08-11 23:57:59.082+08	2	windows10	Chinese	192.168.56.1	192.168.1.8	BL696_0086	{1}	\N	failed
24	2022-08-11 23:57:59.082+08	2	windows10	Chinese	192.168.56.1	192.168.1.8	BL696_7921	{2,1}	\N	failed
25	2022-08-11 23:57:59.082+08	2	windows10	Chinese	192.168.56.1	192.168.1.8	BL696_0711	{1,AllSigned}	\N	failed
\.


--
-- Name: cid; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cid', 210, true);


--
-- Name: rid; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.rid', 25, true);


--
-- Name: config config_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.config
    ADD CONSTRAINT config_pkey PRIMARY KEY (cid);


--
-- Name: result result_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.result
    ADD CONSTRAINT result_pkey PRIMARY KEY (rid);


--
-- PostgreSQL database dump complete
--

