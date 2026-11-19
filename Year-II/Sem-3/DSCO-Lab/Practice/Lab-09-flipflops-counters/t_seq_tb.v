`timescale 1ns/1ns
`include "t_seq.v"

module t_seq_tb;
    reg clk, rst, x;
    wire [1:0] state;

    t_seq dut(x, clk, rst, state);

    initial begin
        clk = 0;
        forever #5 clk = ~clk;
    end

    initial begin
        $dumpfile("t_seq_tb.vcd");
        $dumpvars(0, t_seq_tb);

        rst = 1; x = 0; #10;
        rst =0;

        //Case 1: x=0
        x=0; repeat(6) #10;
        
        //Case 2: x=1
        x=1; repeat(6) #10;
        $finish;
    end
    
    initial $monitor("x=%b -> state=%b", x, state);
endmodule