CREATE database qlnhanvien

create table PHONGBAN
(
	MaPhong varchar(10) not null primary key,
	TenPhong nvarchar(100) not null
)

create table NHANVIEN
(
	MaNV varchar(10) not null primary key,
	TenNV nVarchar(100) not null,
	NgaySinh varchar(10) not null,
	LuongNgay float not null default 0 check(LuongNgay between 0 and 10000000),
	MaPhong varchar(10) not null references PHONGBAN(MaPhong)
)


create table DUAN
(
	MaDA varchar(10) not null primary key,
	TenDA varchar(100) not null,
	SoNgay float not null,
	MaNV varchar(10) not null references NHANVIEN(MaNV)
)


insert PhongBan( MaPhong, TenPhong)
values ('p1' , N'Phòng Nhân sự')
insert PhongBan( MaPhong, TenPhong)
values ('p2' , N'Phòng Kế toán')
insert PhongBan( MaPhong, TenPhong)
values ('p3' , N'Phòng Đào tạo')
---------------------------------------
insert Nhanvien( MaNV, TenNV, NgaySinh, LuongNgay, MaPhong)
values ('nv1', N'Nguyễn Văn A', '19/06/1992', 700, 'p1')
insert Nhanvien( MaNV, TenNV, NgaySinh, LuongNgay, MaPhong)
values ('nv2', N'Nguyễn Văn B', '20/07/1992', 600, 'p2')
insert Nhanvien( MaNV, TenNV, NgaySinh, LuongNgay, MaPhong)
values ('nv3', N'Nguyễn Văn C', '21/08/1992', 500, 'p3')
------------------------------------------------
insert DuAn( MaDA, TenDA, SoNgay, MaNV)
values ('1',N'Quy chế thi', 30, 'nv1')
insert DuAn( MaDA, TenDA, SoNgay, MaNV)
values ('2',N'Lập bảng điểm', 29, 'nv2')
insert DuAn( MaDA, TenDA, SoNgay, MaNV)
values ('3',N'Coi thi', 28, 'nv3')--Q2-----------------------------------------create proc sp_PHONGBANInsert( @MaPhong varchar(10), @TenPhong nvarchar(100) )
as
if not exists (select * from PhongBan where MaPhong=@MaPhong)
begin
	insert PhongBan(MaPhong, TenPhong)
	values(@MaPhong, @TenPhong)
	print N'Them thanh cong'
	return 1
end
else
begin
	print N'Ma phong đa ton tai'
	return 0
end
exec sp_PHONGBANInsert 'p4', 'Phòng thiết bị'--------------------------------------------------create proc sp_NHANVIENInsert(@MaNV varchar(10), @TenNV nvarchar(100), @NgaySinh varchar(10), @LuongNgay float, @MaPhong varchar(10))
as
if not exists (select * from Nhanvien where MaNV=@MaNV)
begin
	insert Nhanvien(MaNV, TenNV, NgaySinh, LuongNgay, MaPhong )
	values(@MaNV, @TenNV, @NgaySinh, @LuongNgay, @MaPhong)
	print N'Thêm thành công'
	return 1
end
else
begin
	print N'Nhân viên đã tồn tại'
	return 0
endexec sp_NHANVIENInsert 'nv4', N'Nguyễn Văn D', '25/09/1992', 5000000, 'p4'-----------------------------------------------------------------create proc sp_DUANInsert (@MaDa varchar(10),@TenDa varchar(100), @SoNgay float, @MaNV varchar(10))
as
if not exists (select * from DuAn where MaDa=@MaDa)
begin
	insert DuAn(MaDa, TenDa, SoNgay, MaNV)
	values(@MaDa, @TenDa, @SoNgay, @MaNV)
	print N'Thêm thành công'
	return 1
end
else
begin
	print N'Mã dự án đã tồn tại'
	return 0
end
exec sp_DUANInsert '4', N'Quản nhân viên', 30, 'nv4'-----------------------------------------------------------create proc spud_DUAN (@MaDa varchar(10),@TenDa varchar(100), @SoNgay float, @MaNV varchar(10))
as
if exists (select * from DuAn where MaDa=@MaDa)
begin
	update DuAn
	set TenDa=@TenDa , SoNgay=@SoNgay , MaNV=@MaNV
	where MaDa=@MaDa
	print N'Cập nhật thành công'
	return 1
end
else
begin
	print N'Mã dự án không tồn tại'
	return 0
end
exec spud_DUAN '3', N'Quản lý sinh viên' , 20, 'nv4'
------------------------------------------------------
create proc spud_TongLuongNV (@MaNV varchar(10), @TongLuong float output)
as
begin
	select @TongLuong= sum(t1.LuongNgay*t2.SoNgay)
	from Nhanvien t1, DuAn t2
	where t1.MaNV= t2.MaNV and t1.MaNV=@MaNV
end
Declare @Tong Float
Exec spud_TongLuongNV 'nv4', @Tong output
Print N'Tổng lương ngày:'+ cast( @Tong as Varchar(200))