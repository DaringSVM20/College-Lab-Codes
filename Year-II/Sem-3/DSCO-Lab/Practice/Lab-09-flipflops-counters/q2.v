module dff(D,clk,clr,Q);
input D,clk,clr;
output reg Q;
initial Q=0;

always @(posedge clk)
if(clr)
Q<=0;
else
Q<=D;
endmodule

module q2(w,clk,clr,A,B,C);
input clk, clr, w;
output wire A,B,C;
wire DA,DB,DC;


dff s1(DA, clk, clr, A);
dff s2(DB, clk, clr, B);
dff s3(DC, clk, clr, C);

assign DA = (C & w)|(~A & B & ~w)|(A & ~B & ~w);
assign DB = C|(A & ~B);
assign DC = (~A & B & w)|(A & B & ~w);

endmodule