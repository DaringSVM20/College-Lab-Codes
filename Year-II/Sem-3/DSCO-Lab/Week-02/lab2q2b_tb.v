// q2b_tb.v
`timescale 1ns/1ns
`include "lab2q2b.v"

module lab2q2b_tb();
reg a,b,c,d;
wire f;

lab2q2b test(a,b,c,d,f);
initial
begin

  $dumpfile("lab2q2b_tb.vcd");
  $dumpvars(0, lab2q2b_tb);
  
  a=0; b=0; c=0; d=0; #20
  a=0; b=0; c=0; d=1; #20
  a=0; b=0; c=1; d=0; #20
  a=0; b=0; c=1; d=1; #20
  a=0; b=1; c=0; d=0; #20
  a=0; b=1; c=0; d=1; #20
  a=0; b=1; c=1; d=0; #20
  a=0; b=1; c=1; d=1; #20
  a=1; b=0; c=0; d=0; #20
  a=1; b=0; c=0; d=1; #20
  a=1; b=0; c=1; d=0; #20
  a=1; b=0; c=1; d=1; #20
  a=1; b=1; c=0; d=0; #20
  a=1; b=1; c=0; d=1; #20
  a=1; b=1; c=1; d=0; #20
  a=1; b=1; c=1; d=1; #20

  $display("Test q2b complete");
end
endmodule
