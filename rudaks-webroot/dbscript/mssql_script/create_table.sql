CREATE TABLE [t_bbs] (
	[seq] [int] IDENTITY (1, 1) NOT NULL ,
	[name] [varchar] (30) COLLATE Korean_Wansung_CI_AS NOT NULL ,
	[email] [varchar] (200) COLLATE Korean_Wansung_CI_AS NULL ,
	[title] [varchar] (200) COLLATE Korean_Wansung_CI_AS NOT NULL ,
	[writeday] [varchar] (30) COLLATE Korean_Wansung_CI_AS NULL ,
	[passwd] [varchar] (20) COLLATE Korean_Wansung_CI_AS NOT NULL ,
	[num] [int] NOT NULL ,
	[bbs] [varchar] (20) COLLATE Korean_Wansung_CI_AS NULL ,
	[cnt] [int] NULL ,
	[ref] [int] NULL ,
	[step] [int] NULL ,
	[b_level] [char] (10) COLLATE Korean_Wansung_CI_AS NULL ,
	[attach_count] [int] NULL CONSTRAINT [DF_t_bbs_attach_count] DEFAULT (0),
	[filename1] [varchar] (50) COLLATE Korean_Wansung_CI_AS NULL ,
	[filename2] [varchar] (50) COLLATE Korean_Wansung_CI_AS NULL ,
	[filename3] [varchar] (50) COLLATE Korean_Wansung_CI_AS NULL ,
	[userIp] [varchar] (20) COLLATE Korean_Wansung_CI_AS NULL ,
	[flag_delete] [varchar] (2) COLLATE Korean_Wansung_CI_AS NULL ,
	[content] [text] COLLATE Korean_Wansung_CI_AS NULL ,
	[exp] [text] COLLATE Korean_Wansung_CI_AS NULL 
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO


CREATE TABLE [t_bbs_info] (
	[seq] [int] IDENTITY (1, 1) NOT NULL ,
	[bbs] [varchar] (20) COLLATE Korean_Wansung_CI_AS NOT NULL ,
	[name] [varchar] (50) COLLATE Korean_Wansung_CI_AS NOT NULL ,
	[flag_delete] [varchar] (10) COLLATE Korean_Wansung_CI_AS NOT NULL CONSTRAINT [DF_t_bbs_info_flag_delete] DEFAULT ('N'),
	[flag_public] [varchar] (10) COLLATE Korean_Wansung_CI_AS NULL CONSTRAINT [DF_t_bbs_info_flag_public] DEFAULT ('Y'),
	[sort_order] [int] NULL CONSTRAINT [DF_t_bbs_info_sort_order] DEFAULT (1),
	[exp] [varchar] (1000) COLLATE Korean_Wansung_CI_AS NULL 
) ON [PRIMARY]
GO

CREATE TABLE [t_file] (
	[seq] [int] NULL ,
	[file_serial] [int] NULL ,
	[file_name] [varchar] (200) COLLATE Korean_Wansung_CI_AS NULL ,
	[file_path] [varchar] (200) COLLATE Korean_Wansung_CI_AS NULL ,
	[file_size] [int] NULL 
) ON [PRIMARY]
GO

CREATE TABLE [t_member] (
	[user_id] [varchar] (30) COLLATE Korean_Wansung_CI_AS NOT NULL ,
	[passwd] [varchar] (30) COLLATE Korean_Wansung_CI_AS NULL ,
	[user_name] [varchar] (30) COLLATE Korean_Wansung_CI_AS NULL ,
	[email] [varchar] (50) COLLATE Korean_Wansung_CI_AS NULL ,
	[ipaddress] [varchar] (30) COLLATE Korean_Wansung_CI_AS NULL ,
	[last_login] [varchar] (30) COLLATE Korean_Wansung_CI_AS NULL ,
	[flag_delete] [varchar] (2) COLLATE Korean_Wansung_CI_AS NULL ,
	[grade] [varchar] (2) COLLATE Korean_Wansung_CI_AS NULL ,
	[login_count] [int] NULL CONSTRAINT [DF_t_member_login_count] DEFAULT (0),
	[register_date] [varchar] (30) COLLATE Korean_Wansung_CI_AS NULL ,
	CONSTRAINT [PK__t_member__300424B4] PRIMARY KEY  CLUSTERED 
	(
		[user_id]
	)  ON [PRIMARY] 
) ON [PRIMARY]
GO




