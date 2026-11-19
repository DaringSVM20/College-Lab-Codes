`timescale 1ns/1ns
`include "q4.v"
module q4_tb;
    reg [3:1]w;
    wire f;
    q4 uut(w,f);
    initial begin
        $dumpfile("q4_tb.vcd");
        $dumpvars(0,q4_tb);
        w=3'd0;#10;
        w=3'd1;#10;
        w=3'd2;#10;
        w=3'd3;#10;
        w=3'd4;#10;
        w=3'd5;#10;
        w=3'd6;#10;
        w=3'd7;#10;
        $display("Test Complete!");
        $finish;
    end
endmodule