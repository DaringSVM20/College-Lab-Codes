`timescale 1ns/1ns
`include "multi2.v"
module multi2_tb();
    reg [1:0] a,b;
    wire [3:0] p;
    multi2 run(a,b,p);
    initial begin
      $dumpfile("multi2.vcd");
      $dumpvars(0, multi2_tb);
      a=2'b00; b=2'b00; #20;
      a=2'b01; b=2'b10; #20;
      a=2'b11; b=2'b11; #20;
      a=2'b10; b=2'b01; #20;
    $display("Test complete");
    end
endmodule