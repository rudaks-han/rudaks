CREATE TABLE t_post
(
	id NUMERIC NOT NULL,
	category VARCHAR(100) NOT NULL,
	username VARCHAR(64) NOT NULL,
	email VARCHAR(128) NOT NULL,
	view_count NUMERIC DEFAULT 0,
	attach_count NUMERIC DEFAULT 0,
	ipaddress VARCHAR(15),
	delete_flag VARCHAR(1),
	title VARCHAR(1024) NOT NULL,
	old_seq NUMERIC,
	content TEXT,
	created_date VARCHAR(14) NOT NULL,
	updated_date VARCHAR(14) NOT NULL,
	CONSTRAINT pk_post PRIMARY KEY (id)
);

CREATE INDEX idx_post_old_seq ON t_post(old_seq);

CREATE INDEX idx_post_category ON t_post(category, id);

CREATE SEQUENCE seq_post START 1;

CREATE TABLE t_category
(
	id NUMERIC NOT NULL,
	category VARCHAR(100) NOT NULL,
	name VARCHAR(100) NOT NULL,
	delete_flag VARCHAR(1) DEFAULT 'N',
	public_flag VARCHAR(1) DEFAULT 'Y',
	sort_order NUMERIC,
	description VARCHAR(4000),
	created_date VARCHAR(14) NOT NULL,
	updated_date VARCHAR(14) NOT NULL,
	CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE SEQUENCE seq_category START 1;

CREATE TABLE t_attach_file
(
	post_id NUMERIC NOT NULL,
	seq NUMERIC NOT NULL,
	file_name VARCHAR(200) NOT NULL,
	file_path VARCHAR(200) NOT NULL,
	file_size NUMERIC,
	CONSTRAINT pk_attach_file PRIMARY KEY (post_id, seq)
);

CREATE TABLE t_guestbook
(
	id NUMERIC NOT NULL,	
	ref NUMERIC NOT NULL,
	username VARCHAR(64) NOT NULL,			
	email VARCHAR(128) NOT NULL,			
	ipaddress VARCHAR(15),
	delete_flag VARCHAR(1),	
	password VARCHAR(32),	
	comment VARCHAR(8000),
	created_date VARCHAR(14) NOT NULL,
	updated_date VARCHAR(14) NOT NULL,
	old_ref NUMERIC,
	CONSTRAINT pk_guestbook PRIMARY KEY (id)
);

CREATE SEQUENCE seq_guestbook START 1;


<!-- GEOIP 생성 -->
CREATE TABLE geoip_country
(
	begin_ip VARCHAR(64) NOT NULL,
	end_ip VARCHAR(64) NOT NULL,
	begin_ip_num bigint NOT NULL,
	end_ip_num bigint NOT NULL,
	country_code varchar(2) NOT NULL,
	country_name varchar(64) NOT NULL,
	CONSTRAINT pk_geoip_country PRIMARY KEY(begin_ip, end_ip)
)


COPY geoip_country (begin_ip, end_ip, begin_ip_num, end_ip_num, country_code, country_name)
FROM 'D:\_PROGRAM\rudaks.co.kr\rudaks-webroot\doc\GeoIP-108.csv' WITH CSV HEADER;


CREATE OR REPLACE FUNCTION inet_to_bigint(inet) RETURNS bigint AS $$
    SELECT $1 - inet '0.0.0.0'
$$ LANGUAGE SQL;

GRANT EXECUTE ON FUNCTION inet_to_bigint( inet) TO postgres;

select * from geoip_country
where inet_to_bigint('211.63.24.124') between begin_ip_num and end_ip_num


