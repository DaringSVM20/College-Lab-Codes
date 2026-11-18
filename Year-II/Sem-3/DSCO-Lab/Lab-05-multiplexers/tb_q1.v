//Test bench code for 4-bit comparator
`timescale 1ns/1ns
`include "q1.v"

module tb_q1();
    reg [3:0]A,B;
    wire A_gt_B,A_eq_B,A_lt_B;
    q1 ex1(A,B,A_gt_B,A_eq_B,A_lt_B);
    initial 
    begin
    
    $dumpfile("tb_q1.vcd");
    $dumpvars(0,tb_q1);
    
    A = 4'b0000; B = 4'b0000; #20;
    A = 4'b0010; B = 4'b0001; #20;
    A = 4'b0100; B = 4'b1000; #20;
    A = 4'b1111; B = 4'b1111; #20;
    A = 4'b1001; B = 4'b0110; #20;
    A = 4'b0111; B = 4'b1110; #20;
    
    $display("Test complete");
    end
endmodule
