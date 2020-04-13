var f: text;
min,sms,S:real;
n,z,a1:string;
i,j,k,e:integer;
t:array[1..100]of real;
a:array[1..100] of string;
err:integer;
begin
n:='915642913';
e:=1;
assign(f,'./data.csv');
Reset (f);
for i:=1 to 100 do
readln(f,a[i]);
Close (f);
for i:=2 to 100 do begin
z:=a[i];
for j:=1 to length(z) do
if 
(z[j]=n[1])and(z[j+1]=n[2])and(z[j+2]=n[3])and(z[j+3]=n[4])and(z[j+4]=n[5])and
(z[j+5]=n[6])and(z[j+6]=n[7])and(z[j+7]=n[8])and(z[j+8]=n[9]) 
then
  begin
  for k:=j+10 to length(z) do
  if z[k]<>',' then a1:=a1+z[k] else begin  val(a1,t[e],err);inc(e);a1:=''; end;  
  if k=length(z) then begin  val(a1,t[e],err);inc(e);a1:=''; end;
  end;
end;
i:=1;
while i<10 do
 if t[i]>10000000 then begin  
  min:=min+t[i+1];
  sms:=sms+t[i+2];
  i:=i+3; continue;
  end else begin
    min:=min+t[i];
    i:=i+2; continue;
    end;
    {writeln('всего минут:  ',min);
    writeln('всего смс:  ',sms);}
if sms>5 then S:=sms-5+min else S:=min;
writeln('Пользователю ',n,' необходимо заплатить: ',S);
  end.