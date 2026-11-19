`timescale 1ns/1ns
`include "mux4_1.v"
module mux4_1_tb();
    reg [3:0] w;
    reg [1:0] s;
    wire f;
    mux4_1 uut(w,s,f);
    initial begin
        $dumpfile("mux4_1_tb.vcd");
        $dumpvars(0,mux4_1_tb);
        w = 4'b1010;
        s = 2'd0; #20;
        s = 2'd1; #20;
        s = 2'd2; #20;
        s = 2'd3; #20;
        $display("Test Complete");
    end
endmodule