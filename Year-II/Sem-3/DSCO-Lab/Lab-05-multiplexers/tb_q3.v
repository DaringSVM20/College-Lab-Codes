//Test bench code for q3
`timescale 1ns/1ns
`include "q3.v"

module tb_q3();
   reg [15:0]W;
   reg [3:0]S;
   wire f;
   q3 ex1(W,S,f);
   initial
   begin
   $dumpfile("tb_q3.vcd");
   $dumpvars(0,tb_q3);
   

   W = 16'b1010110011110001;

   S = 4'b0000; #20;
   S = 4'b0001; #20;
   S = 4'b0010; #20;
   S = 4'b0011; #20;
   S = 4'b0100; #20;
   S = 4'b0101; #20;
   S = 4'b0110; #20;
   S = 4'b0111; #20;
   S = 4'b1000; #20;
   S = 4'b1001; #20;
   S = 4'b1010; #20;
   S = 4'b1011; #20;
   S = 4'b1100; #20;
   S = 4'b1101; #20;
   S = 4'b1110; #20;
   S = 4'b1111; #20;

   $display("Test Complete!");
   
   end
endmodule
