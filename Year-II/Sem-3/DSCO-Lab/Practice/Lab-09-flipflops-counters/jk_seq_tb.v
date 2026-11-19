`timescale 1ns/1ns
`include "jk_seq.v"

module jk_seq_tb;
    reg E, x, clk, rst;
    wire [1:0] state;

    jk_seq dut(E, x, clk, rst, state);

    initial begin
        $dumpfile("jk_seq_tb.vcd");
        $dumpvars(0, jk_seq_tb);

        clk = 0;
        forever #5 clk = ~clk;
    end

    initial begin
        rst = 1; E = 0; x = 0; #10;
        rst = 0;

        E =1; x=1;
        repeat(4) #10;
        E=1; x=0;
        repeat(4) #10;
        E=0; x=0;
        repeat(4) #10;
        $finish;
    end

    initial $monitor("E=%b x=%b -> state=%b", E, x, state);
endmodule