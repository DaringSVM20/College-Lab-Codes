`timescale 1ns/1ns
`include "q2.v"
module q2_tb;
    reg w1,w2,w3;
    wire f;
    q2 uut(w1,w2,w3,f);
    initial begin
        $dumpfile("q2_tb.vcd");
        $dumpvars(0,q2_tb);
        w1=0;w2=0;w3=0;#10;
        w1=0;w2=0;w3=1;#10;
        w1=0;w2=1;w3=0;#10;
        w1=0;w2=1;w3=1;#10;
        w1=1;w2=0;w3=0;#10;
        w1=1;w2=0;w3=1;#10;
        w1=1;w2=1;w3=0;#10;
        w1=1;w2=1;w3=1;#10;
        $finish;
    end
endmodule