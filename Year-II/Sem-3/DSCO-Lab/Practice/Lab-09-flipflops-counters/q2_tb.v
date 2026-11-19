`timescale 1ns/1ns
`include "q2.v"

module q2_tb();
reg clk, clr, w;
wire A,B,C;

q2 uut(w,clk,clr,A,B,C);

initial
begin

$dumpfile("q2_tb.vcd");
$dumpvars(0, q2_tb);

clk=0;
forever #10 clk = ~clk;

end
initial
begin

  clr = 1; w = 0;      // reset active
    #5 clr = 0;          // release reset
    #100 w = 1;          // change direction
    #100;

$display("success");
$finish;
end
endmodule
