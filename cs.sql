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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: config; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.config (
    "timestamp" text NOT NULL,
    id integer,
    os text,
    ruleid text,
    data text,
    type integer,
    param text[]
);


ALTER TABLE public.config OWNER TO postgres;

--
-- Name: TABLE config; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.config IS '配置文件';


--
-- Name: COLUMN config."timestamp"; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.config."timestamp" IS '时间戳，标记存储的时间';


--
-- Name: COLUMN config.id; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.config.id IS '配置文件ID，方便读取';


--
-- Name: COLUMN config.os; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.config.os IS '标记分发的系统';


--
-- Name: COLUMN config.ruleid; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.config.ruleid IS '检查项 ID';


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

COMMENT ON COLUMN public.config.param IS '字符数组类型，存储多个命令行或path';


--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- Name: result; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.result (
    "timestamp" text NOT NULL,
    id integer,
    os text,
    serverip text,
    expected text,
    actual text,
    hostip text,
    status text,
    ruleid text
);


ALTER TABLE public.result OWNER TO postgres;

--
-- Name: TABLE result; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.result IS '比对结果';


--
-- Name: COLUMN result."timestamp"; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.result."timestamp" IS '时间戳，标记存储的时间';


--
-- Name: COLUMN result.id; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.result.id IS '配置文件ID，方便读取';


--
-- Name: COLUMN result.os; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.result.os IS '标记系统类型';


--
-- Name: COLUMN result.serverip; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.result.serverip IS '标记负责比对的主机';


--
-- Name: COLUMN result.expected; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.result.expected IS '查询结果的预期值';


--
-- Name: COLUMN result.actual; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.result.actual IS '查询结果的实际值';


--
-- Name: COLUMN result.hostip; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.result.hostip IS '标记被比对的主机';


--
-- Name: COLUMN result.status; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.result.status IS '比对的结果（pass/failed)';


--
-- Name: COLUMN result.ruleid; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.result.ruleid IS '标记是哪个检查项';


--
-- Data for Name: config; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.config ("timestamp", id, os, ruleid, data, type, param) FROM stdin;
20220524	1	Windows10	BL696_0461	0	0	{"Xbox Live 网络服务"}
20220525\n	1	Windows10	BL696_0086	1	0	{"Machine\\\\System\\\\CurrentControlSet\\\\Control\\\\SCMConfig:EnableSvchostMitigationPolicy"}
20220529	1	Windows10	BL696-0711	0	0	{"Machine\\\\Software\\\\Policies\\\\Microsoft\\\\Windows\\\\PowerShell:EnableScripts","Machine\\\\Software\\\\Policies\\\\Microsoft\\\\Windows\\\\PowerShell:ExecutionPolicy"}
\.


--
-- Data for Name: result; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.result ("timestamp", id, os, serverip, expected, actual, hostip, status, ruleid) FROM stdin;
20220530	1	windows10	192.168.1.103	1	1	192.168.1.155	pass	\N
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 1, false);


--
-- Name: config config_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.config
    ADD CONSTRAINT config_pk PRIMARY KEY ("timestamp");


--
-- Name: result result_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.result
    ADD CONSTRAINT result_pk PRIMARY KEY ("timestamp");


--
-- PostgreSQL database dump complete
--

