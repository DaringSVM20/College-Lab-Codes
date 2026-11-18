//Test bench code for q4
`timescale 1ns/1ns
`include "q4.v"

module tb_q4();
    reg [15:0]w;
    reg [3:0]s;
    wire f;
    q4 ex1(w,s,f);
    initial
    begin
    $dumpfile("tb_q4.vcd");
    $dumpvars(0,tb_q4);
    
    w = 16'b1010110011110001;

    s = 4'b0000; #20;
    s = 4'b0001; #20;
    s = 4'b0010; #20;
    s = 4'b0011; #20;
    s = 4'b0100; #20;
    s = 4'b0101; #20;
    s = 4'b0110; #20;
    s = 4'b0111; #20;
    s = 4'b1000; #20;
    s = 4'b1001; #20;
    s = 4'b1010; #20;
    s = 4'b1011; #20;
    s = 4'b1100; #20;
    s = 4'b1101; #20;
    s = 4'b1110; #20;
    s = 4'b1111; #20;

    $display("Test Completed!!");
    
    end
endmodule
